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

define(["angular", "console", "angular-require"],function(angular, console) {
    console.group("routes.js");
    console.time("routes.js");
    function initialize(angular) {
        console.info("routes.js --> initialize()");
        var routesModule = angular.module('jredmineNgApp.routes', ['ngRequire', 'ui.router']);
            routesModule.config(["$stateProvider", "$urlRouterProvider", "$requireProvider", function ($stateProvider, $urlRouterProvider, $requireProvider) {
                $urlRouterProvider.otherwise("/login");

                $stateProvider.state("login", {
                    url: "/login",
                    templateUrl: "app/views/login/login.html",
                    title: "Login",
                    controller: "loginController",
                    //controllerAs: "login",
                    resolve: {
                        deps: $requireProvider.requireJS([
                            'views/login/login-service',
                            'views/login/login-controller'
                        ]),
                        //css: $requireProvider.requireCSS([
                        //    'css!../css/login.css'
                        //])
                    }
                });

            }]);
    }
    console.timeEnd("routes.js");
    console.groupEnd();
    return {initialize: initialize};
});