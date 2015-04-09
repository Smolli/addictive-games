package de.igeri.labs.spiele.game;

import de.igeri.labs.spiele.game.Tile;
import java.util.Random;

class TileFactory {

    public static final int DIMENSIONS = 4;
    public static final int WIDTH = 3;
    
    private static final Random RANDOM = new Random();

    static Tile createRandom() {
        Tile tile = new Tile(DIMENSIONS);

        for (int i = 0; i < DIMENSIONS; i++) {
            tile.setValue(i, RANDOM.nextInt(WIDTH)); 
        }
        
        return tile;
    }

}
