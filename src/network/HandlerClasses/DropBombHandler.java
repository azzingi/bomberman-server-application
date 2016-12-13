package network.HandlerClasses;

import application.App;
import bomberman.protocol.Labyrinth;
import bomberman.protocol.message.PlayerAssociatedMessage;
import bomberman.protocol.message.client.DropBomb;
import network.Handler;
import network.Message;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class DropBombHandler extends Handler {
    @Override
    public void handle(Message msg) {
        PlayerAssociatedMessage m = (PlayerAssociatedMessage) msg;
        App.getGame().dropBomb(m.getPlayerName());
    }

    @Override
    public boolean canHandle(Message msg) {
        return msg instanceof DropBomb;
    }
}
