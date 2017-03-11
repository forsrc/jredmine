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

define(["angular", "console"],function(angular, console) {
    console.group("routes.js");
    console.time("routes.js");
    function initialize(angular) {
        console.info("routes.js --> initialize()");
        var routesModule = angular.module('jredmineNgApp.routes', []);
            routesModule.config(["$stateProvider", "$urlRouterProvider", function ($stateProvider, $urlRouterProvider) {
                $urlRouterProvider.otherwise("/login");

                $stateProvider.state("login", {
                    url: "/login",
                    templateUrl: "app/views/login.html",
                    title: "Login",
                    controller: "loginController",
                    controllerAs: "login"
                });

            }]);
    }
    console.timeEnd("routes.js");
    console.groupEnd();
    return {initialize: initialize};
});