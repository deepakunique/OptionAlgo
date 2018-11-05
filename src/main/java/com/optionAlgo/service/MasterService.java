package com.optionAlgo.service;

import java.util.List;

import com.optionAlgo.entity.Expiry;
import com.optionAlgo.form.data.FutureScripFormData;
import com.optionAlgo.form.data.PayOffResponseDto;
import com.optionAlgo.form.data.PositionRequestFormDto;
import com.optionAlgo.form.data.SystemStrategyRequestFormDto;
import com.optionAlgo.utility.SystemStrategyResponseFormDto;


public interface MasterService {

	List<Expiry> getAllExpiries();

	List<String> getAllScripNames();

	FutureScripFormData getScripDataByScripName(String scripName);

	PayOffResponseDto getPayOffDetail(PositionRequestFormDto positionDto);

	List<SystemStrategyResponseFormDto> getSystemStrategy(SystemStrategyRequestFormDto sysDto);
	
}
