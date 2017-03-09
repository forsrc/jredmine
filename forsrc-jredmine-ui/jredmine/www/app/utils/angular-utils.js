"use strict";


define([], function() {

    function initialize(angular) {
        var angularModule = angular.module('jredmineNgApp.utils', []);
        angularModule.service("utils",
                function() {
                    require(["utils"], ['$injector', function(service) {
                            $injector.invoke(service, this, {});
                        }]);
                });
    }
    return {initialize: initialize};
});
