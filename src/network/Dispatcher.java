package network;

import bomberman.protocol.message.client.DropBomb;

import java.util.List;

/**
 * Created by Nathanael on 25.10.2016.
 */
public class Dispatcher {
    private MessageQueue msgQ;
    private List<Handler> handler;

    public Dispatcher(List<Handler> handler) {
        this.handler = handler;
    }

    public Dispatcher(){
        msgQ = MessageQueue.getMessageQueue();
    }

    // Prototype Pattern
    public void dispatch() {
        Message msg = msgQ.dequeue(); //maybe make dispatch get Message object
        for (Handler handler1 : handler) {
            if (handler1.canHandle(msg)) {
                handler1.handle(msg);
            }
        }
    }
}
