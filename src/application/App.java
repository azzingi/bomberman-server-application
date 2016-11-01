package application;

import network.Dispatcher;
import network.Handler;
import network.HandlerClasses.PlayerJoinedHandler;
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

        handlerList.add(new PlayerJoinedHandler());

        server = new ServerStub(new ServerApplicationHandler());
        dispatcher = new Dispatcher();
    }
}
