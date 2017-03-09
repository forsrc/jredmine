"use strict";

define(["console"], function(console) {
    console.group("login-controller.js");
    console.info("login-controller.js --> ");
    var controller =
            [
                '$scope', "$_shared",
                function($scope, $_shared) {
                    console.info("login-controller.js --> function()");
                    $scope.message = 'hello world!';
                    $scope.$apply();
                }
            ];
    console.groupEnd();
    return controller;
});