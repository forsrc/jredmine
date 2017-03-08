'use strict';

/**
 (function(angular) {
 
 angular
 .module('jredmineNgApp', ['ionic', 'ui.router', 'ngMaterial', 'ngCordova', 'ngStorage',
 'jredmineNgApp.filters', 'jredmine.controllers', 'jredmine.services'
 ])
 .run(function($ionicPlatform) {
 $ionicPlatform.ready(function() {
 // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
 // for form inputs)
 if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
 cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
 cordova.plugins.Keyboard.disableScroll(true);
 
 }
 if (window.StatusBar) {
 // org.apache.cordova.statusbar required
 StatusBar.styleDefault();
 }
 });
 });
 
 
 })(angular);*/

define(
        [
            "angular",
            "console",
            "filters",
            "directives",
            "routes",
            "controllers",
            "services",
            "shared-service",
            "angular-animate",
            "angular-material",
            "angular-route",
            "angular-sanitize",
            "angular-aria",
            "angular-messages",
            "angular-message-format",
            "angular-resource",
            "ionic",
            "ionic-angular",
            "angular-ui-router",
            "ionic-angular",
            "ng-cordova",
            "ngStorage",
             "svg-assets-cache"

        ],
        function(angular, console, filters, directives, routes, controllers, services, shared) {
            console.group("app.js");
            console.info("app.js --> ", angular);
            if (!window.angular) {
                window.angular = angular;
            }
            var initialize = function() {
                console.info("app.js --> initialize()");
                // angular.element(document).ready(() => {
                angular.element(document).ready(function() {
                    console.info("app.js --> angular.element(document).ready()");
                    angular
                            .module('jredmineNgApp', ['ngResource', 'ui.router', 'ionic', 'ngMaterial', 'ngCordova', 'ngStorage',
                            'jredmineNgApp.shared', 'jredmineNgApp.filters', 'jredmineNgApp.routes', 'jredmineNgApp.directives', 'jredmineNgApp.controllers', 'jredmineNgApp.services'
                    ])
                            .run(function($ionicPlatform) {
                                console.info("app.js --> run()");
                                $ionicPlatform.ready(function() {
                                    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
                                    // for form inputs)
                                    if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
                                        cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
                                        cordova.plugins.Keyboard.disableScroll(true);

                                    }
                                    if (window.StatusBar) {
                                        // org.apache.cordova.statusbar required
                                        StatusBar.styleDefault();
                                    }
                                });
                            });
                    shared.initialize(angular);
                    filters.initialize(angular);
                    routes.initialize(angular);
                    controllers.initialize(angular);
                    services.initialize(angular);
                    directives.initialize(angular);

                    angular.bootstrap(document, ["jredmineNgApp"]);
                    console.info("app.js --> angular.bootstrap()");
                });
            };
            console.groupEnd();
            return {
                initialize: initialize
            };
        });



