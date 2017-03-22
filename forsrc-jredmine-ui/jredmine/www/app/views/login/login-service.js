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
    var jsName = "login-controller.js";
    console.group(jsName);
    console.time(jsName);

    angular.module('jredmineNgApp')
    .service("loginService",
        function () {
            console.debug("{0} --> function()".formatStr([jsName]));

            this.login = function (user) {
                console.info(user);
                if(user && user.username === "forsrc" && user.password === "forsrc"){
                    return true;
                }
                return false;
            }
        }
    );


    function initialize(angular, module) {
        console.debug("{0} --> initialize()".formatStr([jsName]));
        module.service("loginService",
            function () {
                console.debug("{} --> function()".formatStr([jsName]));

                this.login = function (user) {
                    console.info(user);
                    alert(user.username);
                }
            }
        );
    }

    console.timeEnd(jsName);
    console.groupEnd();
    //return {initialize: initialize};
});