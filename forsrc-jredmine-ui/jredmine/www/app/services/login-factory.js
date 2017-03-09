"use strict";

define(["console"], function (console) {
    console.group("login-factory.js");

    function initialize(angular, name, module) {
        console.info("login-factory.js --> initialize()", angular, name, module);
        module.factory('login', function ($http, session) {
            console.info("login-factory.js --> initialize()", angular, name, module);
        });
    }

    console.groupEnd();
    return ['angular', "name", "module", initialize];
});
