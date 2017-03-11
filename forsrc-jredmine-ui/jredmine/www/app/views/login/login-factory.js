"use strict";

// define(["console"], function (console) {
//     console.group("login-factory.js");
//
//     function initialize(angular, name, module) {
//         console.info("login-factory.js --> initialize()", angular, name, module);
//         module.factory('login', function ($http, session) {
//             console.info("login-factory.js --> initialize()", angular, name, module);
//         });
//     }
//
//     console.groupEnd();
//     return ['angular', "name", "module", initialize];
// });


define(["angular", "console"], function (angular, console) {
    console.group("login-factory.js");
    console.time("login-factory.js");

    function initialize(angular, module) {
        console.debug("login-factory.js --> initialize()");

        module.factory("loginFactory", [
            '$http', '$scope', "$_shared",
            function ($http, $scope, $_shared) {
                console.info("login-factory.js --> function()", $_shared);

            }
        ]);

    }

    console.timeEnd("login-factory.js");
    console.groupEnd();
    return {initialize: initialize};
});