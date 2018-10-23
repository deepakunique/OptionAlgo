package com.optionAlgo.dao;
import java.util.List;

import com.optionAlgo.entity.Expiry;
import com.optionAlgo.form.data.FutureScripFormData;
import com.optionAlgo.form.data.OptionPriceFormDto;


public interface MasterDao {
    List<Expiry> getAllExpiries();

	List<String> getAllScripNames();

	FutureScripFormData getScripDataByScripName(String scripName);

	List<OptionPriceFormDto> getOptionPriceDataForAllStrikeByExpiry(String scripName,
			String expiryDate);
}
 