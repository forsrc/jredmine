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
    console.info("routes.js -->");
    function initialize(angular) {
        console.info("routes.js --> initialize()");
        angular
                .module('jredmineNgApp.routes', [])
                .config(["$stateProvider", "$urlRouterProvider", function ($stateProvider, $urlRouterProvider) {
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
    console.groupEnd();
    return {initialize: initialize};
});