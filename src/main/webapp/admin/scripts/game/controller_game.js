'use strict';

gamesApp.controller('GameController', function ($scope, resolvedGame, Game, resolvedUser) {

        $scope.games = resolvedGame;
        $scope.Users = resolvedUser;

        $scope.create = function () {
            Game.save($scope.game,
                function () {
                    $scope.games = Game.query();
                    $('#saveGameModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.game = Game.get({id: id});
            $('#saveGameModal').modal('show');
        };

        $scope.delete = function (id) {
            Game.delete({id: id},
                function () {
                    $scope.games = Game.query();
                });
        };

        $scope.clear = function () {
            $scope.game = {started: null, id: null};
        };
    });
