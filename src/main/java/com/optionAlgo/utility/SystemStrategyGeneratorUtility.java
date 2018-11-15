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
		poDto = populatePositionDetailDto("S", sysDto.getPricingType(), callOb, sysDto.getLotSize(), 1);
		positionRequestFormDto.getPositionList().add(poDto);
		
		callOb = optionBeanMap.get(itmList.get(0)+"PE");
		poDto = populatePositionDetailDto("S", sysDto.getPricingType(), callOb, sysDto.getLotSize(), 1);
		positionRequestFormDto.getPositionList().add(poDto);
		
		poRequestList.add(positionRequestFormDto);
		positionRequestFormDto = new PositionRequestFormDto();
		// Second Short straddle
		
		callOb = optionBeanMap.get(otmList.get(0)+"CE");
		poDto = populatePositionDetailDto("S", sysDto.getPricingType(), callOb, sysDto.getLotSize(), 1);
		positionRequestFormDto.getPositionList().add(poDto);
		
		callOb = optionBeanMap.get(otmList.get(0)+"PE");
		poDto = populatePositionDetailDto("S", sysDto.getPricingType(), callOb, sysDto.getLotSize(),1);
		positionRequestFormDto.getPositionList().add(poDto);
		
		poRequestList.add(positionRequestFormDto);
		String position ="";
		
		for(PositionRequestFormDto pDto : poRequestList){
			position ="";
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

	public static List<SystemStrategyResponseFormDto> ironCondor(SystemStrategyRequestFormDto sysDto, List<Double> itmList, List<Double> otmList, 
			Double atm, Map<String, OptionBean> optionBeanMap){
		
		List<PositionRequestFormDto> poRequestList = new ArrayList<>();
		PositionRequestFormDto positionRequestFormDto = null;
		PositionDetailDto poDto = null;
		PositionDetailDto poDtoForHigherput = null;
		PositionDetailDto poDtoFoLowerput = null;
		PositionDetailDto poDtoForHigherCall = null;
		PositionDetailDto poDtoFoLowerCall = null;
		
		OptionBean putHigherOb =  null;
		OptionBean putLowerOb =  null;
		OptionBean callHigherOb =  null;
		OptionBean callLowerOb =  null;
		List<SystemStrategyResponseFormDto> responseList = new ArrayList<>();
		SystemStrategyResponseFormDto sysResponseDto= new SystemStrategyResponseFormDto();
		int itmSize = itmList.size();
		int otmSize = otmList.size();
		
		for(int itmIndex = 0; itmIndex<itmSize-1; itmIndex++){
			
			
			putHigherOb = optionBeanMap.get(itmList.get(itmIndex)+"PE");
			putLowerOb = optionBeanMap.get(itmList.get(itmIndex+1)+"PE");
			if(putHigherOb.getBidPrice()==0.0 ||putLowerOb.getAskPrice()==0.0 )  // To skip strike price which is ill liquid 
				continue;
			
			poDtoForHigherput = populatePositionDetailDto("S", sysDto.getPricingType(), putHigherOb, sysDto.getLotSize(), 1);
			poDtoFoLowerput= populatePositionDetailDto("B", sysDto.getPricingType(), putLowerOb, sysDto.getLotSize(), 1);
			
			for(int otmIndex = 0; otmIndex<otmSize-1; otmIndex++){
				
				callLowerOb = optionBeanMap.get(otmList.get(otmIndex)+"CE");
				callHigherOb = optionBeanMap.get(otmList.get(otmIndex+1) +"CE");
				if(callLowerOb.getBidPrice()==0.0 || callHigherOb.getAskPrice()==0.0){
					continue;
				}
				
				poDtoFoLowerCall= populatePositionDetailDto("S", sysDto.getPricingType(), callLowerOb, sysDto.getLotSize(), 1);
				poDtoForHigherCall = populatePositionDetailDto("B", sysDto.getPricingType(), callHigherOb, sysDto.getLotSize(), 1);
				
				positionRequestFormDto = new PositionRequestFormDto();
				positionRequestFormDto.getPositionList().add(poDtoForHigherput);
				positionRequestFormDto.getPositionList().add(poDtoFoLowerput);
				positionRequestFormDto.getPositionList().add(poDtoFoLowerCall);
				positionRequestFormDto.getPositionList().add(poDtoForHigherCall);
				poRequestList.add(positionRequestFormDto);
				
			}
		}
		
		String position ="";
		
		for(PositionRequestFormDto pDto : poRequestList){
			position ="";
			PayOffResponseDto payOffDto = new PayOffResponseDto();
			CalculationUtility.getPayOffCalculation(pDto.getPositionList(), payOffDto);
			sysResponseDto = new SystemStrategyResponseFormDto();
			sysResponseDto.setMaxLoss(payOffDto.getMaxLoss());
			sysResponseDto.setMaxProfit(payOffDto.getMaxProfit());
			for(PositionDetailDto po : pDto.getPositionList()){
				position= position+CalculationUtility.createPositionName(po) + " ";
			}
			sysResponseDto.setPosition(position);
			sysResponseDto.setStrategy("Iron Condor");
			responseList.add(sysResponseDto);
		}
		
		return responseList;
		
	}

	public static List<SystemStrategyResponseFormDto> ironButterfly(SystemStrategyRequestFormDto sysDto, List<Double> itmList, List<Double> otmList, 
			Double atm, Map<String, OptionBean> optionBeanMap){
		
		List<PositionRequestFormDto> poRequestList = new ArrayList<>();
		PositionRequestFormDto positionRequestFormDto = null;
		PositionDetailDto poDto = null;
		PositionDetailDto poDtoForHigherput = null;
		PositionDetailDto poDtoFoLowerput = null;
		PositionDetailDto poDtoForHigherCall = null;
		PositionDetailDto poDtoFoLowerCall = null;
		
		OptionBean putHigherOb =  null;
		OptionBean putLowerOb =  null;
		OptionBean callHigherOb =  null;
		OptionBean callLowerOb =  null;
		List<SystemStrategyResponseFormDto> responseList = new ArrayList<>();
		SystemStrategyResponseFormDto sysResponseDto= new SystemStrategyResponseFormDto();
		int itmSize = itmList.size();
		int otmSize = otmList.size();
		int itmIndex = 0;
		
		
		putHigherOb = optionBeanMap.get(itmList.get(itmIndex)+"PE");
		putLowerOb = optionBeanMap.get(itmList.get(itmIndex+1)+"PE");
		poDtoForHigherput = populatePositionDetailDto("S", sysDto.getPricingType(), putHigherOb, sysDto.getLotSize(), 1);
		poDtoFoLowerput= populatePositionDetailDto("B", sysDto.getPricingType(), putLowerOb, sysDto.getLotSize(), 1);
		
		callLowerOb = optionBeanMap.get(itmList.get(itmIndex)+"CE"); // Picking from itmList not otmList
		callHigherOb = optionBeanMap.get(otmList.get(itmIndex) +"CE");
		poDtoFoLowerCall= populatePositionDetailDto("S", sysDto.getPricingType(), callLowerOb, sysDto.getLotSize(), 1);
		poDtoForHigherCall = populatePositionDetailDto("B", sysDto.getPricingType(), callHigherOb, sysDto.getLotSize(), 1);
		
		if(!(callLowerOb.getBidPrice()==0.0 || callHigherOb.getAskPrice()==0.0 || putHigherOb.getBidPrice()==0.0 ||putLowerOb.getAskPrice()==0.0)){
			positionRequestFormDto = new PositionRequestFormDto();
			positionRequestFormDto.getPositionList().add(poDtoForHigherput);
			positionRequestFormDto.getPositionList().add(poDtoFoLowerput);
			positionRequestFormDto.getPositionList().add(poDtoFoLowerCall);
			positionRequestFormDto.getPositionList().add(poDtoForHigherCall);
			poRequestList.add(positionRequestFormDto);
		}
		
		// Adding second ironfly
		putHigherOb = optionBeanMap.get(otmList.get(itmIndex)+"PE");
		putLowerOb = optionBeanMap.get(itmList.get(itmIndex)+"PE");
		poDtoForHigherput = populatePositionDetailDto("S", sysDto.getPricingType(), putHigherOb, sysDto.getLotSize(), 1);
		poDtoFoLowerput= populatePositionDetailDto("B", sysDto.getPricingType(), putLowerOb, sysDto.getLotSize(), 1);
		
		callLowerOb = optionBeanMap.get(otmList.get(itmIndex)+"CE"); // Picking from itmList not otmList
		callHigherOb = optionBeanMap.get(otmList.get(itmIndex+1) +"CE");
		poDtoFoLowerCall= populatePositionDetailDto("S", sysDto.getPricingType(), callLowerOb, sysDto.getLotSize(), 1);
		poDtoForHigherCall = populatePositionDetailDto("B", sysDto.getPricingType(), callHigherOb, sysDto.getLotSize(), 1);
		
		if(!(callLowerOb.getBidPrice()==0.0 || callHigherOb.getAskPrice()==0.0 || putHigherOb.getBidPrice()==0.0 ||putLowerOb.getAskPrice()==0.0)){
			positionRequestFormDto = new PositionRequestFormDto();
			positionRequestFormDto.getPositionList().add(poDtoForHigherput);
			positionRequestFormDto.getPositionList().add(poDtoFoLowerput);
			positionRequestFormDto.getPositionList().add(poDtoFoLowerCall);
			positionRequestFormDto.getPositionList().add(poDtoForHigherCall);
			poRequestList.add(positionRequestFormDto);
		}
		
		
		String position ="";
		
		for(PositionRequestFormDto pDto : poRequestList){
			position ="";
			PayOffResponseDto payOffDto = new PayOffResponseDto();
			CalculationUtility.getPayOffCalculation(pDto.getPositionList(), payOffDto);
			sysResponseDto = new SystemStrategyResponseFormDto();
			sysResponseDto.setMaxLoss(payOffDto.getMaxLoss());
			sysResponseDto.setMaxProfit(payOffDto.getMaxProfit());
			for(PositionDetailDto po : pDto.getPositionList()){
				position= position+CalculationUtility.createPositionName(po) + " ";
			}
			sysResponseDto.setPosition(position);
			sysResponseDto.setStrategy("Iron Butterfly");
			responseList.add(sysResponseDto);
		}
		
		return responseList;
		
	}
	
	public static List<SystemStrategyResponseFormDto> butterfly(SystemStrategyRequestFormDto sysDto, List<Double> itmList, List<Double> otmList, 
			Double atm, Map<String, OptionBean> optionBeanMap){
		
		List<PositionRequestFormDto> poRequestList = new ArrayList<>();
		PositionRequestFormDto positionRequestFormDto = null;
		PositionDetailDto poDto = null;
		PositionDetailDto poDtoForHigherCall = null;
		PositionDetailDto poDtoFoLowerCall = null;
		PositionDetailDto poDtoForAtmCall = null;
		
		OptionBean callAtmOb =  null;
		OptionBean callHigherOb =  null;
		OptionBean callLowerOb =  null;
		List<SystemStrategyResponseFormDto> responseList = new ArrayList<>();
		SystemStrategyResponseFormDto sysResponseDto= new SystemStrategyResponseFormDto();
		int itmIndex = 0;
		
		
		callAtmOb = optionBeanMap.get(itmList.get(itmIndex)+"CE");
		callLowerOb = optionBeanMap.get(itmList.get(itmIndex+1)+"CE");
		callHigherOb = optionBeanMap.get(otmList.get(itmIndex)+"CE");
		
		poDtoForAtmCall = populatePositionDetailDto("S", sysDto.getPricingType(), callAtmOb, sysDto.getLotSize(), 2);
		poDtoFoLowerCall= populatePositionDetailDto("B", sysDto.getPricingType(), callLowerOb, sysDto.getLotSize(), 1);
		poDtoForHigherCall = populatePositionDetailDto("B", sysDto.getPricingType(), callHigherOb, sysDto.getLotSize(), 1);
		
		if(!(callLowerOb.getAskPrice()==0.0 || callHigherOb.getAskPrice()==0.0 || callAtmOb.getBidPrice()==0.0)){
			positionRequestFormDto = new PositionRequestFormDto();
			positionRequestFormDto.getPositionList().add(poDtoForAtmCall);
			positionRequestFormDto.getPositionList().add(poDtoFoLowerCall);
			positionRequestFormDto.getPositionList().add(poDtoForHigherCall);
			poRequestList.add(positionRequestFormDto);
		}
		
		// Adding second ironfly
		callAtmOb = optionBeanMap.get(otmList.get(itmIndex)+"CE");
		callLowerOb = optionBeanMap.get(itmList.get(itmIndex)+"CE");
		callHigherOb = optionBeanMap.get(otmList.get(itmIndex+1)+"CE");
		
		poDtoForAtmCall = populatePositionDetailDto("S", sysDto.getPricingType(), callAtmOb, sysDto.getLotSize(), 2);
		poDtoFoLowerCall= populatePositionDetailDto("B", sysDto.getPricingType(), callLowerOb, sysDto.getLotSize(), 1);
		poDtoForHigherCall = populatePositionDetailDto("B", sysDto.getPricingType(), callHigherOb, sysDto.getLotSize(), 1);
		
		if(!(callLowerOb.getAskPrice()==0.0 || callHigherOb.getAskPrice()==0.0 || callAtmOb.getBidPrice()==0.0)){
			positionRequestFormDto = new PositionRequestFormDto();
			positionRequestFormDto.getPositionList().add(poDtoForAtmCall);
			positionRequestFormDto.getPositionList().add(poDtoFoLowerCall);
			positionRequestFormDto.getPositionList().add(poDtoForHigherCall);
			poRequestList.add(positionRequestFormDto);
		}
		
		
		String position ="";
		
		for(PositionRequestFormDto pDto : poRequestList){
			position ="";
			PayOffResponseDto payOffDto = new PayOffResponseDto();
			CalculationUtility.getPayOffCalculation(pDto.getPositionList(), payOffDto);
			sysResponseDto = new SystemStrategyResponseFormDto();
			sysResponseDto.setMaxLoss(payOffDto.getMaxLoss());
			sysResponseDto.setMaxProfit(payOffDto.getMaxProfit());
			for(PositionDetailDto po : pDto.getPositionList()){
				position= position+CalculationUtility.createPositionName(po) + " ";
			}
			sysResponseDto.setPosition(position);
			sysResponseDto.setStrategy("Iron Butterfly");
			responseList.add(sysResponseDto);
		}
		
		return responseList;
		
	}
	
	public static List<SystemStrategyResponseFormDto> shortStrangle(SystemStrategyRequestFormDto sysDto, List<Double> itmList, List<Double> otmList, 
			Double atm, Map<String, OptionBean> optionBeanMap){
		
		List<PositionRequestFormDto> poRequestList = new ArrayList<>();
		PositionRequestFormDto positionRequestFormDto = new PositionRequestFormDto();
		PositionDetailDto poDto = null;
		PositionDetailDto poDtoForPE = null;
		OptionBean putOb =  null;
		OptionBean callOb =  null;
		List<SystemStrategyResponseFormDto> responseList = new ArrayList<>();
		SystemStrategyResponseFormDto sysResponseDto= new SystemStrategyResponseFormDto();
		
		for(Double itmSp : itmList){
			putOb = optionBeanMap.get(itmSp+"PE");
			if(putOb.getBidPrice()==0.0)  // To skip strike price which is ill liquid 
				continue;
			poDtoForPE = populatePositionDetailDto("S", sysDto.getPricingType(), putOb, sysDto.getLotSize(), 1);
			for(Double otmSp : otmList){
				
				positionRequestFormDto = new PositionRequestFormDto();
				
				callOb = optionBeanMap.get(otmSp+"CE");
				if(callOb.getBidPrice()==0.0)  // To skip strike price which is ill liquid 
					continue;
				
				poDto = populatePositionDetailDto("S", sysDto.getPricingType(), callOb, sysDto.getLotSize(), 1);
				positionRequestFormDto.getPositionList().add(poDto);
				positionRequestFormDto.getPositionList().add(poDtoForPE);
				poRequestList.add(positionRequestFormDto);
			}
		}
		
		String position ="";
		
		for(PositionRequestFormDto pDto : poRequestList){
			position ="";
			PayOffResponseDto payOffDto = new PayOffResponseDto();
			CalculationUtility.getPayOffCalculation(pDto.getPositionList(), payOffDto);
			sysResponseDto = new SystemStrategyResponseFormDto();
			sysResponseDto.setMaxLoss(payOffDto.getMaxLoss());
			sysResponseDto.setMaxProfit(payOffDto.getMaxProfit());
			for(PositionDetailDto po : pDto.getPositionList()){
				position= position+CalculationUtility.createPositionName(po) + " ";
			}
			sysResponseDto.setPosition(position);
			sysResponseDto.setStrategy("Short Strangle");
			responseList.add(sysResponseDto);
		}
		
		return responseList;
		
	}

	
	private static PositionDetailDto populatePositionDetailDto(String action, String priceType, OptionBean callOb, Integer lotSize, Integer lotQty) {
		PositionDetailDto poDto = new PositionDetailDto();
		poDto.setAction(action);
		if(priceType.equals("LTP"))
			poDto.setEntryPrice(callOb.getLtp());
		else if (!priceType.equals("LTP") && action.equals("S") ) 
			poDto.setEntryPrice(callOb.getBidPrice());
		else if (!priceType.equals("LTP") && action.equals("B") ) 
			poDto.setEntryPrice(callOb.getAskPrice());
		
		poDto.setLotSize(lotSize);
		poDto.setLotQty(lotQty);
		poDto.setExpiryDate(callOb.getSeriesName());
		poDto.setInstrumentType("O");
		poDto.setIv(callOb.getIv());
		poDto.setOptionType(callOb.getOptionType());
		poDto.setStrikePrice(callOb.getStrikePrice());
		return poDto;
	}
}
