package com.optionAlgo.service;

import java.util.List;

import com.optionAlgo.entity.Expiry;
import com.optionAlgo.form.data.FutureScripFormData;


public interface MasterService {

	List<Expiry> getAllExpiries();

	List<String> getAllScripNames();

	FutureScripFormData getScripDataByScripName(String scripName);
	
}
