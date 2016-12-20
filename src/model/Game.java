package model;

import application.App;
import bomberman.protocol.Labyrinth;
import bomberman.protocol.message.enums.Direction;
import bomberman.protocol.message.server.*;
import bomberman.protocol.message.server.Error;
import highscore.HighScoreHandler;
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
    private HighScoreHandler highScoreHandler;

    public Game(model.Labyrinth labyrinth) {
        players = new ArrayList<>();
        this.labyrinth = labyrinth;
        highScoreHandler = new HighScoreHandler();
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

    public void calcBombExploded(int x, int y) {
        calcBombExploded(1, x, y);
    }

    public void calcBombExploded(int radius, int x, int y) {
        calcBombExploded(radius, false, x, y);
    }

    public void calcBombExploded(int radius, boolean noRadius, int x, int y) {
        ArrayList<Tile> explodingTiles = new ArrayList<>();
        for (int i = 0; i < radius; i++) {
            if (i > 0) {
                Tile tile = new Tile();
                tile.setX(x);
                tile.setY(y);
                explodingTiles.add(tile);
            } else {
                for (int i1 = -x; i1 <= x; i1++) {
                    for (int i2 = -y; i2 <= y; i2++) {
                        if (i1 != 0 && i2 != 0) {
                            Tile tile = new Tile();
                            tile.setX(i1);
                            tile.setY(i2);
                            explodingTiles.add(tile);
                        }
                    }
                }
            }
        }

        for (Tile tile : labyrinth.getTiles()) {
            for (Tile explodingTile : explodingTiles) {
                if (tile.getX() == explodingTile.getX() && tile.getY() == explodingTile.getY()) {
                    if (tile.getElement() instanceof Block && ((Block) tile.getElement()).isDestructable()) {
                        tile.setElement(new Element());
                    }
                    int countAlive = 4;
                    for (Player player : players) {
                        if (!player.isAlive()) {
                            countAlive--;
                        }
                        if (player.getTile().getX() == tile.getX() && player.getTile().getY() == tile.getY()) {
                            if (player.isAlive()) {
                                highScoreHandler.updateWinner(player.getName(),(4 - countAlive) - 1);
                                player.setAlive(false);
                                countAlive--;
                            }
                            App.getServer().broadcast(new PlayerHit(player.getName()));
                        }
                    }
                }
            }
        }

        App.getServer().broadcast(new Update(MatrixListMapper.toMatrix(labyrinth)));
        boolean gameOver = true;
        for (Player player : players) {
            if (player.isAlive()) {
                gameOver = false;
            }
        }

        App.getServer().broadcast(new GameOver(highScoreHandler.getHighscoreList()));
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
