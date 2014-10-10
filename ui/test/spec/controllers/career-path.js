'use strict';

describe('Controller: CareerPathCtrl', function () {

  // load the controller's module
  beforeEach(module('uiApp'));

  var CareerPathCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CareerPathCtrl = $controller('CareerPathCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
