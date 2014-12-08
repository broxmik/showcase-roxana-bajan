'use strict';

describe('Service: calorieService', function () {

  // load the service's module
  beforeEach(module('uiApp'));

  // instantiate service
  var calorieService;
  beforeEach(inject(function (_calorieService_) {
    calorieService = _calorieService_;
  }));

  it('should do something', function () {
    expect(!!calorieService).toBe(true);
  });

});
