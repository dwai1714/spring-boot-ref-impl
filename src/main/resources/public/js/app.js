(function() {
	var app = angular.module('app', ['ui.router', 'navController', 'ngAnimate', 'ui.bootstrap', 'ngResource', 'app.controllers', 'app.services', 
		'ui.grid', 'ui.grid.resizeColumns' ])

	// define for requirejs loaded modules
	define('app', [], function() { return app; });

	// function for dynamic load with requirejs of a javascript module for use with a view
	// in the state definition call add property `resolve: req('/views/ui.js')`
	// or `resolve: req(['/views/ui.js'])`
	// or `resolve: req('views/ui')`
	function req(deps) {
		if (typeof deps === 'string') deps = [deps];
		return {
			deps: function ($q, $rootScope) {
				var deferred = $q.defer();
				require(deps, function() {
					$rootScope.$apply(function () {
						deferred.resolve();
					});
					deferred.resolve();
				});
				return deferred.promise;
			}
		}
	}

	app.config(function($stateProvider, $urlRouterProvider, $controllerProvider){
		var origController = app.controller
		app.controller = function (name, constructor){
			$controllerProvider.register(name, constructor);
			return origController.apply(this, arguments);
		}

		var viewsPrefix = 'views/';

		// For any unmatched url, send to /
		$urlRouterProvider.otherwise("/")

		$stateProvider
			// you can set this to no template if you just want to use the html in the page
			.state('home', {
				url: "/",
				templateUrl: viewsPrefix + "home.html",
				data: {
					pageTitle: 'Home'
				}
			})
			.state('liquors',{
	        url:'/liquor',
	        templateUrl: viewsPrefix + 'liquor.html',
	        controller:'LiquorListController'
	    }).state('viewLiquor',{
	       url:'/liquors/:id/view',
	       templateUrl: viewsPrefix + 'liquor-view.html',
	       controller:'LiquorViewController',
		   // added by Bharat
		   params: {
			   canEdit: false
		   }
	    }).state('newLiquor',{
	        url:'/liquors/new',
	        templateUrl: viewsPrefix + 'liquor-add.html',
	        controller:'LiquorCreateController'
	    }).state('viewLiquorHistory',{
	        url:'/liquors/:id/history',
	        templateUrl: viewsPrefix + 'history-view.html',
	        controller:'LiquorHistoryController'
	    }).state('viewDiffLog',{
	        url:'/liquors/diff',
	        templateUrl: viewsPrefix + 'diff-view.html',
	        controller:'LiquorHistoryController'

	    }).state('viewLiquorHistoryItem',{
	        url:'/liquors/history-item',
	        templateUrl: viewsPrefix + 'history-item-view.html',
	        controller:'LiquorViewHistoryItemController',
	        params: {
			   liquor: null
		   }
	    }).state('editLiquor',{
	        url:'/liquors/:id/edit',
	        templateUrl: viewsPrefix + 'liquor-edit.html',
	        controller:'LiquorEditController'
	    })
	})
	.directive('updateTitle', ['$rootScope', '$timeout',
		function($rootScope, $timeout) {
			return {
				link: function(scope, element) {
					var listener = function(event, toState) {
						var title = 'Project Name';
						if (toState.data && toState.data.pageTitle) title = toState.data.pageTitle + ' - ' + title;
						$timeout(function() {
							element.text(title);
						}, 0, false);
					};

					$rootScope.$on('$stateChangeSuccess', listener);
				}
			};
		}
	]);
}());
