"use strict";

angular.module('jredmineNgApp', ['ionic', 'jredmine.controllers', 'jredmine.services'])

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
    })

    .config(function ($stateProvider, $urlRouterProvider) {

        $stateProvider

            .state('login', {
                url: '/login',
                abstract: true,
                templateUrl: 'app/views/login.html'
            })


        ;
        // if none of the above states are matched, use this as the fallback
        $urlRouterProvider.otherwise('/login/login');

    });
