/*global $:false*/
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
        var carousel = $('.carousel');
        carousel.carousel({interval:3000});

        $scope.goTo = function(number){
            carousel.carousel(number);
        };

        $scope.next = function(){
            carousel.carousel('next');
        };

        $scope.prev = function(){
            carousel.carousel('prev');
        };

    });
