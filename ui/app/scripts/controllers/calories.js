'use strict';

/**
 * @ngdoc function
 * @name uiApp.controller:CaloriesCtrl
 * @description
 * # CaloriesCtrl
 * Controller of the uiApp
 */
angular.module('uiApp')
    .controller('CaloriesCtrl', ['$scope','calorieService', function ($scope, calorieService) {
        var success = function (data) {
            console.log(data);
            $scope.data = data;
            $scope.error = undefined;
        };
        var failure = function (error) {
            console.log(error);
            $scope.data = undefined;
            $scope.error = error;
        };
        calorieService.search(success, failure);
    }]);
