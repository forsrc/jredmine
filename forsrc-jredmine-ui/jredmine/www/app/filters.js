"use strict";

define(["angular", "console"], function(angular, console) {
    console.group("filters.js");
    console.info("filters.js --> ");
    var filters = {
    };
    function initialize(angular) {
        console.info("filters.js --> initialize()");
        var angularModule = angular.module('jredmineNgApp.filters', []);
        Object.keys(filters).forEach(function(name) {
            var filter = filters[name];
            if (!filter) {
                return;
            }
            angularModule.filter(name, filter);
            console.debug("services.js --> " + name, filter);
        });

    }
    console.groupEnd();
    return {initialize: initialize};
});