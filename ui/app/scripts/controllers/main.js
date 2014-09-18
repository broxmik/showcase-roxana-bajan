'use strict';

/**
 * @ngdoc function
 * @name uiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the uiApp
 */
angular.module('uiApp')
    .controller('MainCtrl', function ($scope) {
        $scope.message = 'This is the about showing value.';
    });
