'use strict'

var demoApp = angular.module('demo', [ 'ui.bootstrap', 'demo.controllers',
		'demo.services','720kb.datepicker' ]);
demoApp.constant("CONSTANTS", {
	getUserByIdUrl : "/user/getUser/",
	getAllUsers : "/master/scripnames",
	saveUser : "/user/saveUser"
});