package com.optionAlgo.blackScholes;

public class OptionGreeks {
	
	 public double delta;
	 public double theta;
	 public double rho;
	 public double gamma =0.0001;
	 public double vega;
	 
	 public String toString() {
		 
		 String out = "delta-[" + delta + "] theta-[" + theta;
		 out += "] rho-[" + rho + "] gamma-[" + gamma;
		 out += "] vega-[" + vega;
		 
		 return out;
	 }

}
