package de.igeri.labs.games.game;

import de.igeri.labs.games.domain.Tile;

import java.security.SecureRandom;
import java.util.Random;

public final class TileFactory {

    public static final int DIMENSIONS = 4;
    public static final int WIDTH = 3;

    private static final Random RANDOM = new SecureRandom();

    private TileFactory() {
        super();
    }

    public static Tile createRandom() {
        final Tile tile = new Tile(/*TileFactory.DIMENSIONS*/);

        for (int i = 0; i < TileFactory.DIMENSIONS; i++) {
            tile.setValue(i, TileFactory.RANDOM.nextInt(TileFactory.WIDTH));
        }

        return tile;
    }

}
