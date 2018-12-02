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
			$scope.showStrategies = false; 
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
			$scope.showGraph = false;
			$scope.buyOrSell ='Buy';
			$scope.lotQty = 1;
			$scope.positionlist = [];
			$scope.showRange = false; 
			$scope.showOptionStrategies = false;
			$scope.view = ['NOT ABOVE','ABOVE' , 'BETWEEN','BELOW','NOT BELOW'];
			$scope.ltpOrBid ="ltp";
			
			$scope.onSignIn  = function (googleUser) {
				  var profile = googleUser.getBasicProfile();
				  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
				  console.log('Name: ' + profile.getName());
				  console.log('Image URL: ' + profile.getImageUrl());
				  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
				};
			
			window.onSignIn = $scope.onSignIn;
			
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
						var d = new Date(value.data.futureAllExpiryMap[i].expiryDate); 
						$scope.date = d;
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
			
			$scope.tab = 1;

		    $scope.setTab = function(newTab){
		      $scope.tab = newTab;
		    };

		    $scope.isSet = function(tabNum){
		      return $scope.tab === tabNum;
		    };
			 
			$scope.getSelectedScripDetails = function() {
				$scope.expiryDateList = [];
				$scope.previousSelectedExpiryDate =  angular.copy($scope.selectedExpiry);
				$scope.showGraph = false;
				UserService.getSelectedScripDetails($scope.selectedScripName).then(function(value) {
					 $scope.futureAllExpiryMap =   value.data.futureAllExpiryMap;
					console.log("getSelectedScripDetails  details==>",value.data);
					$scope.positionlist = [];
					for(var i=0;i<value.data.futureAllExpiryMapSize;i++){
						$scope.expiryDateList.push(value.data.futureAllExpiryMap[i].expiryDate);
						if(i==0){
							$scope.spotPrice= value.data.futureAllExpiryMap[i].spotPrice;
							$scope.changeInOi=value.data.futureAllExpiryMap[i].changeInOi;
							$scope.expiryDate=value.data.futureAllExpiryMap[i].expiryDate;
							var d = new Date(value.data.futureAllExpiryMap[i].expiryDate); 
							$scope.date = d;
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
			
			 
			$scope.addPosition = function( ){ 
				$scope.showGraph = false;
				console.log('instrument value ----',$scope.selectedSegment);
				var instrumentType = 'F';
				var price = 0;
				var action = '';
				if($scope.buyOrSell == 'Buy'){
					action = 'B';
				}else if($scope.buyOrSell == 'Sell'){
					action = 'S';
				}
				
				if($scope.selectedSegment == 'Options'){
					var instrumentType = 'O';
					price = $scope.optionPrice;
				} else{
					price = $scope.futurePrice;
				}
				if($scope.selectedOptionStrike == ""){
					$scope.selectedOptionStrike = 0;
				}
				
				var obj = { "instrumentType" : instrumentType,
						  "action" : action,
						  "entryPrice" : price,
						  "exitPrice" : '0',
						  "lotSize" : $scope.lotSize,
						  "strikePrice" : $scope.selectedOptionStrike,
						   "expiryDate" : $scope.selectedExpiry,
						  "optionType" : $scope.selectedOptionType,
						  "lotQty" : $scope.lotQty,
						  "spotPrice" : $scope.spotPrice,
						  "iv" : $scope.iv
					}
				$scope.positionlist.push(obj);
				var positionRequestFormDto = { "scripName": $scope.selectedScripName,
												"payOffDate" : $scope.date,
												 "positionList": $scope.positionlist  };
			 
				console.log('obj________________',positionRequestFormDto);
				
				UserService.addPosition(positionRequestFormDto).then(function(value) {
					console.log("data from add position",value);
					$scope.chartData = [];
					$scope.data = [];
					$scope.maxProfit = value.data.maxProfit ;
					$scope.maxLoss = value.data.maxLoss;
					if(value.data.breakEven != null){
						$scope.breakEvens = value.data.breakEven;
					}else{
						$scope.breakEvens = 0;
					}
					
					$scope.totalPnl = value.data.pnl;
					for(var i=0;value.data.chartDto.length>i;i++){
						var object= {};
						var chartObject = value.data.chartDto[i];
						object.changePercent = parseFloat(chartObject.changePercent);
						object.currentPnL = parseFloat(chartObject.currentPnL);
						object.finalPnL = parseFloat(chartObject.finalPnL);
						object.stockPrice = parseFloat(chartObject.stockPrice);
						$scope.data.push(object);
					}
					
					$scope.greek = value.data.greekDto;
					$scope.pnl = value.data.pnlDto;
					$scope.positionList = [];
					for(var i=0;$scope.pnl.length-1>i;i++){
						$scope.positionList.push($scope.pnl[i]);
					}
					console.log('data successfully uploaded---------------------',$scope.data);
					$scope.myfunction();
					$scope.showGraph = true;
					
					 
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
			$scope.getOpenStategies = function( data){ 
				$scope.showOptionStrategies = true;
				$scope.lwb =$scope.spotPrice;
			 	var d = new Date($scope.futureAllExpiryMap[0].expiryDate); 
				$scope.targetDate  = d;
				 
						 
				if(data == 'home'){
					$scope.showOptionStrategies = false;
					if($scope.selectedScripName != $scope.selectedStockName){
						$scope.selectedScripName = $scope.selectedStockName;
						$scope.getSelectedScripDetails();
					}
				} else{
					 $scope.selectedStockName = $scope.selectedScripName;
				}
			}
			
			
			$scope.searchStrategies = function(){ 
				$scope.showStrategies = false; 
				 var target2 = 0;
				 if($scope.upb){
					 target2 = $scope.upb;
				 }
				 console.log('asdfasdf===',$scope.selectedView,'-----',target2);
				var SystemStrategyRequestFormDto = {
						"scripName" : $scope.selectedStockName,
						"view" : $scope.selectedView,
						"target1" : $scope.lwb,
						"target2" : target2,
						"payOffDate" : $scope.targetDate,
						"pricingType" : $scope.ltpOrBid,
						"lotSize" : '0'
				};
				UserService.searchStrategies(SystemStrategyRequestFormDto).then(function(value) {
					console.log('option strategies result --->',value.data);
					$scope.showStrategies = true; 
					$scope.searchStrategiesData = value.data;
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
			
			
			$scope.searchStockScripByName = function(selectedStockName) {
				$scope.expiryDateList = [];
				$scope.selectedStockName =  selectedStockName;
				$scope.previousSelectedExpiryDate =  angular.copy($scope.selectedExpiry);
				$scope.showGraph = false;
				 
				 if($scope.selectedView == 'BETWEEN'){
					  $scope.showRange = true;
					  
				  }
				UserService.getSelectedScripDetails($scope.selectedStockName).then(function(value) {
					 $scope.futureAllExpiryMap =   value.data.futureAllExpiryMap;
					console.log("getSelectedScripDetails  details==>",value.data);
					$scope.positionlist = [];
					for(var i=0;i<value.data.futureAllExpiryMapSize;i++){
						$scope.expiryDateList.push(value.data.futureAllExpiryMap[i].expiryDate);
						if(i==0){
							$scope.spotPrice= value.data.futureAllExpiryMap[i].spotPrice;
							$scope.lwb =$scope.spotPrice;
							$scope.changeInOi=value.data.futureAllExpiryMap[i].changeInOi;
							$scope.expiryDate=value.data.futureAllExpiryMap[i].expiryDate;
							var d = new Date(value.data.futureAllExpiryMap[i].expiryDate); 
							$scope.date = d;
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
			 
			$scope.onViewChange = function( value){ 
			  if(value == 'BETWEEN'){
				  $scope.showRange = true;
				  
			  }else{
				  $scope.showRange = false; 
			  }
			 
			}
			
			
		 
			
			$scope.myfunction = function( ){ 
  var mydata =  $scope.data;
			
			$scope.amChartOptions = {
					  data : mydata,
			         "type": "serial",
    "theme": "light",
    pathToImages: 'https://cdnjs.cloudflare.com/ajax/libs/amcharts/3.13.0/images/',
    "marginRight": 80,
    
    "valueAxes": [{
        "position": "left",
        "title": "Unique PnL"
    }],
    "categoryAxis": {
        "parseDates": false,
        "axisColor": "#DADADA",
        "minorGridEnabled": true,
        "title" : "Underleying Price"
    },
    "graphs": [{
        "id": "g1",
        "fillAlphas": 0.4,
        "valueField": "finalPnL",
         "balloonText": "<div style='margin:5px; font-size:12px;'><b>Change percent:[[changePercent]] , today's Pnl: [[currentPnL]] </b></div>"
    }],
    
   /* "chartCursor": {
        "categoryBalloonDateFormat": "JJ:NN, DD MMMM",
        "cursorPosition": "mouse"
    },*/
    "categoryField": "stockPrice"
			    }
			}
			
		} ]).
directive('tabs', function() {
  return {
    restrict: 'E',
    transclude: true,
    scope: {},
    controller: [ "$scope", function($scope) {
      var panes = $scope.panes = [];

      $scope.select = function(pane) {
        angular.forEach(panes, function(pane) {
          pane.selected = false;
        });
        pane.selected = true;
      }

      this.addPane = function(pane) {
        if (panes.length == 0) $scope.select(pane);
        panes.push(pane);
      }
    }],
    template:
      '<div class="tabbable">' +
        '<ul class="nav nav-tabs">' +
          '<li ng-repeat="pane in panes" ng-class="{active:pane.selected}">'+
            '<a href="" ng-click="select(pane)">{{pane.title}}</a>' +
          '</li>' +
        '</ul>' +
        '<div class="tab-content" ng-transclude></div>' +
      '</div>',
    replace: true
  };
}).
directive('pane', function() {
  return {
    require: '^tabs',
    restrict: 'E',
    transclude: true,
    scope: { title: '@' },
    link: function(scope, element, attrs, tabsCtrl) {
      tabsCtrl.addPane(scope);
    },
    template:
      '<div class="tab-pane" ng-class="{active: selected}" ng-transclude>' +
      '</div>',
    replace: true
  };
})

