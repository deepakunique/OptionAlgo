package com.optionAlgo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.optionAlgo.entity.Article;
import com.optionAlgo.entity.Expiry;
import com.optionAlgo.form.data.FutureScripFormData;
import com.optionAlgo.service.IArticleService;
import com.optionAlgo.service.MasterService;

@Controller
@RequestMapping("/master")
public class StrategyMakerController {
	@Autowired
	private MasterService masterService;
	
	
	
	
	@GetMapping("expiries")
	public ResponseEntity<List> getAllExpiries() {
		List<Expiry> expiry = masterService.getAllExpiries();
		
		
		return new ResponseEntity<List>(expiry, HttpStatus.OK);
	}
	
	
	@GetMapping("scripnames")
	public ResponseEntity<List> getScripNames() {
		List<String> scripNames = masterService.getAllScripNames();
		return new ResponseEntity<List>(scripNames, HttpStatus.OK);
	}
	
	@GetMapping("scrip/{scripName}")
	public ResponseEntity<FutureScripFormData> getScripDataByName(@PathVariable("scripName") String scripName) {
		
		FutureScripFormData fs = masterService.getScripDataByScripName(scripName);
		
		
		return new ResponseEntity<FutureScripFormData>(fs, HttpStatus.OK);
	}
	
	
	
	
	
} 