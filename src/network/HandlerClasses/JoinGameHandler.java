package network.HandlerClasses;

import bomberman.protocol.message.client.JoinGame;
import network.Handler;
import network.Message;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class JoinGameHandler extends Handler {
    @Override
    public void handle(Message msg) {
        System.out.println(msg);
    }

    @Override
    public boolean canHandle(Message msg) {
        return msg instanceof JoinGame;
    }
}
