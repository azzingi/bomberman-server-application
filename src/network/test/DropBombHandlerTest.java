package network.test;

import network.Server.ServerStub;
import network.ServerApplicationHandler;
import network.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nathanael on 15.11.2016.
 */
public class DropBombHandlerTest {
    private ServerStub stub;
    @Before
    public void setUp() throws Exception {
        stub = new ServerStub(new ServerApplicationHandler());
    }

    @After
    public void tearDown() throws Exception {
        stub = null;
    }

    @Test
    public void handle() throws Exception {
        stub.
    }

    @Test
    public void canHandle() throws Exception {

    }

}