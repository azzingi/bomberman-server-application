package network;

import application.App;
import bomberman.protocol.message.PlayerAssociatedMessage;
import network.server.ServerApplicationInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathanael on 25.10.2016.
 */
public class ServerApplicationHandler implements ServerApplicationInterface {
    private MessageQueue msgQ;

    public ServerApplicationHandler() {
        msgQ = MessageQueue.getMessageQueue();
    }

    @Override
    public void handleMessage(Message message, String connectionId) {
        if (message instanceof PlayerAssociatedMessage) {
            String playerName = ((PlayerAssociatedMessage) message).getPlayerName();
            Dictionary.getInstance().add(playerName, connectionId);
        }
        msgQ.enqueue(message);
    }
}
