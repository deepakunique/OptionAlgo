'use strict'

var module = angular.module('demo.controllers', []);
module.controller("UserController", [ "$scope","$rootScope", "UserService",
		function($scope, $rootScope, UserService) {

			$scope.userDto = {
				userId : null,
				userName : null,
				skillDtos : []
			};
			$scope.skills = [];
			$scope.articles =[];
			$scope.allScripNames =[];
			$scope.selectedScripName = "ACC";
			$scope.allsegments =["Futures","Options"];
			$scope.selectedSegment = "Futures";
			$scope.expiryDateList =[];
			$scope.spotPrice= 0;
			$scope.changeInOi=0;
			$scope.expiryDate="";
			$scope.futurePrice=0;
			$scope.iv = 0;
			$scope.ivp=0;
			$scope.lotSize=0;
			$scope.selectedExpiry = "";
			$scope.strikeOptions = [];
			$scope.optionTypes = ["CE","PE"];
			$scope.selectedOptionType ="CE"; 
			$scope.optionPrice ="";
			$scope.optionIV ="";
			$scope.optionPriceList =[];
			$scope.selectedOptionStrike="";
			console.log("__________________________ asdfsadf____________________");
			UserService.getAllScripNames().then(function(value) {
				console.log("data fetched successfully--->",value);
				$scope.allScripNames = value.data;
				console.log("get all scrip names  ==>",value);
			}, function(reason) {
				console.log("error occured");
			}, function(value) {
				console.log("no callback");
			});
			
			UserService.getSelectedScripDetails($scope.selectedScripName).then(function(value) {
				console.log("getSelectedScripDetails  details==>",value.data);
				 $scope.futureAllExpiryMap =   value.data.futureAllExpiryMap;
				for(var i=0;i<value.data.futureAllExpiryMapSize;i++){
					$scope.expiryDateList.push(value.data.futureAllExpiryMap[i].expiryDate);
					if(i==0){
						$scope.spotPrice= value.data.futureAllExpiryMap[i].spotPrice;
						$scope.changeInOi=value.data.futureAllExpiryMap[i].changeInOi;
						$scope.expiryDate=value.data.futureAllExpiryMap[i].expiryDate;
						$scope.futurePrice=value.data.futureAllExpiryMap[i].futurePrice;
						$scope.iv = value.data.futureAllExpiryMap[i].iv;
						$scope.ivp=value.data.futureAllExpiryMap[i].ivp;
						$scope.lotSize=value.data.futureAllExpiryMap[i].lotSize;
						$scope.selectedExpiry = value.data.futureAllExpiryMap[i].expiryDate;
						for(var j=0;j<value.data.futureAllExpiryMap[i].optionPricesList.length;j++){
							$scope.strikeOptions.push(value.data.futureAllExpiryMap[i].optionPricesList[j].strike);
							$scope.optionPriceList.push(value.data.futureAllExpiryMap[i].optionPricesList[j]);
						}
					}
				}
			}, function(reason) {
				console.log("error occured");
			}, function(value) {
				console.log("no callback");
			});
			 
			$scope.getSelectedScripDetails = function() {
				$scope.expiryDateList = [];
				$scope.previousSelectedExpiryDate =  angular.copy($scope.selectedExpiry);
				UserService.getSelectedScripDetails($scope.selectedScripName).then(function(value) {
					 $scope.futureAllExpiryMap =   value.data.futureAllExpiryMap;
					console.log("getSelectedScripDetails  details==>",value.data);
					for(var i=0;i<value.data.futureAllExpiryMapSize;i++){
						$scope.expiryDateList.push(value.data.futureAllExpiryMap[i].expiryDate);
						if(i==0){
							$scope.spotPrice= value.data.futureAllExpiryMap[i].spotPrice;
							$scope.changeInOi=value.data.futureAllExpiryMap[i].changeInOi;
							$scope.expiryDate=value.data.futureAllExpiryMap[i].expiryDate;
							$scope.futurePrice=value.data.futureAllExpiryMap[i].futurePrice;
							$scope.iv = value.data.futureAllExpiryMap[i].iv;
							$scope.ivp=value.data.futureAllExpiryMap[i].ivp;
							$scope.lotSize=value.data.futureAllExpiryMap[i].lotSize;
							if($scope.previousSelectedExpiryDate == ""){
								$scope.previousSelectedExpiryDate = value.data.futureAllExpiryMap[i].expiryDate;
							}
						}
					}
					
					//new code start 
					
					for(var i=0;i<$scope.expiryDateList.length;i++){
						if($scope.futureAllExpiryMap[i].expiryDate == $scope.previousSelectedExpiryDate){
							$scope.optionPriceList = [];
							$scope.strikeOptions = [];
							$scope.spotPrice= $scope.futureAllExpiryMap[i].spotPrice;
							$scope.changeInOi=$scope.futureAllExpiryMap[i].changeInOi;
							$scope.expiryDate=$scope.futureAllExpiryMap[i].expiryDate;
							$scope.futurePrice=$scope.futureAllExpiryMap[i].futurePrice;
							$scope.iv = $scope.futureAllExpiryMap[i].iv;
							$scope.ivp = $scope.futureAllExpiryMap[i].ivp;
							$scope.lotSize = $scope.futureAllExpiryMap[i].lotSize;
							$scope.selectedExpiry = $scope.futureAllExpiryMap[i].expiryDate;
							for(var j=0;j<$scope.futureAllExpiryMap[i].optionPricesList.length;j++){
								$scope.strikeOptions.push($scope.futureAllExpiryMap[i].optionPricesList[j].strike);
								$scope.optionPriceList.push($scope.futureAllExpiryMap[i].optionPricesList[j]);
							}
						}
					}
					for(var i=0;i<$scope.optionPriceList.length;i++){
						if($scope.optionPriceList[i].strike == $scope.selectedOptionStrike){
							console.log($scope.optionPriceList[i].optionTypePriceMap.CE.price,"______on stock index change_______",$scope.optionPriceList[i].optionTypePriceMap.CE.iv);
							if($scope.selectedOptionType == "CE"){
								$scope.optionIV = $scope.optionPriceList[i].optionTypePriceMap.CE.iv;
								$scope.optionPrice = $scope.optionPriceList[i].optionTypePriceMap.CE.price;
							}else{
								$scope.optionIV = $scope.optionPriceList[i].optionTypePriceMap.PE.iv;
								$scope.optionPrice = $scope.optionPriceList[i].optionTypePriceMap.PE.price;
							}
							break;
						}else{
							$scope.optionIV = 0;
							$scope.optionPrice = 0;
						}
					}
					//end
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}

			
			$scope.getSelectedStrikePriceDetails = function(selectedOptionStrike) {
				if(selectedOptionStrike != null){
				$scope.selectedOptionStrike = selectedOptionStrike;
				 
				for(var i=0;i<$scope.optionPriceList.length;i++){
					if($scope.optionPriceList[i].strike == selectedOptionStrike){
						console.log($scope.optionPriceList[i].optionTypePriceMap.CE.price,"______data_______",$scope.optionPriceList[i].optionTypePriceMap.CE.iv);
						if($scope.selectedOptionType == "CE"){
							$scope.optionIV = $scope.optionPriceList[i].optionTypePriceMap.CE.iv;
							$scope.optionPrice = $scope.optionPriceList[i].optionTypePriceMap.CE.price;
						}else{
							$scope.optionIV = $scope.optionPriceList[i].optionTypePriceMap.PE.iv;
							$scope.optionPrice = $scope.optionPriceList[i].optionTypePriceMap.PE.price;
						}
						break;
					}else{
						$scope.optionIV = 0;
						$scope.optionPrice = 0;
					}
				}
				}
			}
			
			$scope.getPriceDetailsByExpiryDate = function(selectedExpiry){
				console.log(selectedExpiry,'      selected date======================');
					for(var i=0;i<$scope.expiryDateList.length;i++){
						if($scope.futureAllExpiryMap[i].expiryDate == selectedExpiry){
							$scope.optionPriceList = [];
							$scope.strikeOptions = [];
							$scope.spotPrice= $scope.futureAllExpiryMap[i].spotPrice;
							$scope.changeInOi=$scope.futureAllExpiryMap[i].changeInOi;
							$scope.expiryDate=$scope.futureAllExpiryMap[i].expiryDate;
							$scope.futurePrice=$scope.futureAllExpiryMap[i].futurePrice;
							$scope.iv = $scope.futureAllExpiryMap[i].iv;
							$scope.ivp = $scope.futureAllExpiryMap[i].ivp;
							$scope.lotSize = $scope.futureAllExpiryMap[i].lotSize;
							$scope.selectedExpiry = $scope.futureAllExpiryMap[i].expiryDate;
							for(var j=0;j<$scope.futureAllExpiryMap[i].optionPricesList.length;j++){
								$scope.strikeOptions.push($scope.futureAllExpiryMap[i].optionPricesList[j].strike);
								$scope.optionPriceList.push($scope.futureAllExpiryMap[i].optionPricesList[j]);
							}
						}
					}
					
					for(var i=0;i<$scope.optionPriceList.length;i++){
						if($scope.optionPriceList[i].strike == $scope.selectedOptionStrike){
							console.log($scope.optionPriceList[i].optionTypePriceMap.CE.price,"_____getPriceDetailsByExpiryDate________",$scope.optionPriceList[i].optionTypePriceMap.CE.iv);
							if($scope.selectedOptionType == "CE"){
								$scope.optionIV = $scope.optionPriceList[i].optionTypePriceMap.CE.iv;
								$scope.optionPrice = $scope.optionPriceList[i].optionTypePriceMap.CE.price;
							}else{
								$scope.optionIV = $scope.optionPriceList[i].optionTypePriceMap.PE.iv;
								$scope.optionPrice = $scope.optionPriceList[i].optionTypePriceMap.PE.price;
							}
							break;
						}else{
							$scope.optionIV = 0;
							$scope.optionPrice = 0;
						}
					}
				}
			
			$scope.getOptionPriceByOptionType = function(optionType){
				$scope.selectedOptionType = optionType;
				for(var i=0;i<$scope.optionPriceList.length;i++){
					if($scope.optionPriceList[i].strike == $scope.selectedOptionStrike){
						console.log($scope.optionPriceList[i].optionTypePriceMap.CE.price,"______data_______",$scope.optionPriceList[i].optionTypePriceMap.CE.iv);
						if(optionType == "CE"){
							$scope.optionIV = $scope.optionPriceList[i].optionTypePriceMap.CE.iv;
							$scope.optionPrice = $scope.optionPriceList[i].optionTypePriceMap.CE.price;
						}else{
							$scope.optionIV = $scope.optionPriceList[i].optionTypePriceMap.PE.iv;
							$scope.optionPrice = $scope.optionPriceList[i].optionTypePriceMap.PE.price;
						}
						break;
					}else{
						$scope.optionIV = 0;
						$scope.optionPrice = 0;
					}
				}
			}
			
		} ]);