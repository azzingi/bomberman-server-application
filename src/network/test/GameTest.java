package network.test;

import application.App;
import network.ServerApplicationHandler;
import network.server.Server;
import network.server.ServerStub;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nathanael on 13.12.2016.
 */
public class GameTest {
    private Server server;
    private App applicationServer;

    @Before
    public void setUp() throws Exception {
        server = new ServerStub(new ServerApplicationHandler());
        applicationServer = new App();
    }

    @Test
    public void send() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        server = null;
        applicationServer = null;
    }

}