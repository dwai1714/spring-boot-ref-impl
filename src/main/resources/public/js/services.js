angular.module('app.services', []).factory('Liquor', function($resource) {
  return $resource('/api/v1/liquors/:id', { id: '@id' }, {
    update: {
      method: 'PUT'
    }
  });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});
