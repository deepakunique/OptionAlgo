package com.optionAlgo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Embeddable
public class FutureScrip implements Serializable {

	
	@Id
	private String scripName;
	
	@Id
	private String expiryDate;
	
	private double low;
	private  double high;
	
	private double spotPrice;
	private double  sd;
	private double  iv;
	private double  ivp;
	private double  lotsize;
	private Date lastUpdate;
	private double  bid;
	private double  ask;
	private double  ltp;
	private double  annualVolatility;
	private double  dailyVolatility;
	private double mwpl;
	private double percentChange;
	private double changeinOI;
	private double oi;
	private double open;
	private double closePrice;
	private double prevClose;
    private Date lastUpdateTime;
    private double percentChangeInOI;
    private double changeInPrice;
    
    private double marginLimit;
    
	
	public String getScripName() {
		return scripName;
	}
	public void setScripName(String scripName) {
		this.scripName = scripName;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public double getSpotPrice() {
		return spotPrice;
	}
	public void setSpotPrice(double spotPrice) {
		this.spotPrice = spotPrice;
	}
	public double getSd() {
		return sd;
	}
	public void setSd(double sd) {
		this.sd = sd;
	}
	public double getIv() {
		return iv;
	}
	public void setIv(double iv) {
		this.iv = iv;
	}
	public double getIvp() {
		return ivp;
	}
	public void setIvp(double ivp) {
		this.ivp = ivp;
	}
	public double getLotsize() {
		return lotsize;
	}
	public void setLotsize(double lotsize) {
		this.lotsize = lotsize;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public double getBid() {
		return bid;
	}
	public void setBid(double bid) {
		this.bid = bid;
	}
	public double getAsk() {
		return ask;
	}
	public void setAsk(double ask) {
		this.ask = ask;
	}
	public double getLtp() {
		return ltp;
	}
	public void setLtp(double ltp) {
		this.ltp = ltp;
	}
	public double getAnnualVolatility() {
		return annualVolatility;
	}
	public void setAnnualVolatility(double annualVolatility) {
		this.annualVolatility = annualVolatility;
	}
	public double getDailyVolatility() {
		return dailyVolatility;
	}
	public void setDailyVolatility(double dailyVolatility) {
		this.dailyVolatility = dailyVolatility;
	}
	public double getMwpl() {
		return mwpl;
	}
	public void setMwpl(double mwpl) {
		this.mwpl = mwpl;
	}
	public double getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(double percentChange) {
		this.percentChange = percentChange;
	}
	public double getChangeinOI() {
		return changeinOI;
	}
	public void setChangeinOI(double changeinOI) {
		this.changeinOI = changeinOI;
	}
	public double getOi() {
		return oi;
	}
	public void setOi(double oi) {
		this.oi = oi;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getClose() {
		return closePrice;
	}
	public void setClose(double close) {
		this.closePrice = close;
	}
	public double getPrevClose() {
		return prevClose;
	}
	public void setPrevClose(double prevClose) {
		this.prevClose = prevClose;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public double getPercentChangeInOI() {
		return percentChangeInOI;
	}
	public void setPercentChangeInOI(double percentChangeInOI) {
		this.percentChangeInOI = percentChangeInOI;
	}
	public double getChange() {
		return changeInPrice;
	}
	public void setChange(double change) {
		this.changeInPrice = change;
	}
	public double getMarginLimit() {
		return marginLimit;
	}
	public void setMarginLimit(double marginLimit) {
		this.marginLimit = marginLimit;
	}
    
    @Override
    public boolean equals(Object obj) {
    	// TODO Auto-generated method stub
    	
    	if (this == obj){
    		return true;
    	}
    	if(!(obj instanceof FutureScrip)) {
    		return false;
    	}
    	FutureScrip fs = (FutureScrip) obj;

    	return  fs.getScripName().equals(getScripName()) &&  fs.getExpiryDate().equals(getExpiryDate());
    }
    
    @Override
    public int hashCode() {
    	// TODO Auto-generated method stub
    	return Objects.hash(getScripName(),getExpiryDate());
    }
	
	
	
	
	
	
	
}
