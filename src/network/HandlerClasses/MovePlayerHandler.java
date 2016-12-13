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

    /**
     * Moves the given player in the given direction.
     * @param msg
     */
    @Override
    public void handle(Message msg) {
        MovePlayer mMsg = (MovePlayer) msg;
        App.getGame().movePlayer(mMsg.getPlayerName(),mMsg.getDirection());
    }

    @Override
    public boolean canHandle(Message msg) {
        return msg instanceof MovePlayer;
    }
}
