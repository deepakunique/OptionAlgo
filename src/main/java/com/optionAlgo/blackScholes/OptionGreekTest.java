package com.optionAlgo.blackScholes;

public class OptionGreekTest {
	
	public static void main(String[] args) {
		
		 
		
		BlackScholesFormula bc = new BlackScholesFormula();
		
		OptionDetails req = new OptionDetails(true, 117.70, 120, 10/100.0, 26.0/365, 0.4345);
		req = bc.calculateWithGreeks(req);
		System.out.println(req);
		
		
		
	}
}
