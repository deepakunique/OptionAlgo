'use strict'

angular.module('demo.services', []).factory('UserService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
			service.getUserById = function(userId) {
				var url = CONSTANTS.getUserByIdUrl + userId;
				return $http.get(url);
			}
			service.getAllScripNames = function() {
				return $http.get("/master/scripnames");
			}
			service.getSelectedScripDetails = function(scripName) {
				return $http.get("/master/scrip/"+scripName);
			}
			service.saveUser = function(userDto) {
				return $http.post(CONSTANTS.saveUser, userDto);
			}
			service.getArticles = function() {
				return $http.get("/user/articles");
			}
			return service;
		} ]);