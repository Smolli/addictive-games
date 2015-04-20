'use strict';

gamesApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/game', {
                    templateUrl: 'views/games.html',
                    controller: 'GameController',
                    resolve:{
                        resolvedGame: ['Game', function (Game) {
                            return Game.query();
                        }],
                        resolvedUser: ['User', function (User) {
                            return User.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
