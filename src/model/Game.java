package model;

import application.App;
import bomberman.protocol.Labyrinth;
import bomberman.protocol.message.enums.Direction;
import bomberman.protocol.message.server.*;
import bomberman.protocol.message.server.Error;
import mapper.MatrixListMapper;
import network.Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class Game {
    public static final int MAX_NB_OF_PLAYERS = 4;
    private model.Labyrinth labyrinth;
    private List<Player> players;

    public Game(model.Labyrinth labyrinth) {
        players = new ArrayList<>();
        this.labyrinth = labyrinth;
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

    public void dropBomb(String playerName) {
        Bomb bomb = null;
        Player player = null;
        player = new Player(playerName, getStartTile());
        if (((Block) player.getTile().getElement()).isDestructable()){
            dropBomb(player.getName());
            App.getServer().broadcast(new BombDropped(bomb.getId(),player.getTile().getX(),player.getTile().getY()));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            App.getServer().broadcast(new BombExploded(bomb.getId()));

        }
    }



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


    private Tile getStartTile() {
        return labyrinth.getRandomTile(players);
    }
}
