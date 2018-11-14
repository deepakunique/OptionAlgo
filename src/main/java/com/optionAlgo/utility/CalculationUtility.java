package com.optionAlgo.utility;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.optionAlgo.blackScholes.BlackScholesFormula;
import com.optionAlgo.blackScholes.OptionDetails;
import com.optionAlgo.dao.MasterDao;
import com.optionAlgo.form.data.PayOffChartDto;
import com.optionAlgo.form.data.PayOffResponseDto;
import com.optionAlgo.form.data.PositionDetailDto;
import com.optionAlgo.form.data.PositionGreekDto;
import com.optionAlgo.form.data.PositionPnlDto;
import com.optionAlgo.form.data.PositionRequestFormDto;


public class CalculationUtility {
	@Autowired
	private MasterDao masterDao;
	
	
	public static void getPayOffCalculation(List<PositionDetailDto> positionList, PayOffResponseDto payOffDto){
		
		
		double totalExpense =0;
		double spread = 0;
		long totalCEcount = 0;
		long totalPEcount = 0;
		Collections.sort(positionList, new Comparator<PositionDetailDto>() {
			public int compare(PositionDetailDto o1, PositionDetailDto o2) {
				// TODO Auto-generated method stub
				return o1.getStrikePrice().compareTo(o2.getStrikePrice());
			}
		});
		PositionDetailDto cp = positionList.get(0);
		
		if(cp.getStrikePrice()<200)
			spread = 0.5;
		else if(cp.getStrikePrice()>200 && cp.getStrikePrice()<800)
			spread = 5;
		else if(cp.getStrikePrice()>800 && cp.getStrikePrice()<2500)
			spread = 10;
		else if(cp.getStrikePrice()> 2500)
			spread = 50;
		
		double checkStartPrice = positionList.get(0).getStrikePrice()-5*spread;
		double checkLastPrice = positionList.get(positionList.size()-1).getStrikePrice()+5*spread;
		double maxProfit =0;
		double maxLoss=0;
		double breakeven=0;
		
				
				
		
		
		for(double sp = checkStartPrice; sp <=checkLastPrice; sp=sp+spread){
			
			double profit=0;
			for(PositionDetailDto cpObj : positionList){
				if(cpObj.getInstrumentType().equals("O") &&  cpObj.getOptionType().equals("CE") && 
						cpObj.getAction().equals("B")) {
					if(sp -cpObj.getStrikePrice() > 0)
						profit = profit + (sp-cpObj.getStrikePrice() -cpObj.getEntryPrice())*cpObj.getLotQty()*cpObj.getLotSize();
					else
						profit = profit - cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
					
				}
				
				if(cpObj.getInstrumentType().equals("O") &&  cpObj.getOptionType().equals("CE") && 
						cpObj.getAction().equals("S")) {
					if(sp -cpObj.getStrikePrice() > 0)
						profit = profit - (sp-cpObj.getStrikePrice() +cpObj.getEntryPrice())*cpObj.getLotQty()*cpObj.getLotSize();
					else
						profit = profit + cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
					
				}
				
				if(cpObj.getInstrumentType().equals("O") &&  cpObj.getOptionType().equals("PE") && 
						cpObj.getAction().equals("B")) {
					if(cpObj.getStrikePrice() - sp > 0)
						profit = profit + (cpObj.getStrikePrice()-sp -cpObj.getEntryPrice())*cpObj.getLotQty()*cpObj.getLotSize();
					else
						profit = profit - cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
					
				}
				
				if(cpObj.getInstrumentType().equals("O") &&  cpObj.getOptionType().equals("PE") && 
						cpObj.getAction().equals("S")) {
					if(cpObj.getStrikePrice() -sp > 0)
						profit = profit - (cpObj.getStrikePrice()-sp -cpObj.getEntryPrice())*cpObj.getLotQty()*cpObj.getLotSize();
					else
						profit = profit + cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
					
				}
				
			}
			
			if(profit>maxProfit)
				maxProfit = profit;
			else if(profit < maxLoss)
				maxLoss= profit;
			
			if(profit ==0)
				breakeven = sp;
			
		}
		
		System.out.println("maxp ::" +maxProfit + "  maxloss:: "+ maxLoss);
		
		//Loop
		for(PositionDetailDto cpObj : positionList){
			if(cpObj.getInstrumentType().equals("O") && cpObj.getAction().equals("B")) {
				totalExpense = totalExpense - cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
				if(cpObj.getOptionType().equals("CE"))
						totalCEcount = totalCEcount + cpObj.getLotQty()* cpObj.getLotSize();
				else
						totalPEcount = totalPEcount + cpObj.getLotQty()* cpObj.getLotSize();
			}
				
			else if (cpObj.getInstrumentType().equals("O") && cpObj.getAction().equals("S")){
				totalExpense = totalExpense + cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
				if(cpObj.getOptionType().equals("CE"))
					totalCEcount = totalCEcount - cpObj.getLotQty()* cpObj.getLotSize();
				else
					totalPEcount = totalPEcount - cpObj.getLotQty()* cpObj.getLotSize();
			}
		
			if(cpObj.getInstrumentType().equals("F") && cpObj.getAction().equals("B")) {
						totalCEcount = totalCEcount + cpObj.getLotQty()* cpObj.getLotSize();
			}
			else if (cpObj.getInstrumentType().equals("F") && cpObj.getAction().equals("S")){
					totalPEcount = totalPEcount - cpObj.getLotQty()* cpObj.getLotSize();
			}
		}	
		
		payOffDto.setMaxLoss(maxLoss+"");
		payOffDto.setMaxProfit(maxProfit+"");
		
		if(totalCEcount>0 || totalPEcount >0)
			payOffDto.setMaxProfit("Un defined");

		if(totalCEcount<0 || totalPEcount <0)
			payOffDto.setMaxLoss("Un defined");
		
		
		
		System.out.println("maxp ::" +maxProfit + "  maxloss:: "+ maxLoss + "  P/L ::" + totalExpense + "  breakeven ::"+breakeven);
	
	}
	
	public static String getMonth(String month){
		
		switch(month){
		
		case "JAN" :
			return "01";
		case "FEB" :
			return "02";
		case "MAR" :
			return "03";
		case "APR" :
			return "04";
		case "MAY" :
			return "05";
		case "JUN" :
			return "06";
		case "JUL" :
			return "07";
		case "AUG" :
			return "08";
		case "SEP" :
			return "09";
		case "OCT" :
			return "10";
		case "NOV" :
			return "10";
		case "DEC" :
			return "12";
		}
		
		return null;
		
	}
	
	public static PositionGreekDto calaculateOptionGreek(PositionDetailDto po, String payOffDate){
		
		
		double diffDays = getDaysToExpire(po.getExpiryDate());
		  
		OptionDetails req = new OptionDetails(po.getOptionType(), po.getSpotPrice(), po.getStrikePrice(),
				AppConstant.FIXED_INTEREST_RATE/100.0, diffDays/365, po.getIv()*0.01);
		
		req= BlackScholesFormula.calculateWithGreeks(req);
		PositionGreekDto pGreek = new PositionGreekDto();
		pGreek.setDelta(req.getDelta());
		pGreek.setVega(req.getVega());
		pGreek.setTheta(req.getTheta());
		pGreek.setGamma(req.getGamma());
		pGreek.setIv(req.getDelta());
		
		return pGreek;
	}

	private static long getDaysToExpire(String expiryDate) {
		Calendar expirtCal = Calendar.getInstance();
		
		int day= Integer.parseInt(expiryDate.substring(0, 2));
		int month = Integer.parseInt(getMonth(expiryDate.substring(2, 5)));
		
		int year = Integer.parseInt(expiryDate.substring(5, 9));
		
		expirtCal.set(year, month, day);
		
		
		  Date currDate = new Date();
		  Calendar currCal = Calendar.getInstance();
		  //currCal.set(currDate.getYear(), currDate.getMonth(), currDate.getDate());
		  long milliseconds1 = currCal.getTimeInMillis();
		  long milliseconds2 = expirtCal.getTimeInMillis();
		  long diff = milliseconds2 - milliseconds1;
		  long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}
	
	
	
	
	/**
	 * @param positionDto
	 * @param spotPrice
	 * @param changePercentFrequency (It should be decimal (like 0.01, 0.05))
	 * @param percentLimit : It should be decimal (like 0.15)
	 */
	public static List<PayOffChartDto> calculatePayOffChartDataPoint(PositionRequestFormDto positionDto, Double spotPrice, 
			Double changePercentFrequency, Double percentLimit){
		//TODO Change the IV old value to latest IV to calculate the today's PNL based upon it
		List<PayOffChartDto>payOffChartDtoList = new ArrayList<>();
		for(double changePercent = -percentLimit; changePercent<=percentLimit; changePercent+=changePercentFrequency){
			
			Double underlyingPrice = spotPrice*(1-changePercent);
			Double pnlAtExpiry=0.0;
			Double pnlToday=0.0;
			PayOffChartDto chartDto = new PayOffChartDto();
			for(PositionDetailDto po : positionDto.getPositionList()){
				
				pnlAtExpiry = pnlAtExpiry + pnLforPositionListBySpotPrice(po,underlyingPrice);
				//For calculating today's pnl of contract based upon the fabricated underlying price 
				if(po.getInstrumentType().equals("F"))
					po.setExitPrice(underlyingPrice);
				else if(po.getInstrumentType().equals("O")){
					
					getDaysToExpire(po.getExpiryDate());
					OptionDetails req = new OptionDetails(po.getOptionType(), underlyingPrice, po.getStrikePrice(), 
							AppConstant.FIXED_INTEREST_RATE/100.0, getDaysToExpire(po.getExpiryDate())/365.0, po.getIv()*0.01);
					po.setExitPrice(BlackScholesFormula.calculateWithGreeks(req).getOptionValue());
				
				}
				
				pnlToday = pnlToday + pnLforPositionListBySpotPrice(po,underlyingPrice);
					
			}
			DecimalFormat df = new DecimalFormat("###.####");
			chartDto.setChangePercent(changePercent);
			chartDto.setCurrentPnL(pnlToday);
			chartDto.setFinalPnL(pnlAtExpiry);
			chartDto.setStockPrice(Double.parseDouble(df.format(underlyingPrice)));
			payOffChartDtoList.add(chartDto);
			
		}
			
			
			return payOffChartDtoList;
			
		}
		
		private static double pnLforPositionListBySpotPrice(PositionDetailDto po, Double underlyingPrice ){
			
			double pnl =0.0;
			if(po.getExitPrice()!=null){
				
				if(po.getAction().equals("B"))
					pnl = (po.getExitPrice() - po.getEntryPrice())*po.getLotQty()*po.getLotSize();
				else
					pnl = -(po.getExitPrice() - po.getEntryPrice())*po.getLotQty()*po.getLotSize();
			} else {
				
				if(po.getOptionType().equals("CE")){
					
					if(po.getAction().equals("B") && po.getStrikePrice()<underlyingPrice)
						pnl = (underlyingPrice -po.getStrikePrice() - po.getEntryPrice())*po.getLotQty()*po.getLotSize();
					else if (po.getAction().equals("B") && po.getStrikePrice()<=underlyingPrice)
						pnl = -po.getEntryPrice()*po.getLotQty()*po.getLotSize() ;
					
					if(po.getAction().equals("S") && po.getStrikePrice()<underlyingPrice)
						pnl = -(underlyingPrice -po.getStrikePrice() - po.getEntryPrice())*po.getLotQty()*po.getLotSize();
					else if (po.getAction().equals("S") && po.getStrikePrice()<=underlyingPrice)
						pnl = po.getEntryPrice()*po.getLotQty()*po.getLotSize() ;
					
				} else if(po.getOptionType().equals("PE")){
					
					if(po.getAction().equals("B") && po.getStrikePrice()>underlyingPrice)
						pnl = (po.getStrikePrice() -underlyingPrice  - po.getEntryPrice())*po.getLotQty()*po.getLotSize();
					else if (po.getAction().equals("B") && po.getStrikePrice()<=underlyingPrice)
						pnl = -po.getEntryPrice()*po.getLotQty()*po.getLotSize() ;
					
					if(po.getAction().equals("S") && po.getStrikePrice()>underlyingPrice)
						pnl = -(po.getStrikePrice() -underlyingPrice - po.getEntryPrice())*po.getLotQty()*po.getLotSize();
					else if (po.getAction().equals("S") && po.getStrikePrice()<=underlyingPrice)
						pnl = po.getEntryPrice()*po.getLotQty()*po.getLotSize() ;
					
				}
				
			}
		
			return pnl;
		
	}
	
	
	public static String createPositionName(PositionDetailDto po){
		
		StringBuffer positionName = new StringBuffer();
		if(po.getAction().equals("B"))
			positionName.append("+");
		else
			positionName.append("-");
		
		positionName.append(po.getLotQty()+"x "+ po.getExpiryDate()+" ");
		
		if(po.getInstrumentType().equals("F"))
			positionName.append("FUTURES");
		else
			positionName.append(po.getStrikePrice() + po.getOptionType());
		
		
		return positionName.toString();
	}
	
}
