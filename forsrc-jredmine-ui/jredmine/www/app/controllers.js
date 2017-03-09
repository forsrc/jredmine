"use strict";


define(["angular", "console"], function(angular, console) {
    console.group("controllers.js");
    console.info("controllers.js --> ");
    var controllers = {
        'loginController': 'controllers/login-controller'
    };
    function initialize(angular) {
        console.info("controllers.js --> initialize()");
        var angularModule = angular.module('jredmineNgApp.controllers', ['ngMaterial', 'ngMessages']);
//        angularModule.controller('loginController', ['$scope', '$injector', function($scope, $injector) {
//                require(['controllers/login-controller'], function(controller) {
//                    $injector.invoke(controller, this, {'$scope': $scope});
//                });
//            }]);
        Object.keys(controllers).forEach(function(name) {
            var controllerJs = controllers[name];
            if (!controllerJs) {
                return;
            }
            angularModule.controller(name,
                    ['$scope', '$injector', '$_shared', '$http',
                        function($scope, $injector, $_shared, $http) {
                            require([controllerJs], function(controller) {
                                $injector.invoke(controller, this,
                                        {
                                            '$scope': $scope,
                                            '$_shared': $_shared,
                                            '$http': $http
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

