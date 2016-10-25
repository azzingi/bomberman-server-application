package network;

import network.server.ServerApplicationInterface;

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
        msgQ.enqueue(message);
    }
}