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
			this.auth = {
				access_token: null,
				token_type: "bearer",
				refresh_token: null,
				expires_in: 0,
				scope:"read write",
				jti: null
			};
        });
    }
    console.groupEnd();
    return {initialize: initialize};
});