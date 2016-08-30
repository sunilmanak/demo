angular.module('hbapp.loadAllRoomsController', []).controller('loadAllRoomsController',
		['$rootScope','$scope','$http','$location',function($rootScope,$scope, $http,$location) {
		
   $http.get("./isLogin").success(function(result){
				if(result.status)
				{		
					$http.get("./loadAllRooms").success(function(result){
						if(result.status)
						{ 
							$scope.rooms = result.data;
						}      		
					}).error(function(result, status, headers, config){
						alert("Error While processing Request try after some time");
					});
				}else{
					$location.path("/home");	
				}

			}).error(function(result, status, headers, config){
				$location.path("/home");	
			});
   
    $scope.logout = function(){
		$http.get("./logout").success(function(result){
					$location.path("/home");
		 });
    };
		
}]);