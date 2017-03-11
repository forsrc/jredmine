"use strict";

define(
        [
            "angular",
            "console"
        ],
        function(angular, console) {
            console.group("services.js");
            console.time("services.js");
            console.debug("services.js --> ");
            var services = {
                "loginService": "services/login-service"
            };
            var factories = {
                "login-factory": "services/login-factory"
            };
            function initialize(angular) {
                console.debug("services.js --> initialize()");
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
                    angularModule.factory(name,
                            ['$scope', '$injector', '$_shared', '$http',
                                function($scope, $injector, $_shared, $http) {
                                    require([factoryJs], function(service) {
                                        $injector.invoke(service, this,
                                                {
                                                    'angular': angular,
                                                    "name": name,
                                                    "module": angularModule,
                                                    "$scope": $scope,
                                                    "$_shared": $_shared,
                                                    '$http': $http
                                                }
                                        );
                                    });
                                }]);
                    console.debug("services.js --> " + name, factories);
                });
            }
            console.timeEnd("services.js");
            console.groupEnd();
            return {initialize: initialize};
        });
