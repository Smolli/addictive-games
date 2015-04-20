'use strict';

app.config(function ($routeProvider) {
    $routeProvider
        .when('/Triples/:id', {
            templateUrl: 'views/game/Triples/game-view.html',
            controller: 'TriplesGameController',
            resolve: {
                resolvedBoard: ['Triples', '$route', function (Triples, $route) {
                    return Triples.get({gameId: $route.current.params.id});
                }]
            }
        })
        .when('/Triples', {
            templateUrl: 'views/game/Triples/overview-view.html',
            controller: 'TriplesController',
            resolve: {
                resolvedGames: ['Triples', function (Triples) {
                    return Triples.query();
                }]
            }
        })
    ;
});
