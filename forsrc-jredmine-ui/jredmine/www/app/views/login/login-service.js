"use strict";

// define(["console"], function(console) {
//     console.group("login-service.js");
//     var service =
//             [
//                 '$http', '$scope', "$_shared",
//                 function($http, $scope, $_shared) {
//                     console.info("login-service.js --> function()", $_shared);
//
//                 }
//             ];
//     console.groupEnd();
//     return service;
// });


define(["angular", "console"], function (angular, console) {
    console.group("login-service.js");
    console.time("login-service.js");

    angular.module('jredmineNgApp.routes')
    .service("loginService",
        function () {
            console.debug("login-service.js --> function()");

            this.login = function (user) {
                console.info(user);
                alert(user.username);
            }
        }
    );


    function initialize(angular, module) {
        console.debug("login-service.js --> initialize()");
        module.service("loginService",
            function () {
                console.debug("login-service.js --> function()");

                this.login = function (user) {
                    console.info(user);
                    alert(user.username);
                }
            }
        );
    }

    console.timeEnd("login-service.js");
    console.groupEnd();
    //return {initialize: initialize};
});