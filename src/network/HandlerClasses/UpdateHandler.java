package network.HandlerClasses;

import bomberman.protocol.message.server.Update;
import network.Handler;
import network.Message;

/**
 * Created by Nathanael on 08.11.2016.
 */
public class UpdateHandler extends Handler {
    @Override
    public void handle(Message msg) {

    }

    @Override
    public boolean canHandle(Message msg) {
        return msg instanceof Update;
    }
}
