"use strict";


define(["console", "views/login/login-service", "views/login/login-factory"], function (console, loginServiceJs, loginFactoryJs) {
    console.group("login-controller.js");
    console.time("login-controller.js");

    function init(angular, module) {
        console.debug("login-controller.js --> init()", angular, module);
        loginServiceJs.initialize(angular, module);
        loginFactoryJs.initialize(angular, module);
    }

    var controller =
        [
            'angular', "name", "module", '$scope', "$_shared",
            function (angular, name, module, $scope, $_shared) {
                console.debug("login-controller.js --> function()", angular, name, $scope, module, $_shared, loginService);
                init(angular, module);

                $scope.user = {
                    username : "forsrc",
                    password : "forsrc"
                };
                $scope.message = 'hello world!';
                // $scope.login = function () {
                //     $scope.dataLoading = true;
                //     console.info($scope.user);
                // };
                //$scope.login = loginService.login();
                $scope.dataLoading = false;
                $scope.$apply();
            }
        ];
    console.timeEnd("login-controller.js");
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


