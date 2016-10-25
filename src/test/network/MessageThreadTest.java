package test.network;

import network.Message;
import network.MessageQueue;

/**
 * Created by Nathanael on 25.10.2016.
 */
public class MessageThreadTest extends Thread implements Message {
    private MessageQueue msgQ;

    public MessageThreadTest(MessageQueue messageQueue) {
        msgQ = messageQueue;
    }

    @Override
    public void run() {
        msgQ.enqueue(this);
    }
}
