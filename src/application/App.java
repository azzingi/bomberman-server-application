package application;

import network.Dispatcher;
import network.Handler;
import network.HandlerClasses.*;
import network.Server.ServerStub;
import network.ServerApplicationHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathanael on 18.10.2016.
 */
public class App {
    private static ServerStub server;
    private static Dispatcher dispatcher;
    private static List<Handler> handlerList;

    public static void main(String[] args) {
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

        server = new ServerStub(new ServerApplicationHandler());
        dispatcher = new Dispatcher(handlerList);
        dispatcher.dispatch();
    }
}
