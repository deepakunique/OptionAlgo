package com.optionAlgo.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.optionAlgo.service.DataDownloadService;
import com.optionAlgo.service.MasterService;

@Controller
@RequestMapping("/dataDownload")
public class DataDownloadController {
	@Autowired
	private MasterService masterService;
	
	@Autowired
	private DataDownloadService dataDownloadService;
	
	
	
	
	@GetMapping("refreshFuture")
	public ResponseEntity<Map> downloadAllFutueData() {
		Map outCome  = dataDownloadService.getAllScripRefresh();
		return new ResponseEntity<Map>(outCome, HttpStatus.OK);
	}
	
	
	@GetMapping("refreshOptionChain")
	public ResponseEntity<Map> downloadAllOptionChainData() {
		Map outCome  = dataDownloadService.getAllOptionChainRefresh();
		return new ResponseEntity<Map>(outCome, HttpStatus.OK);
	}
	
	
	
	@GetMapping("scripnames")
	public ResponseEntity<List> getScripNames() {
		List<String> scripNames = masterService.getAllScripNames();
		return new ResponseEntity<List>(scripNames, HttpStatus.OK);
	}
	
	
	
	
	
} 