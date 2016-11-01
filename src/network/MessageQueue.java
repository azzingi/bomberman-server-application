package network;

import java.util.ArrayList;

/**
 * Created by Nathanael on 25.10.2016.
 */
public class MessageQueue {
    private ArrayList<Message> messages = new ArrayList<>();
    private static MessageQueue msgQ;

    private MessageQueue() {
    }

    public static MessageQueue getMessageQueue() {
        if (msgQ == null) {
            msgQ = new MessageQueue();
        }
        return msgQ;
    }

    public synchronized void enqueue(Message msg) {
        messages.add(msg);
        notify();
    }

    public synchronized Message dequeue() {
        if (messages.size() <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message msg = messages.get(0);
        messages.remove(0);
        return msg;
    }
}
