"use strict";

/**
 (function(angular) {
 angular
 .module("jredmineNgApp")
 .config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider) {
 $urlRouterProvider.otherwise("/login");
 
 $stateProvider.state("login", {
 url: "/login",
 templateUrl: "app/views/login.html",
 title: "Login",
 controller: "loginController",
 controllerAs: "login"
 });
 
 }]);
 
 })(angular);
 */

define(["angular", "console", "angular-require", "css"], function(angular, console) {
    console.group("routes.js");
    console.time("routes.js");
    function initialize(angular) {
        console.info("routes.js --> initialize()");
        var routesModule = angular.module('jredmineNgApp.routes', ['ionic', 'ngRequire', 'ui.router', "ngResource","ngRoute","ngCookies", 'ngMaterial', 'md.data.table']);
        routesModule.config(["$stateProvider", "$urlRouterProvider", "$requireProvider", "$mdThemingProvider", function($stateProvider, $urlRouterProvider, $requireProvider, $mdThemingProvider) {
                $mdThemingProvider
                        .theme('default')
                        .primaryPalette('blue')
                        .accentPalette('pink');
                $urlRouterProvider.otherwise("/login");

                $stateProvider
                .state("login", {
                    url: "/login",
                    templateUrl: "app/views/login/login.html",
                    title: "Login",
                    controller: "loginController",
                    //controllerAs: "login",
                    resolve: {
                        // css: $requireProvider.requireCSS([
                        //     'css!css/login.css'
                        // ]),
                        deps: $requireProvider.requireJS([
                            'views/login/login-service',
                            'views/login/login-controller'
                        ])
                    }
                })
                .state("home", {
                    url: "/home",
                    templateUrl: "app/views/home/home.html",
                    title: "Home",
                    controller: "homeController",
                    //controllerAs: "home",
                    resolve: {
                        // css: $requireProvider.requireCSS([
                        //     'css!css/login.css'
                        // ]),
                        deps: $requireProvider.requireJS([
                            'views/home/home-service',
                            'views/home/home-controller'
                        ])
                    }
                })
                .state('tab', {
                    url: '/tab',
                    abstract: true,
                    templateUrl: 'app/views/tab/tab.html',
                    title: "Tab",
                    controller: "tabController",
                    //controllerAs: "home",
                    resolve: {
                        // css: $requireProvider.requireCSS([
                        //     'css!css/login.css'
                        // ]),
                        deps: $requireProvider.requireJS([
                            //'views/tab/tab-service',
                            'views/tab/tab-controller'
                        ])
                    }
                })
                .state('menu', {
                    url: '/menu',
                    abstract: true,
                    templateUrl: 'app/views/menu/menu.html',
                    title: "Menu",
                    controller: "menuController",
                    //controllerAs: "home",
                    resolve: {
                        // css: $requireProvider.requireCSS([
                        //     'css!css/login.css'
                        // ]),
                        deps: $requireProvider.requireJS([
                            //'views/menu/menu-service',
                            'views/menu/menu-controller'
                        ])
                    }
                })
                .state("menu.home1", {
                    url: "/home1",
                    views: {
                        'menuContent': {
                            templateUrl: 'app/views/home/home.html',
                            controller: "homeController"
                        }
                    },
                    //controller: "homeController",
                    //templateUrl: "app/views/home/home.html",
                    title: "Home",
                    //controllerAs: "home",
                    resolve: {
                        // css: $requireProvider.requireCSS([
                        //     'css!css/login.css'
                        // ]),
                        deps: $requireProvider.requireJS([
                            'views/home/home-service',
                            'views/home/home-controller'
                        ])
                    }
                })
                .state("tab.home2", {
                    url: "/home2",
                    views: {
                        'tab-account': {
                            templateUrl: 'app/views/home/home.html',
                            controller: "homeController"
                        }
                    },
                    //controller: "homeController",
                    //templateUrl: "app/views/home/home.html",
                    title: "Home",
                    //controllerAs: "home",
                    resolve: {
                        // css: $requireProvider.requireCSS([
                        //     'css!css/login.css'
                        // ]),
                        deps: $requireProvider.requireJS([
                            'views/home/home-service',
                            'views/home/home-controller'
                        ])
                    }
                })
                .state("table", {
                    url: "/table",
                    templateUrl: "app/views/table/table.html",
                    title: "Table",
                    controller: "tableController",
                    //controllerAs: "login",
                    resolve: {
                        // css: $requireProvider.requireCSS([
                        //     'css!css/login.css'
                        // ]),
                        deps: $requireProvider.requireJS([
                            'views/table/table-controller'
                        ])
                    }
                })
                ;

            }]);
    }

    console.timeEnd("routes.js");
    console.groupEnd();
    return {initialize: initialize};
});