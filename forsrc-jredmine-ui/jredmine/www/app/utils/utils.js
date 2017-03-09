"use strict";

define([], function(console) {
    //console.group("utils.js");
    function forEach(json, callback) {
        //console.debug("controllers.js --> forEach()", json, callback);
        Object.keys(json).forEach(function(key) {
            var value = json[key];
            if (!value) {
                return;
            }
            callback(value);
        });
    }
    //console.groupEnd();
    return {
        forEach: forEach
    };
});