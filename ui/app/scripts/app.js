'use strict';

/**
 * @ngdoc overview
 * @name uiApp
 * @description
 * # uiApp
 *
 * Main module of the application.
 */
angular
    .module('uiApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .when('/about', {
                templateUrl: 'views/about.html',
                controller: 'AboutCtrl'
            })
            .when('/contact', {
                templateUrl: 'views/contact.html',
                controller: 'ContactCtrl'
            })
            .when('/portfolio', {
                templateUrl: 'views/portfolio.html',
                controller: 'PortfolioCtrl'
            })
            .when('/portfolio/career-path', {
                templateUrl: 'views/career-path.html',
                controller: 'CareerPathCtrl'
            })
            .when('/portfolio/calories', {
              templateUrl: 'views/calories.html',
              controller: 'CaloriesCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
