"use strict";

define(["console"], function(console) {
    console.group("shared-service.js");
    console.info("shared-service.js -->");

    function initialize(angular) {
        console.info("shared-service.js --> initialize()");
        var angularModule = angular.module("jredmineNgApp.shared", []);
        angularModule.service("$_shared", function() {
            this.info = {
                title: "JRedmine",
                auth: "forsrc"
            };
        });
    }
    console.groupEnd();
    return {initialize: initialize};
});