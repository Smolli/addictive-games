package de.igeri.labs.games.service;

import de.igeri.labs.games.domain.Tile;
import de.igeri.labs.games.game.TileFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class BoardServiceImpl implements BoardService{

    @Override
    public Collection<Tile> createTiles() {
        Collection<Tile> tiles = new HashSet<>();

        while (this.hasNoSolution(tiles) || tiles.size() < 6) {
            this.addTiles(tiles, 3);
        }

        return tiles;
    }

    private boolean hasNoSolution(Collection<Tile> tiles) {
        if (tiles.isEmpty()) {
            return true;
        }

        int[][] matrix = new int[TileFactory.DIMENSIONS][];
        for (int i = 0; i < TileFactory.DIMENSIONS; i++) {
            matrix[i] = new int[tiles.size()];
        }

        int n = 0;
        for (Tile tile : tiles) {
            for (int i = 0; i < TileFactory.DIMENSIONS; i++) {
                matrix[i][n] = tile.getValue(i);
            }
            n++;
        }

        int dims = 0;
        for (int d = 0; d < TileFactory.DIMENSIONS; d++) {
            int test = 0;
            for (int w = 0; w < tiles.size(); w++) {
                test |= 1 << matrix[d][w];
            }
            if (Integer.bitCount(test) == 1 || Integer.bitCount(test) == TileFactory.WIDTH) {
                dims++;
            }
        }

        return dims != TileFactory.DIMENSIONS;
    }

    private void addTiles(Collection<Tile> tiles, int tileCount) {
        for (int i = 0; i < tileCount; i++) {
            tiles.add(TileFactory.createRandom());
        }
    }

}
