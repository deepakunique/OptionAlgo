package com.optionAlgo.form.data;

import java.util.ArrayList;
import java.util.List;

public class PayOffResponseDto {
	
	String maxProfit;
	String maxLoss;
	String breakEven;
	String pnl;
	List<PositionGreekDto> greekDto = new ArrayList<>();
	Double spotPrice;
	Double iv;
	List<PositionPnlDto> pnlDto = new ArrayList<>();
	List<PayOffChartDto> chartDto= new ArrayList<>();
	
	
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
	public String getBreakEven() {
		return breakEven;
	}
	public void setBreakEven(String breakEven) {
		this.breakEven = breakEven;
	}
	public String getPnl() {
		return pnl;
	}
	public void setPnl(String pnl) {
		this.pnl = pnl;
	}
	
	public List<PositionGreekDto> getGreekDto() {
		return greekDto;
	}
	public void setGreekDto(List<PositionGreekDto> greekDto) {
		this.greekDto = greekDto;
	}
	public List<PositionPnlDto> getPnlDto() {
		return pnlDto;
	}
	public void setPnlDto(List<PositionPnlDto> pnlDto) {
		this.pnlDto = pnlDto;
	}
	public Double getSpotPrice() {
		return spotPrice;
	}
	public void setSpotPrice(Double spotPrice) {
		this.spotPrice = spotPrice;
	}
	public Double getIv() {
		return iv;
	}
	public void setIv(Double iv) {
		this.iv = iv;
	}
	public List<PayOffChartDto> getChartDto() {
		return chartDto;
	}
	public void setChartDto(List<PayOffChartDto> chartDto) {
		this.chartDto = chartDto;
	}
	
	

}
