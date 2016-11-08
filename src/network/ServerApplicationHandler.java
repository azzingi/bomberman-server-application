package network;

import bomberman.protocol.message.PlayerAssociatedMessage;
import network.server.ServerApplicationInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathanael on 25.10.2016.
 */
public class ServerApplicationHandler implements ServerApplicationInterface {
    private MessageQueue msgQ;
    private Map<String, String> dictionary;

    public ServerApplicationHandler() {
        msgQ = MessageQueue.getMessageQueue();
        dictionary = new HashMap<>();
    }

    @Override
    public void handleMessage(Message message, String connectionId) {
        if (message instanceof PlayerAssociatedMessage) {
            String playerName = ((PlayerAssociatedMessage) message).getPlayerName();
            dictionary.putIfAbsent(playerName, connectionId);
        }
        msgQ.enqueue(message);
    }
}
