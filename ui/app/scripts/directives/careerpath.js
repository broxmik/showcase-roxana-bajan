/*global d3:false*/
'use strict';

/**
 * @ngdoc directive
 * @name uiApp.directive:careerPath
 * @description
 * # careerPath
 */
angular.module('uiApp')
    .directive('careerPath', [ '$window', function ($window) {
        return {
            restrict: 'EA',
            link: function postLink(scope, element) {
                var diameter = 600;

                var svg = d3.select(element[0])
                    .append('svg')
                    .style('width', '100%')
                    .style('height', diameter);

                var bubble = d3.layout.pack()
                    .size([diameter, diameter])
                    .padding(3)
                    .value(function (d) {
                        return d.index * 100;
                    });

                // Browser onresize event
                window.onresize = function () {
                    scope.$apply();
                };

                scope.data = [
                    {title: 'Lead Software Engineer - ORSYP', index: 4, className: 'red-circle'},
                    {title: 'Senior Software Engineer - ORSYP', index: 3, className: 'blue-circle' },
                    {title: 'Software Engineer - ORSYP', index: 2, className: 'orange-circle'},
                    {title: 'Quality Assurance Analyst - ORSYP', index: 1, className: 'yellow-circle'}
                ];

                // Watch for resize event
                scope.$watch(function () {
                    return angular.element($window)[0].innerWidth;
                }, function () {
                    scope.render(scope.data);
                });

                scope.render = function (data) {
                    // our custom d3 code

                    var nodes = bubble.nodes({children: data})
                        .filter(function (d) {
                            return !d.children;
                        });//filter out the outer bubble

                    var vis = svg.selectAll('circle')
                        .data(nodes, function (d) {
                            return d.title;
                        });

                    vis.enter().append('circle')
                        .attr('transform', function (d) {
                            return 'translate(' + d.x + ',' + d.y + ')';
                        })
                        .attr('r', function (d) {
                            return d.r;
                        })
                        .attr('class', function (d) {
                            return d.className;
                        });
                };
            }
        };
    }]);
