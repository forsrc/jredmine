"use strict";

define(["console"], function(console) {
    console.group("login-service.js");
    var service =
            [
                '$http', '$scope', "$_shared",
                function($http, $scope, $_shared) {
                    console.info("login-controller.js --> function()", $_shared);

                }
            ];
    console.groupEnd();
    return service;
});