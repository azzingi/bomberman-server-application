package network.HandlerClasses;

import application.App;
import bomberman.protocol.message.PlayerAssociatedMessage;
import bomberman.protocol.message.client.JoinGame;
import bomberman.protocol.message.server.Error;
import bomberman.protocol.message.server.PlayerJoined;
import model.Player;
import network.Handler;
import network.Message;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class JoinGameHandler extends Handler {

    /**
     * Adds the player to the current game.
     * @param msg
     */
    @Override
    public void handle(Message msg) {
        PlayerAssociatedMessage m = (PlayerAssociatedMessage) msg;
        App.getGame().addPlayer(m.getPlayerName());
    }

    @Override
    public boolean canHandle(Message msg) {
        return msg instanceof JoinGame;
    }
}
