"use strict";
/**
(function(angular) {
    angular
            .module('jredmine.services', [])
            .factory('users', function() {
                var users = [{}];
                return {
                    all: function() {
                        return users;
                    }
                    , get: function(id) {
                        return users.filter(function(item) {
                            tem.id === id;
                        });
                    }
                };
            })

            ;
})(angular);
 */

define(["angular", "console", "services/login-service"],function(angular, console, loginService) {
    console.group("services.js");
    console.info("services.js --> ");
    function initialize(angular) {
        console.info("services.js --> initialize()");
        angular
                .module('jredmineNgApp.services', [])
                .factory('users', function() {
                    var users = [{}];
                    return {
                        all: function() {
                            return users;
                        }
                        , get: function(id) {
                            return users.filter(function(item) {
                                item.id === id;
                            });
                        }
                };
        })
        ;
        loginService.initialize(angular);
    }
    console.groupEnd();
    return {initialize: initialize};
});
