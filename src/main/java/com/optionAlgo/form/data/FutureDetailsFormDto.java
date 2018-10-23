package com.optionAlgo.form.data;

import java.util.LinkedHashMap;

import lombok.Data;


@Data
public class FutureDetailsFormDto {
	
	
	String expirdate;
	Double spotPrice;
	Double futurePrice;
	Integer lotSize;
	Double iv;
	Double ivp;
	Double changeInOi;
	LinkedHashMap<String,OptionPriceFormDto > optionPricesMap;
	public String getExpirdate() {
		return expirdate;
	}
	public void setExpirdate(String expirdate) {
		this.expirdate = expirdate;
	}
	public Double getSpotPrice() {
		return spotPrice;
	}
	public void setSpotPrice(Double spotPrice) {
		this.spotPrice = spotPrice;
	}
	public Double getFuturePrice() {
		return futurePrice;
	}
	public void setFuturePrice(Double futurePrice) {
		this.futurePrice = futurePrice;
	}
	public Integer getLotSize() {
		return lotSize;
	}
	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}
	public Double getIv() {
		return iv;
	}
	public void setIv(Double iv) {
		this.iv = iv;
	}
	public Double getIvp() {
		return ivp;
	}
	public void setIvp(Double ivp) {
		this.ivp = ivp;
	}
	public Double getChangeInOi() {
		return changeInOi;
	}
	public void setChangeInOi(Double changeInOi) {
		this.changeInOi = changeInOi;
	}
	public LinkedHashMap<String, OptionPriceFormDto> getOptionPricesMap() {
		return optionPricesMap;
	}
	public void setOptionPricesMap(LinkedHashMap<String, OptionPriceFormDto> optionPricesMap) {
		this.optionPricesMap = optionPricesMap;
	}
	
	

}
