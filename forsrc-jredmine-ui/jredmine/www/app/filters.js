"use strict";

/**
(function (angular) {

    angular.module('jredmineNgApp.filters', []).
    filter('interpolate', ['version', function(version) {
        return function(text) {
            return String(text).replace(/\%VERSION\%/mg, version);
        }
    }]);

})(angular);
*/

define(["angular", "console"],function(angular, console) {
    console.group("filters.js");
    console.info("filters.js --> ");
    function initialize(angular) {
        console.info("filters.js --> initialize()");
        angular
                .module('jredmineNgApp.filters', [])
                .filter('interpolate', ["version", function(version) {
                    return function(text) {
                        return String(text).replace(/\%VERSION\%/mg, version);
                    };
                }]);
    }
    console.groupEnd();
    return {initialize : initialize};
});