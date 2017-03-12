"use strict";


define(["myutils"], function(myutils) {

    function initialize(angular) {
        var angularModule = angular.module('jredmineNgApp.utils', []);
        angularModule.service("utils",
                function() {
                    return myutils;
                });
    }
    return {initialize: initialize};
});
