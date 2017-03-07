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

define([], function () {

    var directives = {};

    directives.mydirective = function () {
        return {
            restrict: "jredmine",
            template: "<strong>jredmineDirective</strong>",
            replace: true
        };

    };

    directives.appVersion = function (version) {
        return function (scope, elm, attrs) {
            elm.text(version);
        };
    };

    return directives;
});