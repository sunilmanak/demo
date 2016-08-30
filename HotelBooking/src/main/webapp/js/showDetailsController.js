angular.module('hbapp.showDetailsController', []).controller('showDetailsController',
		['$rootScope','$scope','$http','$location','$routeParams',function($rootScope,$scope, $http,$location,$routeParams) {
		
   $http.get("./isLogin").success(function(result){
				if(result.status)
				{		
					var roomNumber =  $routeParams.roomNumber;
					$http.post("./showCustomerDetails",roomNumber).success(function(result){
						if(result.status)
						{ 
							var cust = result.data;
							var cindate = new Date(cust.checkInDate)
							var coutdate = new Date(cust.checkOutDate)
							cust.checkInDate =cindate;
							cust.checkOutDate = coutdate;
							console.log(cindate);
							console.log(coutdate);
							$scope.customer= cust;
							console.log($scope.customer);
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