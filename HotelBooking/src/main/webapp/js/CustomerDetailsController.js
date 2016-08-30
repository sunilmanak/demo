angular.module('hbapp.customerDetailsController', []).controller('customerDetailsController',
		['$rootScope','$scope','$http','$location','$routeParams',function($rootScope,$scope, $http,$location,$routeParams) {
		
		$scope.customerDetails = {};
		$("#UserLoginError").hide();
		$("#TicketDetailSuccess").hide();
		
		 $http.get("./isLogin").success(function(result){
				if(!result.status)
				{
					$location.path("/home");	
				}
		 });
		 
		$scope.logout = function(){
		$http.get("./logout").success(function(result){
					$location.path("/home");
		 });
		};
		
		$scope.customerDetailsme = function(){
			var roomNumber =  $routeParams.roomNumber;
			$("#UserLoginError").hide();
			$("#TicketDetailSuccess").hide();
			var errors = validate($scope.customerDetails);
			$scope.customerDetails.roomNumber = roomNumber;
			if(errors.length > 0){
				$("#UserLoginError").show();
				$scope.errors = errors;
				return ;
			}
			
			$http.post("./saveCustomerDetails",$scope.customerDetails).success(function(result){
			    if(result.status){
			    	var data = result.data; 
			    	$("#TicketDetailSuccess").show();	
			    	$("#TicketDetailSuccessMSG").html("Ticket Updated SuccessFully : Ticket ID : "+data.customerId);	
			   }else{
				   $("#UserLoginError").show();
	    			$scope.errors = result.errors;
			   }
			});
			
		};
		
		 $('#checkInDate').datepicker({
	            format: 'yyyy-mm-dd',
	            startDate: new Date(),
	            endDate: '2020-12-30'
	        }).on('changeDate', function(e) {
	            // Revalidate the date field
	           // $('#dateRangeForm').formValidation('revalidateField', 'date');
	        });
		 $('#checkOutDate').datepicker({
	            format: 'yyyy-mm-dd',
	            startDate: new Date(),
	            endDate: '2020-12-30'
	        }).on('changeDate', function(e) {
	            // Revalidate the date field
	           // $('#dateRangeForm').formValidation('revalidateField', 'date');
	        });
		 
		 $("#file").on('change', function(event) {
                var reader = new FileReader();
			    reader.onload = function( loadEvent ) {
                 $scope.customerDetails.fileData = loadEvent.target.result;
			        $scope.$apply();
			    };
			    reader.readAsDataURL( event.target.files[0] );
			});
}]);

function validate(CustomerDetails){
	var errors = [];
	if(CustomerDetails.firstName == null || typeof CustomerDetails.firstName == "undefined" || CustomerDetails.firstName.trim() == ""){
		errors.push("FirstName Shouldnot be Empty");
	}
	if(CustomerDetails.lastName == null || typeof CustomerDetails.lastName == "undefined" || CustomerDetails.lastName.trim() == ""){
		errors.push("LastName Shouldnot be Empty");
	}
	if(CustomerDetails.fileData == null || typeof CustomerDetails.fileData == "undefined" || CustomerDetails.fileData.trim() == ""){
		errors.push("Phote Id Should not be Empty");
	}
   if(CustomerDetails.mobile == null || typeof CustomerDetails.mobile == "undefined" || CustomerDetails.mobile.trim() == ""){
	   errors.push("Mobile Number Should not be Empty");
	}
   if(isNaN(CustomerDetails.mobile)){
	   errors.push("Invalid Mobile Number");
   }
   if(CustomerDetails.checkInDate == null || typeof CustomerDetails.checkInDate == "undefined" || CustomerDetails.checkInDate.trim() == ""){
	   errors.push("Checkin Date Should not be Empty");
	}
   if(CustomerDetails.checkOutDate == null || typeof CustomerDetails.checkOutDate == "undefined" || CustomerDetails.checkOutDate.trim() == ""){
	   errors.push("CheckOut Date Should not be Empty");
	}
   if(CustomerDetails.address == null || typeof CustomerDetails.address == "undefined" || CustomerDetails.address.trim() == ""){
	   errors.push("Address Should not be Empty");
	}
   return errors;
};








