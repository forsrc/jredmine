"use strict";

/**
(function(angular) {
    angular
            .module('jredmine.controllers', ['ngMaterial', 'ngMessages'])
            .controller('loginController', function($scope) {
                $scope.showHints = true;
                //$scope.username = "";
                //$scope.password = "";
            })
            ;
})(angular);
 */

define(["angular", "console"], function(angular, console) {
    console.group("controllers.js");
    console.info("controllers.js --> ");
    function initialize(angular) {
        console.info("controllers.js --> initialize()");
        angular
            .module('jredmineNgApp.controllers', ['ngMaterial', 'ngMessages'])
            .controller('loginController', function($scope) {
                $scope.showHints = true;
                //$scope.username = "";
                //$scope.password = "";
            });
    }
    console.groupEnd();
    return {initialize: initialize};
});

