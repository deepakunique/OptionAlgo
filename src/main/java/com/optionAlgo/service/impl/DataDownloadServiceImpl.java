package com.optionAlgo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optionAlgo.dao.DataDownloadDao;
import com.optionAlgo.dao.MasterDao;
import com.optionAlgo.service.DataDownloadService;


@Service
public class DataDownloadServiceImpl implements DataDownloadService {
	@Autowired
	private MasterDao masterDao;
	
	@Autowired
	private DataDownloadDao dataDownloadDao;
	
	
	@Override
	public Map getAllScripRefresh() {
		// TODO Auto-generated method stub
		return dataDownloadDao.getRefreshFutureScrip();
	}
	
	@Override
	public Map getAllOptionChainRefresh() {
		// TODO Auto-generated method stub
		return dataDownloadDao.getRefreshOptionBean();
	}

	
	@Override
	public boolean getRefreshDataByScripName(String scripName) {
		// TODO Auto-generated method stub
		return dataDownloadDao.getRefreshFutureByScripName(scripName);
	}
	
}
