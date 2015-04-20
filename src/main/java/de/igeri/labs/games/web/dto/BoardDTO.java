package de.igeri.labs.games.web.dto;

import de.igeri.labs.games.domain.Board;
import de.igeri.labs.games.domain.Tile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoardDTO implements Serializable {

    private long id;
    private int remaining;
    private List<Tile> tiles;

    public BoardDTO(Board board, List<Tile> tiles) {
        this.id = board.getId();
        this.remaining = board.getCardsRemaining();
        this.tiles = new ArrayList<>(tiles);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRemaining() {
        return this.remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public List<Tile> getTiles() {
        return this.tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }
}
