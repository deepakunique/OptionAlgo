package com.optionAlgo.form.data;

public class PositionPnlDto {
	
	String position;
	Double entryPrice;
	Double exitPrice;
	Double currentPrice;
	Double pnl;
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Double getPnl() {
		return pnl;
	}
	public void setPnl(Double pnl) {
		this.pnl = pnl;
	}
	
	
	
}
