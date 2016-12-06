package model;

import application.App;
import bomberman.protocol.Labyrinth;
import bomberman.protocol.message.enums.Direction;
import bomberman.protocol.message.server.Error;
import bomberman.protocol.message.server.PlayerJoined;
import bomberman.protocol.message.server.PlayerMoved;
import bomberman.protocol.message.server.StartGame;
import mapper.MatrixListMapper;
import network.Dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class Game {
    public static final int MAX_NB_OF_PLAYERS = 4;
    private model.Labyrinth labyrinth;
    private List<Player> players;

    public Game() {
        players = new ArrayList<>();
        labyrinth = new model.Labyrinth(new ArrayList<Tile>());
    }

    public void addPlayer(String playerName) {
        Player player = null;
        if (players.size() < MAX_NB_OF_PLAYERS) {
            player = new Player(playerName, getStartTile());
            players.add(player);
            App.getServer().broadcast(new PlayerJoined(player.getName(), player.getTile().getX(), player.getTile().getY()));
        }
        if (players.size() == MAX_NB_OF_PLAYERS) {
            App.getServer().broadcast(new StartGame(MatrixListMapper.toMatrix(labyrinth)));
        } else if (players.size() > MAX_NB_OF_PLAYERS){
            App.getServer().send(new Error("Spiel läuft bereits"), Dictionary.getInstance().get(playerName));
        }
    }

<<<<<<< HEAD
    public void dropBomb(String playerName) {
        Bomb bomb = null;
        Block block = null;
        Player player = null;

        player = new Player(playerName, getStartTile());
        if (((Block) player.getTile().getElement()).isDestructable()){

        }
    }

    public void

=======
    public void movePlayer(String playerName, Direction direction) {
        Player p = null;
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                p = player;
            }
        }

        if (p != null) {
            int newX = p.getTile().getX();
            int newY = p.getTile().getY();

            switch (direction) {
                case DOWN:
                    newY -= 1;
                    break;
                case LEFT:
                    newX -= 1;
                    break;
                case RIGHT:
                    newX += 1;
                    break;
                case UP:
                    newY += 1;
                    break;
                default:
                    break;
            }

            if (newX != p.getTile().getX() || newY != p.getTile().getY()) {
                for (Tile tile : labyrinth.getTiles()) {
                    if (tile.getX() == newX && tile.getY() == newY) {
                        p.move(tile);
                        App.getServer().broadcast(new PlayerMoved(p.getName(),direction));
                    }
                }
            }
        }
    }

>>>>>>> a0ff669ee791a11ca1cb465a7de2d2666f01c027
    public Tile getStartTile() {
        return labyrinth.getRandomTile();
    }
}
