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

define([], function () {

    function initialize(app) {
        app.config(["$stateProvider", "$urlRouterProvider", function ($stateProvider, $urlRouterProvider) {
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

    return {initialize: initialize};
});