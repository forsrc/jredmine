"use strict";


require.config({
    paths: {
        "angular": "../js/lib/angular-1.6.2/angular",
        "angular-animate": "../js/lib/angular-1.6.2/angular-animate",
        "angular-aria": "../js/lib/angular-1.6.2/angular-aria",
        "angular-cookies": "../js/lib/angular-1.6.2/angular-cookies",
        "angular-loader": "../js/lib/angular-1.6.2/angular-loader",
        "angular-messages": "../js/lib/angular-1.6.2/angular-messages",
        "angular-message-format": "../js/lib/angular-1.6.2/angular-message-format",
        "angular-mocks": "../js/lib/angular-1.6.2/angular-mocks",
        "angular-parse-ext": "../js/lib/angular-1.6.2/angular-parse-ext",
        "angular-resource": "../js/lib/angular-1.6.2/angular-resource",
        "angular-route": "../js/lib/angular-1.6.2/angular-route",
        "angular-sanitize": "../js/lib/angular-1.6.2/angular-sanitize",
        "angular-scenario": "../js/lib/angular-1.6.2/angular-scenario",
        "angular-touch": "../js/lib/angular-1.6.2/angular-touch",
        "angular-material": "../js/lib/angular-material/angular-material",
        "angular-material-icons": "../js/lib/angular-material/angular-material-icons",
        "ng-cordova": "../js/lib/ng/ng-cordova.min",
        "ngStorage": "../js/lib/ng/ngStorage.min",
        "angular-ui-router": "../js/lib/ng/angular-ui-router",
        "svg-assets-cache": "../js/lib/svg/svg-assets-cache",
        "ionic": "../lib/ionic/js/ionic",
        "ionic-angular": "../lib/ionic/js/ionic-angular",
        "console-min": "../js/lib/console/console-min",
        "console": "../js/lib/console/console",
        "order": "../js/lib/require/order",
        "css": "../js/lib/require/order",
        "text": "../js/lib/require/text",
        "app": "app",
        "filters": "filters",
        "controllers": "controllers",
        "services": "services",
        "routes": "routes",
        "directives": "directives",
        "shared-service": "shared/shared-service",
        "loginService": "services/login-service",
        "utils": "utils/angular-utils",
    },
    shim: {
        angular: {
            exports: "angular"
        },
        ionic: {
            exports: "ionic",
            deps: ["angular"]
        },
        "angular-animate": {
            deps: ["angular"]
        },
        "angular-sanitize": {
            deps: ["angular"]
        },
        "angular-aria": {
            deps: ["angular"]
        },
        "angular-messages": {
            deps: ["angular"]
        },
        "angular-message-format": {
            deps: ["angular"]
        },
        "angular-material": {
            deps: ["angular"]
        },
        "angular-material-icons": {
            deps: ["angular"]
        },
        "angular-route": {
            deps: ["angular"]
        },
        "ionic-angular": {
            deps: ["angular", "ionic"]
        },
        "ng-cordova": {
            deps: ["angular"]
        },
        "angular-ui-router": {
            deps: ["angular"]
        },
        "app": {
            deps: ["angular"]
        },
        "routes": {
            deps: ["angular"]
        },
        "controllers": {
            deps: ["angular"]
        },
        "utils": {
            deps: ["angular"]
        },
        "console": {
            deps: ["console-min", "order"]
        }
    },
    priority: [
        "angular"
    ]//,
            //baseUrl: '/'
});


require(["app", "angular"],
        function(App) {
            App.initialize();
        }
);

