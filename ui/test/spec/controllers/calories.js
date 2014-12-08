'use strict';

describe('Controller: CaloriesCtrl', function () {

  // load the controller's module
  beforeEach(module('uiApp'));

  var CaloriesCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CaloriesCtrl = $controller('CaloriesCtrl', {
      $scope: scope
    });
  }));
});
