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

define([],function() {

    function initialize(app) {
        app.controller('loginController', function($scope) {
            $scope.showHints = true;
            //$scope.username = "";
            //$scope.password = "";
        })
    }

    return {initialize : initialize};
});

