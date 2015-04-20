'use strict';

app.controller('TriplesGameController', function ($scope, Triples, resolvedBoard) {

    $scope.selectedCount = 0;
    $scope.board = resolvedBoard;

    $scope.selectTile = function (id) {
        $($scope.board.tiles).each(function (i, item) {
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
        $($scope.board.tiles).each(function (i, item) {
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
