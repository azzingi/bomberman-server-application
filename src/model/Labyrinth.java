package model;

import java.util.List;
import java.util.Random;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class Labyrinth {
    private List<Tile> tiles;
    private Random random;

    public Labyrinth(List<Tile> tiles) {
        this.tiles = tiles;
        random = new Random();
    }

    public Tile getRandomTile() {
        return tiles.get(random.nextInt(tiles.size()));
    }
}
