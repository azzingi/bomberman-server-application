package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

    public Tile getRandomTile(List<Player> players) {
        ArrayList<Tile> occupiedTiles = new ArrayList<>();
        ArrayList<Integer> unoccupiedTiles = new ArrayList<>();

        for (Player player : players) {
            occupiedTiles.add(player.getTile());
        }

        int index = 0;
        int maxX = 0;
        int maxY = 0;

        for (Tile tile : tiles) {
            if (tile.getX() > maxX) {
                maxX = tile.getX();
            }
            if (tile.getY() > maxY) {
                maxY = tile.getY();
            }
        }

        for (Tile tile : tiles) {
            if ((tile.getX() == 0 || tile.getX() == maxX) && (tile.getY() == 0 || tile.getY() == maxY)) {
                boolean notOccupied = true;
                for (Tile occ : occupiedTiles) {
                    notOccupied = !(occ.getX() == tile.getX() && occ.getY() == tile.getY());
                }
                if (notOccupied) {
                    unoccupiedTiles.add(tiles.indexOf(tile));
                }
            }
        }

        return tiles.get(unoccupiedTiles.get(ThreadLocalRandom.current().nextInt(0,unoccupiedTiles.size())));
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
