package com.optionAlgo.blackScholes;
import java.util.Date;

/**
 * Data class used to manage the specific data items for one
 * option transaction.
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class OptionDetails {

	 Boolean callOption;
	 double spotPriceOfUnderlying;
	 double strikePrice;
	 double riskFreeInterestRate;
	 double timeToExpire;
	 double volatility;
	 Date valuationDate;
	 Date ExpDate;

	 Double optionValue;
	 Double delta;
	 Double theta;
	 Double rho;
	 Double gamma;
	 Double vega;
	 
	 
	 
	public Double getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(Double optionValue) {
		this.optionValue = optionValue;
	}

	public Double getDelta() {
		return delta;
	}

	public void setDelta(Double delta) {
		this.delta = delta;
	}

	public Double getTheta() {
		return theta;
	}

	public void setTheta(Double theta) {
		this.theta = theta;
	}

	public Double getRho() {
		return rho;
	}

	public void setRho(Double rho) {
		this.rho = rho;
	}

	public Double getGamma() {
		return gamma;
	}

	public void setGamma(Double gamma) {
		this.gamma = gamma;
	}

	public Double getVega() {
		return vega;
	}

	public void setVega(Double vega) {
		this.vega = vega;
	}

	public OptionDetails(Boolean call, double s, double k, double r, double t,
			double v) {

			callOption = call;
			spotPriceOfUnderlying = s;
			strikePrice = k;
			riskFreeInterestRate = r;
			timeToExpire = t;
			volatility = v;
	}
	
	
	
	public OptionDetails(String optionType, double s, double k, double r, double t,
			double v) {
			
			if(optionType.equals("CE"))
				callOption = true;
			else
				callOption = false;
			spotPriceOfUnderlying = s;
			strikePrice = k;
			riskFreeInterestRate = r;
			timeToExpire = t;
			volatility = v;
	}
	
	public String toString() {
		
		String out = "spot price [" + spotPriceOfUnderlying + "] strike [";
		out += strikePrice + "] int rate [" + riskFreeInterestRate + "] expire [";
		out += timeToExpire + "] vol [" + volatility + "] \n";
		
		out += "option value-[" + optionValue + "] delta-[" + delta + "] theta-[";
		out += theta + "] rho-[" + rho + "] gamma-[" + gamma + "] vega-[" + vega + "]";
		
		return out;
	}
}
