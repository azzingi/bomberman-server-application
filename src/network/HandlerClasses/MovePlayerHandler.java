package network.HandlerClasses;

import application.App;
import bomberman.protocol.message.client.MovePlayer;
import model.Game;
import network.Handler;
import network.Message;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class MovePlayerHandler extends Handler {
    @Override
    public void handle(Message msg) {

    }

    @Override
    public boolean canHandle(Message msg) {
        return msg instanceof MovePlayer;
    }
}
