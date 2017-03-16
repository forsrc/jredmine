"use strict";


define(["angular", "console"], function(angular, console) {

    var jsName = "table-controller.js";
    console.group(jsName);
    console.time(jsName);

    console.debug("{0} --> ".formatStr([jsName]));
    
    angular.module('jredmineNgApp.routes').factory('$nutrition', ['$resource', function($resource) {

        return {
            desserts: $resource('app/data/table.data.jsonp')
        };
    }]);

    angular.module('jredmineNgApp.routes')
            .controller('tableController', ['$http', '$mdEditDialog', '$q', '$timeout', '$scope', "$mdDialog", "$nutrition", function($http, $mdEditDialog, $q, $timeout, $scope, $mdDialog, $nutrition) {

            console.debug("{0} --> function()".formatStr([jsName]), $scope);
            var bookmark;
  
            $scope.selected = [];

            $scope.filter = {
                options: {
                    debounce: 500
                }
            };

            $scope.query = {
                filter: '',
                limit: '5',
                order: 'nameToLower',
                page: 1
            };

            function success(desserts) {
                $scope.desserts = desserts;
            }

            $scope.addItem = function(event) {
                $mdDialog.show({
                    clickOutsideToClose: true,
                    controller: 'addItemController',
                    controllerAs: 'ctrl',
                    focusOnOpen: false,
                    targetEvent: event,
                    templateUrl: 'app/views/table/table-add-item-dialog.html',
                }).then($scope.getDesserts);
            };

            $scope.delete = function(event) {
                $mdDialog.show({
                    clickOutsideToClose: true,
                    controller: 'deleteController',
                    controllerAs: 'ctrl',
                    focusOnOpen: false,
                    targetEvent: event,
                    locals: {desserts: $scope.selected},
                    templateUrl: 'app/views/table/table-delete-dialog.html',
                }).then($scope.getDesserts);
            };

            $scope.getDesserts = function() {
                $scope.promise = $nutrition.desserts.get($scope.query, success).$promise;
            };

            $scope.removeFilter = function() {
                $scope.filter.show = false;
                $scope.query.filter = '';

                if ($scope.filter.form.$dirty) {
                    $scope.filter.form.$setPristine();
                }
            };

            $scope.$watch('query.filter', function(newValue, oldValue) {
                if (!oldValue) {
                    bookmark = $scope.query.page;
                }

                if (newValue !== oldValue) {
                    $scope.query.page = 1;
                }

                if (!newValue) {
                    $scope.query.page = bookmark;
                }

                $scope.getDesserts();
            });
            //$scope.$apply();
        }]);
        angular.module('jredmineNgApp.routes').controller('addItemController', ['$mdDialog', '$scope', '$nutrition', function ($mdDialog, $scope, $nutrition) {

            this.cancel = $mdDialog.cancel;

            function success(dessert) {
                $mdDialog.hide(dessert);
            }

            this.addItem = function() {
                $scope.item.form.$setSubmitted();

                if ($scope.item.form.$valid) {
                    //$nutrition.desserts.save({dessert: $scope.dessert}, success);
                }
            };

        }]);
        angular.module('jredmineNgApp.routes').controller('deleteController', ['desserts', '$mdDialog', '$scope', '$q', '$nutrition', function(desserts, $mdDialog, $scope, $q, $nutrition) {

                this.cancel = $mdDialog.cancel;

                function deleteDessert(dessert, index) {
                    var deferred = $nutrition.desserts.remove({id: dessert._id});

                    deferred.$promise.then(function() {
                        desserts.splice(index, 1);
                    });

                    return deferred.$promise;
                }

                function onComplete() {
                    $mdDialog.hide();
                }

                function error() {
                    $scope.error = 'Invalid secret.';
                }

                function success() {
                    $q.all(desserts.forEach(deleteDessert)).then(onComplete);
                }

                this.authorizeUser = function() {
                    //$authorize.get({secret: $scope.authorize.secret}, success, error);
                };

            }]);
    ;
    console.timeEnd(jsName);
    console.groupEnd();
})
;

// define(["console"], function (console) {
//     console.group("login-controller.js");
//     console.time("login-controller.js");
//
//     var controller =
//             function (angular, name, module, $scope, $_shared, loginService) {
//                 console.debug("login-controller.js --> function()", angular, name, $scope, module, $_shared, loginService);
//                 //init(angular, module);
//
//                 $scope.user = {
//                     username : "forsrc",
//                     password : "forsrc"
//                 };
//                 $scope.message = 'hello world!';
//                 // $scope.login = function () {
//                 //     $scope.dataLoading = true;
//                 //     console.info($scope.user);
//                 // };
//                 //$scope.login = loginService.login();
//                 $scope.dataLoading = false;
//                 $scope.$apply();
//             }
//         ;
//     console.timeEnd("login-controller.js");
//     console.groupEnd();
//     return controller;
// });


// define(["console"], function (console) {
//     console.group("login-controller.js");
//
//     function initialize(angular, name) {
//         console.info("login-controller.js --> initialize()", angular, name);
//         var angularModule = angular.module('jredmineNgApp');
//         console.info("login-controller.js --> angularModule", angularModule);
//         angularModule.controller("loginCtrl", [
//             '$scope', "$_shared",
//             function ($scope, $_shared) {
//                 console.info("login-controller.js --> function()", $_shared);
//                 $scope.message = 'hello world!';
//                 $scope.$apply();
//
//             }
//         ]);
//
//     }
//
//     console.groupEnd();
//     return ['angular', "name", initialize];
// });


