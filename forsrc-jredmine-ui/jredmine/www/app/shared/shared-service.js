"use strict";

define(["angular", "console"], function(angular, console) {
    console.group("shared-service.js");
    console.info("shared-service.js -->");

    function initialize(angular) {
        console.info("shared-service.js --> initialize()");
        angular
                .module("jredmineNgApp.shared", [])
                .service("shared", function() {
                    this.info = {
                        title: "JRedmine",
                        auth: "forsrc"
                    };
                });
    }
    console.groupEnd();
    return {initialize: initialize};
});