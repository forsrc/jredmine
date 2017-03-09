"use strict";


define(["console"], function (console) {
    console.group("login-controller.js");

    var controller =
        [
            'angular', "name", "module", '$scope', "$_shared",
            function (angular, name, module, $scope, $_shared) {
                console.info("login-controller.js --> function()", angular, name, $scope, $_shared);
                $scope.message = 'hello world!';
                $scope.$apply();
            }
        ];
    console.groupEnd();
    return controller;
});


// define(["console"], function (console) {
//     console.group("login-controller.js");
//
//     function initialize(angular, name) {
//         console.info("login-controller.js --> initialize()", angular, name);
//         var angularModule = angular.module('jredmineNgApp');
//         console.info("login-controller.js --> angularModule", angularModule);
//         angularModule.controller("loginCtrl", [
//             '$scope', "$_shared",
//             function ($scope, $_shared) {
//                 console.info("login-controller.js --> function()", $_shared);
//                 $scope.message = 'hello world!';
//                 $scope.$apply();
//
//             }
//         ]);
//
//     }
//
//     console.groupEnd();
//     return ['angular', "name", initialize];
// });


