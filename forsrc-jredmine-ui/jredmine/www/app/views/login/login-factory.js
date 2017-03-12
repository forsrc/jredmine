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
    var jsName = "login-factory.js";
    console.group(jsName);
    console.time(jsName);

    function initialize(angular, module) {
        console.debug("{0} --> initialize()".formatStr([jsName]));

        module.factory("loginFactory", [
            '$http', '$scope', "$_shared",
            function ($http, $scope, $_shared) {
                console.info("{0} --> function()".formatStr([jsName]), $_shared);

            }
        ]);

    }

    console.timeEnd(jsName);
    console.groupEnd();
    return {initialize: initialize};
});