package network;

import java.util.ArrayList;

/**
 * MessageQueue to add and remove messages from the queue
 * Created by Nathanael on 25.10.2016.
 * Ausgewählte Klasse
 * Clean Code zu bestimmen
 */
public class MessageQueue {
    private ArrayList<Message> messages = new ArrayList<>();
    private static MessageQueue msgQ = new MessageQueue();

    private MessageQueue() {
    }

    /**
     * creates and returns MessageQueue instance
     * @return MessageQueue instance
     */
    public static MessageQueue getMessageQueue() {
        return msgQ;
    }

    /**
     * adds message to the queue and notifys the dequeue method
     * @param msg Message to add
     */
    public synchronized void enqueue(Message msg) {
        messages.add(msg);
        notify();
    }

    /**
     * removes a message from the list
     * @return Message from the queue
     */
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
