"use strict";

/**
 (function (angular) {

    angular.module('jredmineNgApp.directives', []).
    directive('appVersion', ['version', function(version) {
        return function(scope, elm, attrs) {
            elm.text(version);
        };
    }]);

})(angular);
 */

define(["angular", "console"],function(angular, console) {
    console.group("directives.js");
    console.timeEnd("directives.js");
    console.debug("directives.js --> ");
    var directives = {};

    function initialize(angular) {
        console.debug("directives.js --> initialize()");
        directives.mydirective = function() {
            return {
                restrict: "E",
                template: "<strong>jredmineDirective</strong>",
                replace: true
            };
        };
        angular.module('jredmineNgApp.directives', []).directive(directives);
    }

    directives.appVersion = function (version) {
        return function (scope, elm, attrs) {
            elm.text(version);
        };
    };
    console.timeEnd("directives.js");
    console.groupEnd();
    return {
        initialize: initialize
    };
});