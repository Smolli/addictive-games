'use strict';

var app = angular.module('app', ['http-auth-interceptor', 'ngRoute', 'ngResource']);

/* Constants */
app.constant('USER_ROLES', {
    'all': '*',
    'admin': 'ROLE_ADMIN',
    'user': 'ROLE_USER'
});


app.run(function (Session, $rootScope, $location, AuthenticationSharedService, USER_ROLES) {

    $rootScope.$on('$routeChangeStart', function (event, next) {
        $rootScope.isAuthorized = AuthenticationSharedService.isAuthorized;
        $rootScope.userRoles = USER_ROLES;
        AuthenticationSharedService.valid(next.access.authorizedRoles);
    });

    // Call when the 401 response is returned by the server
    $rootScope.$on('event:auth-loginRequired', function (rejection) {
        Session.invalidate();
        $rootScope.authenticated = false;
        if ($location.path() !== '/' && $location.path() !== '' && $location.path() !== '/register' &&
            $location.path() !== '/activate' && $location.path() !== '/login') {
            var redirect = $location.path();
            $location.path('/login').search('redirect', redirect).replace();
        }
    });

    // Call when the 403 response is returned by the server
    $rootScope.$on('event:auth-notAuthorized', function (rejection) {
        $rootScope.errorMessage = 'errors.403';
        $location.path('/error').replace();
    });

    // Call when the user logs out
    $rootScope.$on('event:auth-loginCancelled', function () {
        $location.path('');
    });

    // Call when the the client is confirmed
    $rootScope.$on('event:auth-loginConfirmed', function (data) {
        $rootScope.authenticated = true;
        //console.log(data);
        if ($location.path() === '/login') {
            var search = $location.search();
            if (search.redirect !== undefined) {
                $location.path(search.redirect).search('redirect', null).replace();
            } else {
                $location.path('/').replace();
            }
        }
    });

});
