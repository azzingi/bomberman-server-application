package network.HandlerClasses;

import bomberman.protocol.message.server.PlayerMoved;
import network.Handler;
import network.Message;

/**
 * Created by vmadmin on 01.11.2016.
 */
public class PlayerMovedHandler extends Handler {
    @Override
    public void handle(Message msg) {

    }

    @Override
    public boolean canHandle(Message msg) {
        return msg instanceof PlayerMoved;
    }
}
