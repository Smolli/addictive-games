'use strict';

app.factory('Triples', function ($resource) {
    return $resource('game/triples/:gameId', {gameId: '@id'}, {
        'get': {method: 'GET'},
        'query': {method: 'GET', isArray: true}
    });
});
