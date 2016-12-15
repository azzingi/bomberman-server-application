package network.test;

import application.App;
import network.ServerApplicationHandler;
import network.server.Server;
import network.serverImpls.ServerStub;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nathanael on 13.12.2016.
 */
public class GameTest {
    private Server server;
    private App applicationServer;

    @Before
    public void setUp() throws Exception {
        applicationServer = new App();
    }

    @Test
    public void send() throws Exception {
        new Thread() {
            @Override
            public void run() {
                applicationServer.run();
            }
        }.start();
        server = new ServerStub(new ServerApplicationHandler());
        applicationServer.stop();
    }

    @After
    public void tearDown() throws Exception {
        server = null;
        applicationServer = null;
    }

}