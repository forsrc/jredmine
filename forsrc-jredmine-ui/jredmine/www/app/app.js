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
        "filters",
        "directives",
        "routes",
        "controllers",
        "services",
        "angular-animate",
        "angular-material",
        "angular-route",
        "angular-sanitize",
        "angular-aria",
        "angular-messages",
        "angular-message-format",
        "ionic",
        "ionic-angular",
        "angular-ui-router",
        "ng-cordova",
        "ngStorage"

    ],

    function init(angular, filters, directives, routes, controllers, services, angularAnimate, angularMaterial) {
        var initialize = function () {

            var app = angular
                .module('jredmineNgApp', ['ionic', 'ui.router', 'ngMaterial', 'ngCordova', 'ngStorage',
                    'jredmineNgApp.filters', 'jredmineNgApp.controllers', 'jredmineNgApp.services'
                ])
                .run(function ($ionicPlatform) {
                    $ionicPlatform.ready(function () {
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

            if(!window.angular){
                window.angular = angular;
            }
            filters.initialize(angular.module('jredmineNgApp.filters', []));
            routes.initialize(angular.module('jredmineNgApp.routes', []));
            controllers.initialize(angular.module('jredmineNgApp.controllers', ['ngMaterial', 'ngMessages']));
            services.initialize(angular.module('jredmineNgApp.services', []));
            //angular.module('jredmineNgApp.services', []).factory(services);
            angular.module('jredmineNgApp.directives', []).directive(directives);

            angular.bootstrap(document, ["jredmineNgApp"]);

        };
        return {
            initialize: initialize
        };
    });



