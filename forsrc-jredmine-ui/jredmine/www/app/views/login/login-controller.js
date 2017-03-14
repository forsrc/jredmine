"use strict";


define(["angular", "console", 'views/login/login-service'], function (angular, console, loginService) {

    var jsName = "login-controller.js";
    console.group(jsName);
    console.time(jsName);

    console.debug("{0} --> ".formatStr([jsName]), loginService);

    angular.module('jredmineNgApp.routes')
        .controller('loginController', function ($scope, $http, $location, $cookies,  $httpParamSerializer, $mdToast, loginService) {

            console.debug("{0} --> function()".formatStr([jsName]), $scope, $cookies, loginService);
            $scope.user = {
                username: "forsrc",
                password: "forsrc"
            };
            $scope.message = 'hello world!';
            // $scope.login = function () {
            //     $scope.dataLoading = true;
            //     console.info($scope.user);
            // };
            var toast = $mdToast.simple()
              .textContent('Welcome {0}'.formatStr([$scope.user.username]))
              .action('OK')
              .highlightAction(true)
              .highlightClass('md-accent')// Accent is used by default, this just demonstrates the usage.
              .position("right");
            $scope.toLogin = function () {
                $mdToast.show(toast).then(function(response) {
                    if (response === 'ok') {
                         console.debug("{0} --> toLogin() toast:".formatStr([jsName]), toast, response);
                    }
                });
                var success = loginService.login($scope.user);
                if (success) {
                    //$location.path("/menu");
                }
                $scope.dataLoading = true;
                var req = {
                    method: 'GET',
                    url: "app/data/login.jsonp",
                    headers: {
                        "Authorization": "Basic " + $scope.encoded,
                        "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                    },
                    data: $httpParamSerializer($scope.user)
                };
                $http(req).then(function(response) {
                    console.debug("{0} --> toLogin() response:".formatStr([jsName]), response);
                    console.debug("{0} --> toLogin() response:".formatStr([jsName]), response.status, response.data);
                    if(response.status === 200){
                        $location.path("/menu/home1");
                    }
                }, function(response) {
                    console.debug("{0} --> toLogin() error".formatStr([jsName]), response);
                }); 

                //$scope.$apply();
            };

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


