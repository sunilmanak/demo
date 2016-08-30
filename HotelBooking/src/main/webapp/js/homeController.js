angular.module('hbapp.homeController', []).controller('homeController',
		['$rootScope','$scope','$http','$location',function($rootScope,$scope, $http,$location) {
			
	$scope.user = {};
	$("#UserLoginError").hide();
	
	$scope.login = function(){
		if(($scope.user.userName == null ||$scope.user.userName.trim() == "") &&
				($scope.user.password == null ||	$scope.user.password.trim() == "")){
			alert("Invalid User Name And Password");
			return ;
		}
		else
			if(($scope.user.userName == null || $scope.user.userName.trim() == "")) {
				alert("Invalid User Name");
				return ;
		}
		else if(($scope.user.password == null || $scope.user.password.trim() == "")) {
					alert("Invalid Password");
					return ;
		}	
		
		$http.post("./login",$scope.user).success(function(result){
			if(result.status)
    		{ 
				var data =  result.data;
				console.log(data.empId +" ==  "+ data.empName);
				if(data.empId != null && data.empName != null){
					console.log("loadAllRooms");		
					 $location.path("/loadAllRooms");
				}
				$("#UserLoginError").hide();
    		}else{
    			$("#UserLoginError").show();
    			$scope.errors = result.errors;
    		}       		
	  	}).error(function(result, status, headers, config){
	  		console.log("Error While processing Request try after some time");
	 });
		
	};
	
	

}]);