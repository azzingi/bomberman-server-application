package network.HandlerClasses;

import bomberman.protocol.message.server.GameOver;
import network.Handler;
import network.Message;

/**
 * Created by vmadmin on 08.11.2016.
 */
public class GameOverHandler extends Handler{
    @Override
    public void handle(Message msg) {
        System.out.println(msg);
    }

    @Override
    public boolean canHandle(Message msg) {
        return msg instanceof GameOver;
    }
}
