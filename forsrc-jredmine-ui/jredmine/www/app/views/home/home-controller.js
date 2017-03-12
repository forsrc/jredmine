define(["angular", "console", "utils", 'views/home/home-service'], function (angular, console, utils, homeService) {
    var jsName = "home-controller.js";
    console.group(jsName);
    console.time(jsName);
    console.debug("{0} --> ".formatStr([jsName]), homeService);

    angular.module('jredmineNgApp.routes')
        .controller('homeController', function ($scope, homeService) {
            console.debug("{0} --> function()".formatStr([jsName]), $scope, homeService);
            $scope.dataLoading = false;
            //$scope.$apply();
        });
    console.timeEnd(jsName);
    console.groupEnd();
});