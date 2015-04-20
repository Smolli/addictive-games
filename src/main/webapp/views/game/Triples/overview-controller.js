'use strict';

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
        $location.path('Triples/' + id);
    };
});
