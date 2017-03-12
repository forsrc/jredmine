"use strict";


define(["angular", "console", 'views/login/login-service'], function (angular, console, loginService) {

    var jsName = "login-controller.js";
    console.group(jsName);
    console.time(jsName);

    console.debug("{0} --> ".formatStr([jsName]), loginService);

    angular.module('jredmineNgApp.routes')
        .controller('loginController', function ($scope, $location, loginService) {

            console.debug("{0} --> function()".formatStr([jsName]), $scope, loginService);
            $scope.user = {
                username: "forsrc",
                password: "forsrc"
            };
            $scope.message = 'hello world!';
            // $scope.login = function () {
            //     $scope.dataLoading = true;
            //     console.info($scope.user);
            // };
            $scope.toLogin = function () {
                var success = loginService.login($scope.user);
                if (success) {
                    $location.path("/home")
                }
            };
            $scope.dataLoading = false;
            //$scope.$apply();


        });
    console.timeEnd(jsName);
    console.groupEnd();
})
;

// define(["console"], function (console) {
//     console.group("login-controller.js");
//     console.time("login-controller.js");
//
//     var controller =
//             function (angular, name, module, $scope, $_shared, loginService) {
//                 console.debug("login-controller.js --> function()", angular, name, $scope, module, $_shared, loginService);
//                 //init(angular, module);
//
//                 $scope.user = {
//                     username : "forsrc",
//                     password : "forsrc"
//                 };
//                 $scope.message = 'hello world!';
//                 // $scope.login = function () {
//                 //     $scope.dataLoading = true;
//                 //     console.info($scope.user);
//                 // };
//                 //$scope.login = loginService.login();
//                 $scope.dataLoading = false;
//                 $scope.$apply();
//             }
//         ;
//     console.timeEnd("login-controller.js");
//     console.groupEnd();
//     return controller;
// });


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


