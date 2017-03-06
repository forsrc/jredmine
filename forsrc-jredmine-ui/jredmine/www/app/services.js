"use strict";

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
