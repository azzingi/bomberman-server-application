package network.HandlerClasses;

import bomberman.protocol.message.server.PlayerJoined;
import network.Handler;
import network.Message;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class PlayerJoinedHandler extends Handler {
    @Override
    public void handle(Message msg) {

    }

    @Override
    public boolean canHandle(Message msg) {
        return msg instanceof PlayerJoined;
    }
}
