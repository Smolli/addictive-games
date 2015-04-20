'use strict';

app.config(function ($routeProvider) {
    $routeProvider
        .when('/Triples', {
            templateUrl: 'views/game/Triples/running-games-view.html',
            controller: 'TriplesController',
            resolve: {
                resolvedGames: ['Triples', function (Triples) {
                    return Triples.query();
                }]
            }
        })
        .when('/TriplesGame/:id', {
            templateUrl: 'views/game/Triples/game-view.html',
            controller: 'TriplesGameController',
            resolve: {
                resolvedBoard: ['TriplesGame', '$route', function (TriplesGame, $route) {
                    return TriplesGame.get({gameId: $route.current.params.id});
                }]
            }
        });
});


app.factory('Triples', function ($resource) {
    return $resource('game/triples', {}, {
        'query': {method: 'GET', isArray: true},
        //'get': {method: 'GET'}
    });
});
app.factory('TriplesGame', function ($resource) {
    return $resource('game/triples/:gameId', {gameId: '@id'}, {
        'get': {method: 'GET'}
    });
});

app.controller('TriplesController', function ($scope, Triples, resolvedGames, $location) {

    $scope.games = resolvedGames;

    $scope.newGame = function () {
        Triples.save($scope.game, function () {
            $scope.games = Triples.query();
        });
    };

    $scope.clear = function () {
        $scope.game = {};
    };

    $scope.play = function (id) {
        $location.path('TriplesGame/' + id);
    };
});

app.controller('TriplesGameController', function ($scope, Triples, resolvedBoard) {

    $scope.selectedCount = 0;
    $scope.board = resolvedBoard;

    $scope.selectTile = function (id) {
        $($scope.tiles).each(function (i, item) {
            if (item.id === id) {
                item.selected = !(item.selected || false);
                if (item.selected) {
                    $scope.selectedCount++;
                } else {
                    $scope.selectedCount--;
                }

                if ($scope.selectedCount >= 3) {
                    $scope.checkTriple();
                }
            }
        });
    };

    $scope.checkTriple = function () {
        var tiles = $([]);
        $($scope.tiles).each(function (i, item) {
            if (item.selected) {
                tiles.push(item);
            }
        });
        Triples.check({tiles: tiles}, function () {
            // success
        }, function (data) {
            // error
            alert(data);
        });
    };

    var uncheckAll = function () {
        $($scope.tiles).each(function (i, item) {
            item.selected = false;
        });
        $scope.selectedCount = 0;
    };

});
