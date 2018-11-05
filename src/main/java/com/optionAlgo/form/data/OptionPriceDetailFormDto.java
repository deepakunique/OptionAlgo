package com.optionAlgo.form.data;

import lombok.Data;

@Data
public class OptionPriceDetailFormDto {

	
	Double  price;
	Double iv;
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getIv() {
		return iv;
	}
	public void setIv(Double iv) {
		this.iv = iv;
	}
	
	
    		  
}
