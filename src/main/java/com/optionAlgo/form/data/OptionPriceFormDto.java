package com.optionAlgo.form.data;

import java.util.HashMap;

import lombok.Data;

@Data
public class OptionPriceFormDto {

	
	Double strike;
	HashMap<String, OptionPriceDetailFormDto> optionTypePriceMap = new HashMap<>();
	
	public Double getStrike() {
		return strike;
	}
	public void setStrike(Double strike) {
		this.strike = strike;
	}
	public HashMap<String, OptionPriceDetailFormDto> getOptionTypePriceMap() {
		return optionTypePriceMap;
	}
	public void setOptionTypePriceMap(HashMap<String, OptionPriceDetailFormDto> optionTypePriceMap) {
		this.optionTypePriceMap = optionTypePriceMap;
	}
	
	
    		  
}
