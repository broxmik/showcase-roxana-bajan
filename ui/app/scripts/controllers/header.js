'use strict';

/**
 * @ngdoc function
 * @name uiApp.controller:HeaderCtrl
 * @description
 * # HeaderCtrl
 * Controller of the uiApp
 */
angular.module('uiApp')
    .controller('HeaderCtrl', function ($scope, $location) {

        $scope.activeNav = function() {
            if ($location.path() === '/') {
                return 'home';
            }
            if($location.path() === '/about'){
                return 'about';
            }
            if($location.path() === '/portfolio'){
                return 'portfolio';
            }
            if($location.path() === '/contact'){
                return 'contact';
            }
            return 'home';
        };
    });