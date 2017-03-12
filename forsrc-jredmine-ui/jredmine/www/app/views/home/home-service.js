define(["angular", "console", "utils", 'views/home/home-service'], function (angular, console, utils) {
    var jsName = "home-service.js";
    console.group(jsName);
    console.time(jsName);
    console.debug("{0} --> ".formatStr([jsName]));

    angular.module('jredmineNgApp.routes')
        .service('homeService', function () {
            console.debug("{0} --> function()".formatStr([jsName]));

        });
    console.timeEnd(jsName);
    console.groupEnd();
});