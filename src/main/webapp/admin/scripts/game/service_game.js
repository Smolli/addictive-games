'use strict';

gamesApp.factory('Game', function ($resource) {
        return $resource('app/rest/games/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
