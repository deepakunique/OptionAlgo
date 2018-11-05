package com.optionAlgo.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.optionAlgo.dao.MasterDao;
import com.optionAlgo.entity.OptionBean;
import com.optionAlgo.form.data.PayOffResponseDto;
import com.optionAlgo.form.data.PositionDetailDto;
import com.optionAlgo.form.data.PositionRequestFormDto;
import com.optionAlgo.form.data.SystemStrategyRequestFormDto;

public class SystemStrategyGeneratorUtility {
	
	@Autowired
	private MasterDao masterDao;
	
	public static List<SystemStrategyResponseFormDto> shortStraddles(SystemStrategyRequestFormDto sysDto, List<Double> itmList, List<Double> otmList, 
			Double atm, Map<String, OptionBean> optionBeanMap){
		
		List<PositionRequestFormDto> poRequestList = new ArrayList<>();
		PositionRequestFormDto positionRequestFormDto = new PositionRequestFormDto();
		PositionDetailDto poDto = null;
		OptionBean callOb =  null;
		List<SystemStrategyResponseFormDto> responseList = new ArrayList<>();
		SystemStrategyResponseFormDto sysResponseDto= new SystemStrategyResponseFormDto();
		
		
		// First Short straddle
		callOb = optionBeanMap.get(itmList.get(0)+"CE");
		poDto = populatePositionDetailDto("S", sysDto.getPricingType(), callOb);
		positionRequestFormDto.getPositionList().add(poDto);
		
		callOb = optionBeanMap.get(itmList.get(0)+"PE");
		poDto = populatePositionDetailDto("S", sysDto.getPricingType(), callOb);
		positionRequestFormDto.getPositionList().add(poDto);
		
		poRequestList.add(positionRequestFormDto);
		positionRequestFormDto = new PositionRequestFormDto();
		// Second Short straddle
		
		callOb = optionBeanMap.get(otmList.get(0)+"CE");
		poDto = populatePositionDetailDto("S", sysDto.getPricingType(), callOb);
		positionRequestFormDto.getPositionList().add(poDto);
		
		callOb = optionBeanMap.get(otmList.get(0)+"PE");
		poDto = populatePositionDetailDto("S", sysDto.getPricingType(), callOb);
		positionRequestFormDto.getPositionList().add(poDto);
		
		poRequestList.add(positionRequestFormDto);
		String position ="";
		
		for(PositionRequestFormDto pDto : poRequestList){
			PayOffResponseDto payOffDto = new PayOffResponseDto();
			CalculationUtility.getPayOffCalculation(pDto.getPositionList(), payOffDto);
			sysResponseDto = new SystemStrategyResponseFormDto();
			sysResponseDto.setMaxLoss(payOffDto.getMaxLoss());
			sysResponseDto.setMaxProfit(payOffDto.getMaxProfit());
			for(PositionDetailDto po : pDto.getPositionList()){
				position= position+CalculationUtility.createPositionName(po) + " ";
			}
			sysResponseDto.setPosition(position);
			sysResponseDto.setStrategy("Short Straddle");
			responseList.add(sysResponseDto);
		}
		
		return responseList;
		
	}

	private static PositionDetailDto populatePositionDetailDto(String action, String priceType, OptionBean callOb) {
		PositionDetailDto poDto = new PositionDetailDto();
		poDto.setAction(action);
		if(priceType.equals("LTP"))
			poDto.setEntryPrice(callOb.getLtp());
		else if (!priceType.equals("LTP") && action.equals("S") ) 
			poDto.setEntryPrice(callOb.getBidPrice());
		else if (!priceType.equals("LTP") && action.equals("B") ) 
			poDto.setEntryPrice(callOb.getAskPrice());
		
		poDto.setExpiryDate(callOb.getSeriesName());
		poDto.setInstrumentType("O");
		poDto.setIv(callOb.getIv());
		poDto.setOptionType(callOb.getOptionType());
		poDto.setStrikePrice(callOb.getStrikePrice());
		return poDto;
	}
}
