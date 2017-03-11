"use strict";


define(["angular", "console", "angular-require"
], function (angular, console) {
    console.group("controllers.js");
    console.time("controllers.js");
    var controllers = {
        //'loginController': 'views/login/login-controller'
    };

    function initialize(angular) {
        console.debug("controllers.js --> initialize()");
        var angularModule = angular.module('jredmineNgApp.controllers', []);
//        angularModule.controller('loginController', ['$scope', '$injector', function($scope, $injector) {
//                require(['controllers/login-controller'], function(controller) {
//                    $injector.invoke(controller, this, {'$scope': $scope});
//                });
//            }]);
        Object.keys(controllers).forEach(function (name) {
            var controllerJs = controllers[name];
            if (!controllerJs) {
                return;
            }

            angularModule.controller(name,
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
                    });
            console.debug("controllers.js --> " + name, controllerJs);
        });
    }

    console.timeEnd("controllers.js");
    console.groupEnd();
    return {initialize: initialize};
});

