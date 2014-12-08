'use strict';

/**
 * @ngdoc service
 * @name uiApp.calorieService
 * @description
 * # calorieService
 * Service in the uiApp.
 */
angular.module('uiApp')
  .service('calorieService', ['$http', function ($http) {
        var getData = function (config, success, failure) {
            $http.get(config.url, {params: config.params})
                .success(function (data) {
                    success(data);
                }).error(function (e) {
                    failure(e);
                });
        };

        this.search = function (success, failure){
            var searchConfig = {url:'/api/food/calories'};
            getData(searchConfig, success, failure);
        };
  }]);
