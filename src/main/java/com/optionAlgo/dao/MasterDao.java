package com.optionAlgo.dao;
import java.util.List;
import java.util.Map;

import com.optionAlgo.entity.Expiry;
import com.optionAlgo.entity.OptionBean;
import com.optionAlgo.form.data.FutureScripFormData;
import com.optionAlgo.form.data.OptionPriceFormDto;
import com.optionAlgo.form.data.PositionDetailDto;


public interface MasterDao {
    List<Expiry> getAllExpiries();

	List<String> getAllScripNames();

	FutureScripFormData getScripDataByScripName(String scripName);

	List<OptionPriceFormDto> getOptionPriceDataForAllStrikeByExpiry(String scripName,
			String expiryDate);

	Double getCurrentPriceForPosition(PositionDetailDto po, String scripName);

	Double getSpotPriceByScrip(String scripName, String expiryDate);

	List<Double> getStrikePriceByScripNameLimit(String scripName, int noOfStrikePrice, boolean itm, Double spotPrice,
			String seriesName);

	Map<String, OptionBean> getOptionBeanByStrikePrice(String scripName, String expiryDate, List<Double> spList);
}
 