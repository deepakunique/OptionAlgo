package com.optoinAlgo.utility;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mysql.jdbc.Connection;

public class PayOffCalculation {
	
	public static void main(String[] args) {
		
		// "JAN","FEB", "MAR", "APR", "MAY" ,"JUN", "JUL","AUG","SEP","OCT","NOV","DEC"
		ClientPosition cp1 = new ClientPosition();
		cp1.setActionType("B");
		cp1.setEntryPrice(0.85);
		cp1.setExpiryDate("25OCT2018");
		cp1.setInstrumentType("Option");
		cp1.setLotQty(1);
		cp1.setLotSize(20000);
		cp1.setOptionType("CE");
		cp1.setStrikePrice(35.0);
		
		
		
		ClientPosition cp2 = new ClientPosition();
		cp2.setActionType("S");
		cp2.setEntryPrice(0.45);
		cp2.setExpiryDate("25OCT2018");
		cp2.setInstrumentType("Option");
		cp2.setLotQty(1);
		cp2.setLotSize(20000);
		cp2.setOptionType("CE");
		cp2.setStrikePrice(37.5);
		
		List<ClientPosition> cpList = new ArrayList<ClientPosition>();
		cpList.add(cp1);
		cpList.add(cp2);
		
		
		cp1 = new ClientPosition();
		cp1.setActionType("B");
		cp1.setEntryPrice(0.5);
		cp1.setExpiryDate("25OCT2018");
		cp1.setInstrumentType("Option");
		cp1.setLotQty(2);
		cp1.setLotSize(20000);
		cp1.setOptionType("PE");
		cp1.setStrikePrice(36.0);
		
		cp2 = new ClientPosition();
		cp2.setActionType("S");
		cp2.setEntryPrice(1.0);
		cp2.setExpiryDate("25OCT2018");
		cp2.setInstrumentType("Option");
		cp2.setLotQty(1);
		cp2.setLotSize(20000);
		cp2.setOptionType("PE");
		cp2.setStrikePrice(37.0);
		
		
		cpList.add(cp1);
		cpList.add(cp2);
		
		double totalExpense =0;
		
		
		double spread = 0;
		long totalCEcount = 0;
		long totalPEcount = 0;
		Collections.sort(cpList, new Comparator<ClientPosition>() {
			public int compare(ClientPosition o1, ClientPosition o2) {
				// TODO Auto-generated method stub
				return o1.getStrikePrice().compareTo(o2.getStrikePrice());
			}
		});
		ClientPosition cp = cpList.get(0);
		
		if(cp.getStrikePrice()<200)
			spread = 0.5;
		else if(cp.getStrikePrice()>200 && cp.getStrikePrice()<800)
			spread = 5;
		else if(cp.getStrikePrice()>800 && cp.getStrikePrice()<2500)
			spread = 10;
		else if(cp.getStrikePrice()> 2500)
			spread = 50;
		
		double checkStartPrice = cpList.get(0).getStrikePrice()-5*spread;
		double checkLastPrice = cpList.get(cpList.size()-1).getStrikePrice()+5*spread;
		double maxProfit =0;
		double maxLoss=0;
		double breakeven=0;
		
				
				
		
		
		for(double sp = checkStartPrice; sp <=checkLastPrice; sp=sp+spread){
			
			double profit=0;
			for(ClientPosition cpObj : cpList){
				if(cpObj.getInstrumentType().equals("Option") &&  cpObj.getOptionType().equals("CE") && 
						cpObj.getActionType().equals("B")) {
					if(sp -cpObj.getStrikePrice() > 0)
						profit = profit + (sp-cpObj.getStrikePrice() -cpObj.getEntryPrice())*cpObj.getLotQty()*cpObj.getLotSize();
					else
						profit = profit - cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
					
				}
				
				if(cpObj.getInstrumentType().equals("Option") &&  cpObj.getOptionType().equals("CE") && 
						cpObj.getActionType().equals("S")) {
					if(sp -cpObj.getStrikePrice() > 0)
						profit = profit - (sp-cpObj.getStrikePrice() +cpObj.getEntryPrice())*cpObj.getLotQty()*cpObj.getLotSize();
					else
						profit = profit + cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
					
				}
				
				if(cpObj.getInstrumentType().equals("Option") &&  cpObj.getOptionType().equals("PE") && 
						cpObj.getActionType().equals("B")) {
					if(cpObj.getStrikePrice() - sp > 0)
						profit = profit + (cpObj.getStrikePrice()-sp -cpObj.getEntryPrice())*cpObj.getLotQty()*cpObj.getLotSize();
					else
						profit = profit - cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
					
				}
				
				if(cpObj.getInstrumentType().equals("Option") &&  cpObj.getOptionType().equals("PE") && 
						cpObj.getActionType().equals("S")) {
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
		for(ClientPosition cpObj : cpList){
			if(cpObj.getInstrumentType().equals("Option") && cpObj.getActionType().equals("B")) {
				totalExpense = totalExpense - cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
				if(cpObj.getOptionType().equals("CE"))
						totalCEcount = totalCEcount + cpObj.getLotQty()* cpObj.getLotSize();
				else
						totalPEcount = totalPEcount + cpObj.getLotQty()* cpObj.getLotSize();
			}
				
			else if (cpObj.getInstrumentType().equals("Option") && cpObj.getActionType().equals("S")){
				totalExpense = totalExpense + cpObj.getEntryPrice()*cpObj.getLotQty()*cpObj.getLotSize();
				if(cpObj.getOptionType().equals("CE"))
					totalCEcount = totalCEcount - cpObj.getLotQty()* cpObj.getLotSize();
				else
					totalPEcount = totalPEcount - cpObj.getLotQty()* cpObj.getLotSize();
			}
		
			/*if(cpObj.getInstrumentType().equals("FUTURE") && cpObj.getActionType().equals("B")) {
						totalCEcount = totalCEcount + cpObj.getLotQty()* cpObj.getLotSize();
			}
			else if (cpObj.getInstrumentType().equals("FUTURE") && cpObj.getActionType().equals("S")){
					totalPEcount = totalPEcount - cpObj.getLotQty()* cpObj.getLotSize();
			}*/
		}	
		
		if(totalCEcount>0 || totalPEcount >0)
			maxProfit = 9999999.0;
			
		
		
		
		System.out.println("maxp ::" +maxProfit + "  maxloss:: "+ maxLoss + "  P/L ::" + totalExpense + "  breakeven ::"+breakeven);
		
		
	}
	
	
	/*static int  insertData(String file){
		
		int x = 0;
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hbtest", "root","root");
			
			Statement statement = connection.createStatement();
			 x = statement.executeUpdate("LOAD DATA INFILE '"+file+"' "
			 		+ "INTO TABLE HistoricalOptionData FIELDS TERMINATED BY ','  ENCLOSED BY '\"' LINES TERMINATED BY '\n' "
			 		+ "IGNORE 1 ROWS (INSTRUMENT,SYMBOL,@EXPIRY_DT,STRIKE_PR,OPTION_TYP,OPEN,HIGH,LOW,CLOSE,SETTLE_PR,CONTRACTS,VAL_INLAKH,OPEN_INT,CHG_IN_OI,@currentDate) "
			 		+ "SET EXPIRY_DT = STR_TO_DATE(@EXPIRY_DT, '%d-%b-%Y'), currentDate = STR_TO_DATE(@currentDate, '%d-%b-%Y');");
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		} finally{
			
		}
         // Setup the connection with the DB
		  
         // Statements allow to issue SQL queries to the database
		return x;
	}*/

}
