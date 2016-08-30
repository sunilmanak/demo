var hbapp = angular.module('hbapp', ['ngRoute','hbapp.homeController','hbapp.loadAllRoomsController','hbapp.customerDetailsController','hbapp.showDetailsController']);
hbapp.config([ '$routeProvider','$locationProvider', function($routeProvider,$locationProvider) {
	$routeProvider.when('/loadAllRooms', {
		controller:'loadAllRoomsController',
		templateUrl : 'html/body/loadAllRooms.html'			
	}).when('/customerDetails/:roomNumber', {
		controller:'customerDetailsController',
		templateUrl : 'html/body/customerDetails.html'			
	}).when('/showDetails/:roomNumber', {
		controller:'showDetailsController',
		templateUrl : 'html/body/showDetails.html'			
	}).otherwise({
		controller:'homeController',
		templateUrl : 'html/body/home.html'
	});	
}]);

