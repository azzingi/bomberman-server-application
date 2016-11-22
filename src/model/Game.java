package model;

import application.App;
import bomberman.protocol.Labyrinth;
import bomberman.protocol.message.server.Error;
import bomberman.protocol.message.server.PlayerJoined;
import bomberman.protocol.message.server.StartGame;
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
            //App.getServer().broadcast(new StartGame(labyrinth)); TODO
        } else if (players.size() > MAX_NB_OF_PLAYERS){
            App.getServer().send(new Error("Spiel läuft bereits"), Dictionary.getInstance().get(playerName));
        }
    }

    public Tile getStartTile() {
        return labyrinth.getRandomTile();
    }
}
