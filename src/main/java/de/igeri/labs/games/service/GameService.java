package de.igeri.labs.games.service;

import de.igeri.labs.games.domain.Board;
import de.igeri.labs.games.domain.Game;
import de.igeri.labs.games.domain.Tile;

import java.util.List;

public interface GameService {

    Game createGame();

    List<Game> listGames();

    Board getBoard(long gameId);

    List<Tile> getTiles(long id);

}
