package network;

import application.App;
import bomberman.protocol.message.PlayerAssociatedMessage;
import network.server.ServerApplicationInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * implements ServerApplicationInterface and handle the handleMessage method
 * Created by Nathanael on 25.10.2016.
 */
public class ServerApplicationHandler implements ServerApplicationInterface {
    private MessageQueue msgQ;

    public ServerApplicationHandler() {
        msgQ = MessageQueue.getMessageQueue();
    }

    /**
     * handles incoming messages end adds them to the messageQueue
     * @param message message to handle
     * @param connectionId id for the connection
     */
    @Override
    public void handleMessage(Message message, String connectionId) {
        if (message instanceof PlayerAssociatedMessage) {
            String playerName = ((PlayerAssociatedMessage) message).getPlayerName();
            Dictionary.getInstance().add(playerName, connectionId);
        }
        msgQ.enqueue(message);
    }
}
