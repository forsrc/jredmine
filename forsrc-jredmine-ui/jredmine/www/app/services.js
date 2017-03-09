"use strict";

define(
        [
            "angular",
            "console"
        ],
        function(angular, console) {
            console.group("services.js");
            console.info("services.js --> ");
            var services = {
                "loginService": "services/login-service"
            };
            var factories = {
            };
            function initialize(angular) {
                console.info("services.js --> initialize()");
                var angularModule = angular.module('jredmineNgApp.services', ["jredmineNgApp.shared"]);
                Object.keys(services).forEach(function(name) {
                    var serviceJs = services[name];
                    if (!serviceJs) {
                        return;
                    }
                    angularModule.service(name,
                            ['$scope', '$injector', '$_shared', '$http',
                                function($scope, $injector, $_shared, $http) {
                                    require([serviceJs], function(service) {
                                        $injector.invoke(service, this,
                                                {
                                                    '$scope': $scope,
                                                    '$_shared': $_shared,
                                                    '$http': $http
                                                }
                                        );
                                    });
                                }]);
                    console.debug("services.js --> " + name, services);
                });
                Object.keys(factories).forEach(function(name) {
                    var factoryJs = factories[name];
                    if (!factoryJs) {
                        return;
                    }
                    angularModule.factoryJs(name,
                            ['$scope', '$injector', '$_shared', '$http',
                                function($scope, $injector, $_shared, $http) {
                                    require([factoryJs], function(service) {
                                        $injector.invoke(service, this,
                                                {
                                                    '$scope': $scope,
                                                    '$_shared': $_shared,
                                                    '$http': $http,
                                                    '$_angularModule': angularModule
                                                }
                                        );
                                    });
                                }]);
                    console.debug("services.js --> " + name, factories);
                });
            }
            console.groupEnd();
            return {initialize: initialize};
        });
