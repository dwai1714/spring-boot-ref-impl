angular.module('app.controllers', []).controller('LiquorListController', function($scope, $state, popupService, $window, $stateParams, Liquor) {
  $scope.liquors = Liquor.query(); //fetch all liquors. Issues a GET to /api/vi/liquors

  $scope.deleteLiquor = function(liquor) { // Delete a Liquor. Issues a DELETE to /api/v1/liquors/:id
    if (popupService.showPopup('Really delete this?')) {
      liquor.$delete(function() {
        $scope.liquors = Liquor.query(); 
        $state.go('liquors');
      });
    }
  };

  
  // added by Bharat
  $scope.viewLiquor = function(id) {
    $state.go('viewLiquor', { id: id, canEdit: true });
  }; 
  
  $scope.viewLiquorHistory = function (id) {
	  $state.go('viewLiquorHistory', { id: id, canEdit: false });
  };
}).controller('LiquorViewController', function($scope, $stateParams, Liquor) {
	$scope.canEdit = $stateParams.canEdit;
	$scope.liquor = Liquor.get({ id: $stateParams.id }); //Get a single liquor.Issues a GET to /api/v1/liquors/:id
}).controller('LiquorViewHistoryItemController', function($scope, $stateParams, Liquor) {
	$scope.liquor = $stateParams.liquor;
}).controller('LiquorHistoryController', function($scope, $state, $stateParams, $http, Liquor) {
	$scope.canEdit = $stateParams.canEdit;
	$scope.viewHistoryItem = function(row) {
		console.log(row.entity.sourceObj);
	    $state.go('viewLiquorHistoryItem', { liquor: row.entity.sourceObj });
	  }; 
	  $scope.viewDiffLog = function(row) {
			console.log(row.entity.diffLog);
			alert(row.entity.diffLog);
      };
		  $scope.gridOptions = {
				  
		    columnDefs : [
		    	{ name: 'sourceObj.name' , displayName :'Name' },
		    	   		  { name: 'createdBy' },
				      { name: 'lastModifiedBy' },
				      { name: 'createdDate' },
				      { name: 'lastModifiedDate' },
				      { name: 'createdDate' },   
				      { name: 'Show This Item',
				            cellTemplate: '<button class="btn btn-primary" ng-click="grid.appScope.viewHistoryItem(row)">Show History</button>'
				        },
				        { name: 'Show Diff',
				            cellTemplate: '<button class="btn btn-primary" ng-click="grid.appScope.viewDiffLog(row)">Show Diff</button>'
				        }
		]};
		  

		var historyApiUrl = "http://auditservice-env.jbdypyue4w.ap-southeast-1.elasticbeanstalk.com/audit/history/"  + $stateParams.id + "/Liq";
	
	$http.get(historyApiUrl)
		.then(function(response) {
			$scope.gridOptions.data = response.data;
		});
}).controller('LiquorCreateController', function($scope, $state, $stateParams, Liquor) {
	
  $scope.liquor = new Liquor();  //create new liquor instance. Properties will be set via ng-model on UI

  $scope.addLiquor = function() { //create a new liquor. Issues a POST to /api/v1/liquors
    $scope.liquor.$save(function() {
      $state.go('liquors'); // on success go back to the list i.e. liquors state.
    });
  };
}).controller('LiquorEditController', function($scope, $state, $stateParams, Liquor) {
  $scope.updateLiquor = function() { //Update the edited liquor. Issues a PUT to /api/v1/liquors/:id
    $scope.liquor.$update(function() {
      $state.go('liquors'); // on success go back to the list i.e. liquors state.
    });
  };

  $scope.loadLiquor = function() { //Issues a GET request to /api/v1/liquors/:id to get a liquor to update
    $scope.liquor = Liquor.get({ id: $stateParams.id });
  };

  $scope.loadLiquor(); // Load a liquor which can be edited on UI
});

