package com.optionAlgo.service;

import java.util.Map;


public interface DataDownloadService {


	Map getAllScripRefresh();

	Map getAllOptionChainRefresh();

	boolean getRefreshDataByScripName(String scripName);

	
}
