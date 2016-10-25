package network;

/**
 * Created by Nathanael on 25.10.2016.
 */
public class Dispatcher {
    private MessageQueue msgQ;

    public Dispatcher(){
        msgQ = MessageQueue.getMessageQueue();
    }

    public void dispatch() {
        msgQ.dequeue();
    }
}
