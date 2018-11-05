package com.optionAlgo.utility;

public class SystemStrategyResponseFormDto {
	
	String strategy;
	String position;
	Double pop;
	Double maxROC;
	String maxProfit;
	String maxLoss;
	Double margin;
	String payoffLink;
	
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Double getPop() {
		return pop;
	}
	public void setPop(Double pop) {
		this.pop = pop;
	}
	public Double getMaxROC() {
		return maxROC;
	}
	public void setMaxROC(Double maxROC) {
		this.maxROC = maxROC;
	}
	
	public String getMaxProfit() {
		return maxProfit;
	}
	public void setMaxProfit(String maxProfit) {
		this.maxProfit = maxProfit;
	}
	public String getMaxLoss() {
		return maxLoss;
	}
	public void setMaxLoss(String maxLoss) {
		this.maxLoss = maxLoss;
	}
	public Double getMargin() {
		return margin;
	}
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	public String getPayoffLink() {
		return payoffLink;
	}
	public void setPayoffLink(String payoffLink) {
		this.payoffLink = payoffLink;
	}
	
	

}
