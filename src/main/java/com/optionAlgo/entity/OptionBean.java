package com.optionAlgo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OptionBean {
	
	
	@Id
	@GeneratedValue
	int id;
	
	Double oi;
	Double ChangeInOi	;
	Double volume;
	Double iv;
	Double ltp;
	Double netChng;
	Double bidQty;
	Double bidPrice;
	Double askPrice;
	Double askQty;
	Double strikePrice;
	Double cmp;
	
	String scripName;
	String seriesName; 
	String optionType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getOi() {
		return oi;
	}
	public void setOi(Double oi) {
		this.oi = oi;
	}
	public Double getChangeInOi() {
		return ChangeInOi;
	}
	public void setChangeInOi(Double changeInOi) {
		ChangeInOi = changeInOi;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Double getIv() {
		return iv;
	}
	public void setIv(Double iv) {
		this.iv = iv;
	}
	public Double getLtp() {
		return ltp;
	}
	public void setLtp(Double ltp) {
		this.ltp = ltp;
	}
	public Double getNetChng() {
		return netChng;
	}
	public void setNetChng(Double netChng) {
		this.netChng = netChng;
	}
	public Double getBidQty() {
		return bidQty;
	}
	public void setBidQty(Double bidQty) {
		this.bidQty = bidQty;
	}
	public Double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Double getAskPrice() {
		return askPrice;
	}
	public void setAskPrice(Double askPrice) {
		this.askPrice = askPrice;
	}
	public Double getAskQty() {
		return askQty;
	}
	public void setAskQty(Double askQty) {
		this.askQty = askQty;
	}
	public Double getStrikePrice() {
		return strikePrice;
	}
	public void setStrikePrice(Double strikePrice) {
		this.strikePrice = strikePrice;
	}
	public Double getCmp() {
		return cmp;
	}
	public void setCmp(Double cmp) {
		this.cmp = cmp;
	}
	public String getScripName() {
		return scripName;
	}
	public void setScripName(String scripName) {
		this.scripName = scripName;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	
		
	

}
