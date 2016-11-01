package application;

import network.Server.ServerStub;
import network.ServerApplicationHandler;

/**
 * Created by Nathanael on 18.10.2016.
 */
public class App {
    public static void main(String[] args) {
        ServerStub server = new ServerStub(new ServerApplicationHandler());
    }
}
