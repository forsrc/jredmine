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

define([],function() {

    function initialize(app) {
        app.filter(('interpolate'),["version",function(version){
            return function(text) {
                return String(text).replace(/\%VERSION\%/mg, version);
            };
        }]);
    }

    return {initialize : initialize};
});