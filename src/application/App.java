package application;

import labyrinth.LabyrinthReader;
import model.Game;
import model.Labyrinth;
import network.Dispatcher;
import network.Handler;
import network.HandlerClasses.*;
import network.serverImpls.ServerStub;
import network.ServerApplicationHandler;
import network.server.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathanael on 18.10.2016.
 */
public class App {
    private static Server server;
    private static Dispatcher dispatcher;
    private static List<Handler> handlerList;
    private static Game game;

    /**
     * Starts the serverImpls and loads the labyrinth in.
     * @param args
     */
    public static void main(String[] args) {
        LabyrinthReader labyrinthReader = new LabyrinthReader("Labyrinth_Beispiel_lukasmiikaschmid_jonasjallard.txt");
        handlerList = new ArrayList<>();

        handlerList.add(new DropBombHandler());
        handlerList.add(new JoinGameHandler());
        handlerList.add(new MovePlayerHandler());
        handlerList.add(new PlayerJoinedHandler());
        handlerList.add(new PlayerMovedHandler());
        handlerList.add(new StartGameHandler());
        handlerList.add(new UpdateHandler());
        handlerList.add(new BombDroppedHandler());
        handlerList.add(new BombExplodedHandler());
        handlerList.add(new ErrorHandler());
        handlerList.add(new GameOverHandler());
        handlerList.add(new PlayerHitHandler());

        Labyrinth labyrinth = labyrinthReader.getLabyrinth();

        game = new Game(labyrinth);
        server = new ServerStub(new ServerApplicationHandler());
        dispatcher = new Dispatcher(handlerList);
        dispatcher.dispatch();
    }

    public static Game getGame() {
        return game;
    }

    public static Server getServer() { return server; }
}
