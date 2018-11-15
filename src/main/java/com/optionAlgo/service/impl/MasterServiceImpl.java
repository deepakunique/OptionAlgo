package com.optionAlgo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optionAlgo.dao.MasterDao;
import com.optionAlgo.entity.Expiry;
import com.optionAlgo.entity.OptionBean;
import com.optionAlgo.form.data.FutureScripFormData;
import com.optionAlgo.form.data.PayOffChartDto;
import com.optionAlgo.form.data.PayOffResponseDto;
import com.optionAlgo.form.data.PositionDetailDto;
import com.optionAlgo.form.data.PositionGreekDto;
import com.optionAlgo.form.data.PositionPnlDto;
import com.optionAlgo.form.data.PositionRequestFormDto;
import com.optionAlgo.form.data.SystemStrategyRequestFormDto;
import com.optionAlgo.service.MasterService;
import com.optionAlgo.utility.AppConstant;
import com.optionAlgo.utility.CalculationUtility;
import com.optionAlgo.utility.SystemStrategyGeneratorUtility;
import com.optionAlgo.utility.SystemStrategyResponseFormDto;


@Service
public class MasterServiceImpl implements MasterService {
	@Autowired
	private MasterDao masterDao;
	
	@Override
	public List<Expiry> getAllExpiries() {
		// TODO Auto-generated method stub
		return masterDao.getAllExpiries();
	}
	
	@Override
	public List<String> getAllScripNames() {
		// TODO Auto-generated method stub
		return masterDao.getAllScripNames();
	}
	
	@Override
	public FutureScripFormData getScripDataByScripName(String scripName) {
		// TODO Auto-generated method stub
		return masterDao.getScripDataByScripName(scripName);
	}
	
	

	@Override
	public List<SystemStrategyResponseFormDto> getSystemStrategy(SystemStrategyRequestFormDto sysDto) {
		
		Double cmp = masterDao.getSpotPriceByScrip(sysDto.getScripName(), AppConstant.currentSeries);
		List<Double>itmSPList = masterDao.getStrikePriceByScripNameLimit(sysDto.getScripName(), 
				3, true, cmp, AppConstant.currentSeries);
		
		List<Double>otmSPList = masterDao.getStrikePriceByScripNameLimit(sysDto.getScripName(), 
				3, false, cmp, AppConstant.currentSeries);
		
		Double spread = otmSPList.get(0)-itmSPList.get(0);
		Double atmSp;
		if(cmp-itmSPList.get(0)>spread/2)
			atmSp = otmSPList.get(0);
		else
			atmSp = itmSPList.get(0);
		
		List<Double>allPList = new ArrayList<>();
		allPList.addAll(otmSPList);
		allPList.addAll(itmSPList);
		Map<String, OptionBean> optionBeanMap = masterDao.getOptionBeanByStrikePrice(sysDto.getScripName(), AppConstant.currentSeries, allPList);
		List<SystemStrategyResponseFormDto>sysList  = SystemStrategyGeneratorUtility.shortStraddles(sysDto, itmSPList, otmSPList, atmSp, optionBeanMap);
		sysList.addAll(SystemStrategyGeneratorUtility.shortStrangle(sysDto, itmSPList, otmSPList, atmSp, optionBeanMap));
		sysList.addAll(SystemStrategyGeneratorUtility.ironCondor(sysDto, itmSPList, otmSPList, atmSp, optionBeanMap));
		sysList.addAll(SystemStrategyGeneratorUtility.ironButterfly(sysDto, itmSPList, otmSPList, atmSp, optionBeanMap));
		sysList.addAll(SystemStrategyGeneratorUtility.butterfly(sysDto, itmSPList, otmSPList, atmSp, optionBeanMap));
		return sysList;
		
	}
	
	@Override
	public PayOffResponseDto getPayOffDetail(PositionRequestFormDto positionDto) {
		// TODO Auto-generated method stub
		PayOffResponseDto payOffDto = new PayOffResponseDto();
		List<PositionGreekDto> pGreekList = new ArrayList<>();
		PositionGreekDto pGreekTotal = new PositionGreekDto(); 
		PositionPnlDto pnlDto = null;
		PositionPnlDto pnlDtoTotal = new PositionPnlDto();
		pnlDtoTotal.setPosition("P&L Total");
		Double pnlTotal=0.0;
		pGreekTotal.setPosition("Greeks Total");
		
		for(PositionDetailDto poDto : positionDto.getPositionList()){
			if(poDto.getInstrumentType().equals("O")){
				PositionGreekDto pGreek =CalculationUtility.calaculateOptionGreek(poDto, positionDto.getPayOffDate());
				pGreekTotal.setDelta(pGreekTotal.getDelta()+pGreek.getDelta());
				pGreekTotal.setVega(pGreekTotal.getVega()+pGreek.getVega());
				pGreekTotal.setTheta(pGreekTotal.getTheta()+pGreek.getTheta());
				pGreekTotal.setGamma(pGreekTotal.getGamma()+pGreek.getGamma());
				pGreekList.add(pGreek);
			}
			
			
			pnlDto =calculatePnLPerPosition(poDto,positionDto.getScripName());
			pnlTotal = pnlTotal+pnlDto.getPnl();
			payOffDto.getPnlDto().add(pnlDto);
		}
		pnlDtoTotal.setPnl(pnlTotal);
		pGreekList.add(pGreekTotal);
		payOffDto.getPnlDto().add(pnlDtoTotal);
		CalculationUtility.getPayOffCalculation(positionDto.getPositionList(), payOffDto);
		payOffDto.setGreekDto(pGreekList);
		
		
		// Charts Data preparations ::
		Double changeFrequency = 0.05; // Data point will be generated for every 0.05 peercent change in price.
		Double percentLimit = 0.25;
		Double cmp = masterDao.getSpotPriceByScrip(positionDto.getScripName(), AppConstant.currentSeries);
		List<PayOffChartDto> chartDtoList=CalculationUtility.calculatePayOffChartDataPoint(positionDto, cmp, changeFrequency, percentLimit);
		payOffDto.setChartDto(chartDtoList);
		payOffDto.setPnl(pnlTotal+"");
		return payOffDto;
	}
	
	private PositionPnlDto calculatePnLPerPosition(PositionDetailDto po,String scripName){
		PositionPnlDto pnlDto = new PositionPnlDto();
		pnlDto.setPosition(CalculationUtility.createPositionName(po));
		Double pnl=0.0;
			
			Double cmp = masterDao.getCurrentPriceForPosition(po, scripName);
			if(po.getExitPrice()!=null){
				
				if(po.getAction().equals("B"))
					pnl = (po.getExitPrice() - po.getEntryPrice())*po.getLotQty()*po.getLotSize();
				else
					pnl = (po.getEntryPrice()-po.getExitPrice())*po.getLotQty()*po.getLotSize() ;
			} else {
				
				if(po.getAction().equals("B"))
					pnl = (cmp - po.getEntryPrice())*po.getLotQty()*po.getLotSize();
				else
					pnl = (po.getEntryPrice()-cmp)*po.getLotQty()*po.getLotSize() ;
					
			}
			
			pnlDto.setEntryPrice(po.getEntryPrice());
			pnlDto.setCurrentPrice(cmp);
			pnlDto.setExitPrice(po.getExitPrice());
			pnlDto.setPnl(pnl);
		return pnlDto;
	}
	
	
}
