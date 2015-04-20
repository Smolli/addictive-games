package de.igeri.labs.games.service;

import de.igeri.labs.games.domain.Board;
import de.igeri.labs.games.domain.Tile;

import java.util.Collection;

public interface BoardService {
    Collection<Tile> createTiles();
}
