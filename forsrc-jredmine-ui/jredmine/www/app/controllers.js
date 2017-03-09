"use strict";


define(["angular", "console"], function (angular, console) {
    console.group("controllers.js");
    console.info("controllers.js --> ");
    var controllers = {
        'loginController': 'controllers/login-controller'
    };

    function initialize(angular) {
        console.info("controllers.js --> initialize()");
        var angularModule = angular.module('jredmineNgApp.controllers', []);
//        angularModule.controller('loginController', ['$scope', '$injector', function($scope, $injector) {
//                require(['controllers/login-controller'], function(controller) {
//                    $injector.invoke(controller, this, {'$scope': $scope});
//                });
//            }]);
        //angularModule = angular.module('jredmineNgApp');
        Object.keys(controllers).forEach(function (name) {
            var controllerJs = controllers[name];
            if (!controllerJs) {
                return;
            }
            angularModule.controller(name,
                ["$injector", "$scope", "$_shared",
                    function ($injector, $scope, $_shared) {
                        require([controllerJs], function (controller) {
                            $injector.invoke(controller, this,
                                {
                                    'angular': angular,
                                    "name": name,
                                    "module": angularModule,
                                    "$scope": $scope,
                                    "$_shared": $_shared
                                }
                            );
                        });
                    }]);
            console.debug("controllers.js --> " + name, controllerJs);
        });
    }

    console.groupEnd();
    return {initialize: initialize};
});

