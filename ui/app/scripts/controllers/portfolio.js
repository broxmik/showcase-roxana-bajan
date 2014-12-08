'use strict';

/**
 * @ngdoc function
 * @name uiApp.controller:PortfolioCtrl
 * @description
 * # PortfolioCtrl
 * Controller of the uiApp
 */
angular.module('uiApp')
    .controller('PortfolioCtrl', function ($scope, $sce) {
        $scope.cases = [
            {
                image: $sce.trustAsResourceUrl('images/cases/career_path.png'),
                title: 'Career Path',
                text: 'This is a small widget I made with the help of D3js SVG library.<br><i class="fa fa-circle-o-notch fa-spin"></i> Work in progress...',
                path: '#/portfolio/career-path'
            },
            {
                image: $sce.trustAsResourceUrl('images/cases/calories.jpg'),
                title: 'Calories',
                text: 'Regular foods and their corresponding nutritive values.<br><i class="fa fa-circle-o-notch fa-spin"></i> Work in progress...',
                path: '#/portfolio/calories'
            }
        ];
    });
