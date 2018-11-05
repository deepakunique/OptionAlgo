package com.optionAlgo.form.data;

import java.util.ArrayList;
import java.util.List;

public class PositionRequestFormDto {

	String scripName;
	String payOffDate;
	
	List<PositionDetailDto> positionList = new ArrayList<>();

	public String getScripName() {
		return scripName;
	}

	public void setScripName(String scripName) {
		this.scripName = scripName;
	}

	public String getPayOffDate() {
		return payOffDate;
	}

	public void setPayOffDate(String payOffDate) {
		this.payOffDate = payOffDate;
	}

	public List<PositionDetailDto> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<PositionDetailDto> positionList) {
		this.positionList = positionList;
	}
	
	
	
	
}
