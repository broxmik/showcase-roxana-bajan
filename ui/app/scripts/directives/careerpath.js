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
                var diameter = 400;

                var svg = d3.select(element[0])
                    .append('svg')
                    .style('width', '100%')
                    .style('height', diameter);

                // Browser onresize event
                window.onresize = function () {
                    scope.$apply();
                };

                scope.data = [
                    {title: 'Lead Software Engineer - ORSYP', index: 4, duration: 0.5, className: 'red-circle'},
                    {title: 'Senior Software Engineer - ORSYP', index: 3, duration: 3, className: 'blue-circle' },
                    {title: 'Software Engineer - ORSYP', index: 2, duration: 3, className: 'orange-circle'},
                    {title: 'Quality Assurance Analyst - ORSYP', index: 1, duration: 0.6, className: 'yellow-circle'}
                ];

                // Watch for resize event
                scope.$watch(function () {
                    return angular.element($window)[0].innerWidth;
                }, function () {
                    scope.render(scope.data);
                });

                scope.render = function (data) {
                    // our custom d3 code

                    //<line x1="5" y1="5" x2="40" y2="40" stroke="gray" stroke-width="5"  />
                    var dataForLines = data.slice(1,4);
                    var lines = svg.selectAll('line')
                        .data(dataForLines, function (d) {
                            return d.title;
                        });
                    lines.enter().append('line')
                        .attr('x1', function(d){
                            var x = 100;
                            for(var i=1; i< d.index; i++){
                                x+= 2*data[i-1].duration*20 + 50;
                            }
                            return x + 2 * d.duration * 20;
                        })
                        .attr('y1', 100)
                        .attr('x2', function(d){
                            var x = 100;
                            for(var i=1; i< d.index; i++){
                                x+= 2*data[i-1].duration*20 + 50;
                            }
                            return x + 2 * d.duration * 20 + 50;
                        })
                        .attr('y2', 100)
                        .attr('stroke', 'grey')
                        .attr('stroke-width', 0.5);

                    var vis = svg.selectAll('circle')
                        .data(data, function (d) {
                            return d.title;
                        });

                    vis.enter().append('circle')
                        .attr('cx', function (d) {
                            var x = 100;
                            for(var i=1; i< d.index; i++){
                                x+= 2*data[i-1].duration*20 + 50;
                            }
                            return x + d.duration * 20;
                        })
                        .attr('cy', 100)
                        .attr('r', function (d) {
                            return d.duration * 20;
                        })
                        .attr('class', function (d) {
                            return d.className;
                        });
                };
            }
        };
    }]);
