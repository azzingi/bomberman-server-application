package application;

import network.Dispatcher;
import network.Handler;
import network.Server.ServerStub;
import network.ServerApplicationHandler;

/**
 * Created by Nathanael on 18.10.2016.
 */
public class App {
    private static ServerStub server;
    private static Dispatcher dispatcher;

    public static void main(String[] args) {
        server = new ServerStub(new ServerApplicationHandler());
        dispatcher = new Dispatcher(new Handler());
    }
}
