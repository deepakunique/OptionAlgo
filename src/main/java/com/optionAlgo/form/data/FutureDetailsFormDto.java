package com.optionAlgo.form.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class FutureDetailsFormDto {
	
	
	String expiryDate;
	Double spotPrice;
	Double futurePrice;
	Integer lotSize;
	Double iv;
	Double ivp;
	Double changeInOi;
	List<OptionPriceFormDto> optionPricesList = new ArrayList<>();
	
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
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
	public List<OptionPriceFormDto> getOptionPricesList() {
		return optionPricesList;
	}
	public void setOptionPricesList(List<OptionPriceFormDto> optionPricesList) {
		this.optionPricesList = optionPricesList;
	}
	
	
	

}
