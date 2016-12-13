package network;

import bomberman.protocol.message.client.DropBomb;

import java.util.List;

/**
 * watchs for messages in messagequeue and lets the handlers handle this
 * Created by Nathanael on 25.10.2016.
 */
public class Dispatcher {
    private MessageQueue msgQ;
    private List<Handler> handler;
    private boolean running;

    public Dispatcher(List<Handler> handler) {
        this.handler = handler;
        this.running = true;
        msgQ = MessageQueue.getMessageQueue();
    }

    // Prototype Pattern
    /**
     * dispatches and watchs for the right handler
     */
    public void dispatch() {
        while (running) {
            Message msg = msgQ.dequeue();
            for (Handler handler1 : handler) {
                if (handler1.canHandle(msg)) {
                    handler1.handle(msg);
                }
            }
        }
    }
}
