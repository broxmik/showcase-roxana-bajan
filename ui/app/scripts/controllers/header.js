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

        function pathContains(category){
            return $location.path().indexOf(category) > -1;
        }

        function isAbout() {
            return pathContains('/about');
        }

        function isPortfolio() {
            return pathContains('/portfolio');
        }

        function isContact() {
            return pathContains('/contact');
        }

        $scope.activeNav = function() {
            if(isAbout()){
                return 'about';
            }
            if(isPortfolio()){
                return 'portfolio';
            }
            if(isContact()){
                return 'contact';
            }
            return 'home';
        };

        $scope.getTitle = function(){
            if(isAbout()){
                return 'Resume';
            }
            if(isPortfolio()){
                return 'Portfolio';
            }
            if(isContact()){
                return 'Contact';
            }
            return 'Roxana Bajan Showcase Page';
        };
    });
