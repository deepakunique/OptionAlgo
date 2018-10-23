package com.optionAlgo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optionAlgo.dao.MasterDao;
import com.optionAlgo.entity.Expiry;
import com.optionAlgo.form.data.FutureScripFormData;
import com.optionAlgo.service.MasterService;


@Service
public class MasterServiceImpl implements MasterService {
	@Autowired
	private MasterDao masterDao;
	
	@Override
	public List<Expiry> getAllExpiries() {
		// TODO Auto-generated method stub
		return masterDao.getAllExpiries();
	}
	
	@Override
	public List<String> getAllScripNames() {
		// TODO Auto-generated method stub
		return masterDao.getAllScripNames();
	}
	
	@Override
	public FutureScripFormData getScripDataByScripName(String scripName) {
		// TODO Auto-generated method stub
		return masterDao.getScripDataByScripName(scripName);
	}
	
	
}
