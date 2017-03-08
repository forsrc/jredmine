"use strict";
/**
(function (angular) {
    angular
        .module('jredmine.services', [])
        .controller('loginController', ['$scope', 'login', function ($scope, notify) {
            $scope.login = function (){
                login($scope.username, $scope.password);
            }
            ;
        }])
        .factory('login', ['$window', function (window) {
            return function (username, password) {
                window.alert(username);
            };
        }]);

    ;
})(angular);
*/
define([], function() {
    function initialize(angular) {
        angular
                .module('jredmineNgApp.loginService', [])
                .controller('loginController', ['$scope', 'login', function($scope, notify) {
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