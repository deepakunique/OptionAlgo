package com.optionAlgo.form.data;

public class PositionDetailDto {
	
	
	String instrumentType;
	String action;
	Double entryPrice;
	Double exitPrice;
	Integer lotSize;
	Double strikePrice;
	String expiryDate;
	String optionType;
	Integer lotQty;
	Double spotPrice;
	Double iv;
	
	
	
	
	public Double getIv() {
		return iv;
	}
	public void setIv(Double iv) {
		this.iv = iv;
	}
	public Double getSpotPrice() {
		return spotPrice;
	}
	public void setSpotPrice(Double spotPrice) {
		this.spotPrice = spotPrice;
	}
	public Integer getLotQty() {
		return lotQty;
	}
	public void setLotQty(Integer lotQty) {
		this.lotQty = lotQty;
	}
	public String getInstrumentType() {
		return instrumentType;
	}
	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Double getEntryPrice() {
		return entryPrice;
	}
	public void setEntryPrice(Double entryPrice) {
		this.entryPrice = entryPrice;
	}
	public Double getExitPrice() {
		return exitPrice;
	}
	public void setExitPrice(Double exitPrice) {
		this.exitPrice = exitPrice;
	}
	public Integer getLotSize() {
		return lotSize;
	}
	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}
	
	public Double getStrikePrice() {
		return strikePrice;
	}
	public void setStrikePrice(Double strikePrice) {
		this.strikePrice = strikePrice;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	
	
}
