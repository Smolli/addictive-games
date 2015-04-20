'use strict';

app.config(function ($routeProvider) {
    $routeProvider
        .otherwise({
            templateUrl: 'views/games-view.html',
            controller: 'GamesController'
        });
});
