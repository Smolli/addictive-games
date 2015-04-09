package de.igeri.labs.spiele.game;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Board implements Serializable {

    private Collection<Tile> tiles = new HashSet<>();

    public static Board init() {
        Board board = new Board();

        while (board.hasNoSolution()) {
            board.addTiles(3);
        }

        return board;
    }

    void print() {
        Iterator<Tile> iterator = tiles.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
            System.out.print(iterator.next() + " ");
            System.out.print(iterator.next());
            System.out.println();
        }
    }

    private boolean hasNoSolution() {
        if (tiles.isEmpty()) {
            return true;
        }

        int[][] matrix = new int[TileFactory.DIMENSIONS][];
        for (int i = 0; i < TileFactory.DIMENSIONS; i++) {
            matrix[i] = new int[tiles.size()];
        }

        int n = 0;
        for (Tile tile : this.tiles) {
            for (int i = 0; i < TileFactory.DIMENSIONS; i++) {
                matrix[i][n] = tile.getValue(i);
            }
            n++;
        }

        int dims = 0;
        for (int d = 0; d < TileFactory.DIMENSIONS; d++) {
            int test = 0;
            for (int w = 1; w < tiles.size(); w++) {
                test |= 1 << matrix[d][w];
            }
            if (Integer.bitCount(test) == 1 || Integer.bitCount(test) == TileFactory.WIDTH) {
                dims++;
            }
        }

        return dims != TileFactory.DIMENSIONS;
    }

    private void addTiles(int tileCount) {
        for (int i = 0; i < tileCount; i++) {
            this.tiles.add(TileFactory.createRandom());
        }
    }

    public Collection<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(Collection<Tile> tiles) {
        this.tiles = tiles;
    }

}
