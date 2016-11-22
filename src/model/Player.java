package model;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class Player {
    private boolean alive;
    private String name;
    private Tile tile;

    public Player(String name, Tile start) {
        this.name = name;
        tile = start;
    }

    public void move(Tile tile) {

    }

    public void placeBomb() {

    }

    public String getName() {
        return name;
    }

    public Tile getTile() {
        return tile;
    }
}
