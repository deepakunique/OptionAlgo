package com.optionAlgo.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Embeddable
public class OptionBean implements Serializable {
	

	
	double oi;
	double ChangeInOi	;
	double volume;
	double iv;
	double ltp;
	double netChng;
	double bidQty;
	double bidPrice;
	double askPrice;
	double askQty;
	
	@Id
	double strikePrice;
	
	double cmp;
	
	@Id
	String scripName;
	
	@Id
	String seriesName; 
	
	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	
	@Id
	String optionType;

	public double getOi() {
		return oi;
	}
	
	public double getCmp() {
		return cmp;
	}

	public void setCmp(double cmp) {
		this.cmp = cmp;
	}



	public void setOi(double oi) {
		this.oi = oi;
	}

	public double getChangeInOi() {
		return ChangeInOi;
	}

	public void setChangeInOi(double changeInOi) {
		ChangeInOi = changeInOi;
	}
	
	public String getScripName() {
		return scripName;
	}

	public void setScripName(String scripName) {
		this.scripName = scripName;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getIv() {
		return iv;
	}

	public void setIv(double iv) {
		this.iv = iv;
	}

	public double getLtp() {
		return ltp;
	}

	public void setLtp(double ltp) {
		this.ltp = ltp;
	}

	public double getNetChng() {
		return netChng;
	}

	public void setNetChng(double netChng) {
		this.netChng = netChng;
	}

	public double getBidQty() {
		return bidQty;
	}

	public void setBidQty(double bidQty) {
		this.bidQty = bidQty;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}

	public double getAskQty() {
		return askQty;
	}

	public void setAskQty(double askQty) {
		this.askQty = askQty;
	}

	public double getStrikePrice() {
		return strikePrice;
	}

	public void setStrikePrice(double strikePrice) {
		this.strikePrice = strikePrice;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	
	/*bidQty
	bidPrice
	askPrice
	askQty
	netChng
	LTP	
	IV
	Volume
	ChnginOI
	OI*/
	
	@Override
    public boolean equals(Object obj) {
    	// TODO Auto-generated method stub
    	
    	if (this == obj){
    		return true;
    	}
    	if(!(obj instanceof OptionBean)) {
    		return false;
    	}
    	OptionBean fs = (OptionBean) obj;

    	return  fs.getScripName().equals(getScripName()) &&  fs.getSeriesName().equals(getSeriesName()) 
    			&& fs.getOptionType().equals(getOptionType()) && (fs.getStrikePrice() == getStrikePrice());
    }
    
    @Override
    public int hashCode() {
    	// TODO Auto-generated method stub
    	return Objects.hash(getScripName(),getSeriesName(), getOptionType(),getStrikePrice());
    }

}
