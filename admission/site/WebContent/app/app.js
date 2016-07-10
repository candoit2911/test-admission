var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	// Item List Arrays
	$scope.selectedItems = [];
	$scope.itemAmount = 1;
	var servicePath = 'http://localhost:9000';
	$scope.showItems = false;
	$scope.showOrder = false;
    $scope.getStudent = function () {
    	$http({
    		  'method': 'GET',
    		  'url': servicePath + '/admission/get-student?rollNumber=' +$scope.rollNumber
    		})    	
    	.then(function(response) {        	
        	$scope.student = response.data;
        	$scope.loadItems();
        }, function(response) {        	
        	$scope.showItems= false;
           
        }); 
    }
    
    $scope.loadItems = function() {
    	$http.get(servicePath + '/admission/get-items')
        .then(function(response) {        	
        	$scope.items = response.data;
        	$scope.showItems= true;
           // $scope.items = response.data;        	 
        }, function(response){
        	console.log("error");
        	$scope.showItems= false;            
        }); 
    	
    }
 // Add a Item to the list
	$scope.addItem = function () {	
		var i = $scope.selectedItem;
		i.quantity = $scope.itemAmount;
	    $scope.selectedItems.push(i);

	    // Clear input fields after push
	    $scope.itemAmount = 1;
	    $scope.item = {};

	};

	// Add Item to Checked List and delete from Unchecked List
	$scope.removeItem = function (index) {	  
	    $scope.selectedItems.splice(index, 1);
	};

	// Get Total Items
	$scope.getTotalItems = function () {
	    return $scope.selectedItems.length;
	};

	 $scope.processOrder = function() {
		var data = {
			'rollNumber': $scope.student.rollNumber,
			'orderedItem': $scope.selectedItems
		}    
    	$http.post(servicePath + '/admission/process-order', JSON.stringify(data), {})
        .then(function(response) {        	
        	$scope.orderResponse = response.data;
        	$scope.showOrder= true;
        	$scope.showItems= false;  
           // $scope.items = response.data;        	 
        }, function(response){
        	console.log("error");
        	$scope.showOrder= false;            
        }); 	    	
	 }    
});

