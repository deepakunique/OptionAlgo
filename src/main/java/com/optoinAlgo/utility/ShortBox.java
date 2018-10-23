package com.optoinAlgo.utility;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShortBox {

	@Id
	@GeneratedValue
	private int id;
	

	private double itmCallStrikePrice;
	private double otmCallStrikePrice;
	private String scripName;
	private String desciption;
	private double shortBoxReturn;
	private double cmp;
	private double strikePriceDifference;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	

	public double getItmCallStrikePrice() {
		return itmCallStrikePrice;
	}
	public void setItmCallStrikePrice(double itmCallStrikePrice) {
		this.itmCallStrikePrice = itmCallStrikePrice;
	}
	public double getOtmCallStrikePrice() {
		return otmCallStrikePrice;
	}
	public void setOtmCallStrikePrice(double otmCallStrikePrice) {
		this.otmCallStrikePrice = otmCallStrikePrice;
	}
	public String getScripName() {
		return scripName;
	}
	public void setScripName(String scripName) {
		this.scripName = scripName;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	

	public double getShortBoxReturn() {
		return shortBoxReturn;
	}
	public void setShortBoxReturn(double shortBoxReturn) {
		this.shortBoxReturn = shortBoxReturn;
	}
	public double getCmp() {
		return cmp;
	}
	public void setCmp(double cmp) {
		this.cmp = cmp;
	}
	public double getStrikePriceDifference() {
		return strikePriceDifference;
	}
	public void setStrikePriceDifference(double strikePriceDifference) {
		this.strikePriceDifference = strikePriceDifference;
	}
	
	
	
	
}
