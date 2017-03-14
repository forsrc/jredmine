'use strict';

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
            "utils",
            "angular-require",
            "angular-animate",
            "angular-material",
            "angular-material-icons",
            "angular-route",
            "angular-sanitize",
            "angular-aria",
            "angular-messages",
            "angular-message-format",
            "angular-resource",
             "angular-cookies",
            "ionic",
            "ionic-angular",
            "angular-ui-router",
            "ionic-angular",
            "ng-cordova",
            "ngStorage",
            "svg-assets-cache"
        ],
        function(angular, console, filters, directives, routes, controllers, services, shared, utils) {
            console.group("app.js");
            console.time("app.js");
            console.debug("app.js --> ", angular);

            var initialize = function(angular) {
                console.debug("app.js --> initialize()");
                // angular.element(document).ready(() => {
                angular.element(window.document).ready(function() {
                    console.debug("app.js --> angular.element(document).ready()");
                    var angularModule = angular.module("jredmineNgApp", ['ng', 'ngRequire', 'ngResource', 'ui.router', 'ionic', 'ngMaterial', 'ngCordova',
                        'ngStorage', 'ngAnimate', 'ngSanitize', 'ngMdIcons', "ngAria", 'ngMessages', "ngRoute",
                        'jredmineNgApp.utils', 'jredmineNgApp.shared', 'jredmineNgApp.filters', 'jredmineNgApp.routes',
                        'jredmineNgApp.directives', 'jredmineNgApp.controllers', 'jredmineNgApp.services'
                    ]);
                    angularModule.run(function($ionicPlatform) {
                        console.debug("app.js --> run()");
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
                    angularModule.config(function($mdThemingProvider, $mdGestureProvider) { // Angular-Material Color Theming
                        $mdGestureProvider.skipClickHijack();

                        $mdThemingProvider.theme('default')
                                .primaryPalette('red')
                                .accentPalette('blue');
                    });
                    angularModule.controller('jredmineNgAppController', function($scope) {

                    });
                    utils.initialize(angular);
                    shared.initialize(angular);
                    filters.initialize(angular);
                    routes.initialize(angular);
                    directives.initialize(angular);
                    services.initialize(angular);
                    controllers.initialize(angular);

                    angular.bootstrap(window.document, ["jredmineNgApp"]);
                    console.debug("app.js --> angular.bootstrap()");
                });
            };
            console.timeEnd("app.js");
            console.groupEnd();
            return {
                initialize: initialize
            };
        });



