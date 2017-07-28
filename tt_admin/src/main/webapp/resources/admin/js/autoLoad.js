var app = angular.module("autoLoad_App", []); 

app.controller('autoLoad_Ctrl', function($scope, $http) {
	$scope.tails=[];
	$scope.updTailsTable = function () {
		$http({
	        method : "GET",
	        url : ""
	    }).then(function success(response) {
	        $scope.tails = response.data.ok;
	        if($scope.tails.length > 0)
        	{
	        	
        	}

	    }, function error(response) {
	        $scope.tails = response.statusText;
	    });
	};
	
	
});