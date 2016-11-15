package network.HandlerClasses;

import bomberman.protocol.message.server.PlayerHit;
import network.Handler;
import network.Message;

/**
 * Created by vmadmin on 08.11.2016.
 */
public class PlayerHitHandler extends Handler{
    @Override
    public void handle(Message msg) {

    }

    @Override
    public boolean canHandle(Message msg) {
        return msg instanceof PlayerHit;
    }
}
