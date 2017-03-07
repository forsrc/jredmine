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

define([], function () {

    function initialize(app) {
        app.factory('users', function() {
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
    }

    return {initialize: initialize};
});
