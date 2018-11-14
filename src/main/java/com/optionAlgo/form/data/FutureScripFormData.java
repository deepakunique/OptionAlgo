package com.optionAlgo.form.data;

import java.util.LinkedHashMap;

import lombok.Data;


@Data
public class FutureScripFormData {
	
	
	String scripName;
	LinkedHashMap<String, FutureDetailsFormDto> futureAllExpiryMap;
	Integer futureAllExpiryMapSize;
	
	
	
	
	
	public Integer getFutureAllExpiryMapSize() {
		return futureAllExpiryMapSize;
	}
	public void setFutureAllExpiryMapSize(Integer futureAllExpiryMapSize) {
		this.futureAllExpiryMapSize = futureAllExpiryMapSize;
	}
	public String getScripName() {
		return scripName;
	}
	public void setScripName(String scripName) {
		this.scripName = scripName;
	}
	public LinkedHashMap<String, FutureDetailsFormDto> getFutureAllExpiryMap() {
		return futureAllExpiryMap;
	}
	public void setFutureAllExpiryMap(LinkedHashMap<String, FutureDetailsFormDto> futureAllExpiryMap) {
		this.futureAllExpiryMap = futureAllExpiryMap;
	}
	
}
