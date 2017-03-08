"use strict";

define([], function() {
    function initialize(angular) {
        angular
                .module('jredmineNgApp.loginService', [])
                .controller('loginController', ['$scope', 'login', function($scope, login) {
                    $scope.login = function() {
                        login($scope.username, $scope.password);
                    }
                    ;
                }])
                .factory('login', ['$window', function(window) {
                    return function(username, password) {
                        window.alert(username);
                    };
                }]);
    }
    return {initialize: initialize};
});