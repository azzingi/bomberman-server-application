package network;

/**
 * Created by Nathanael on 25.10.2016.
 */
public class Dispatcher {
    private MessageQueue msgQ;
    private Handler handler;

    public Dispatcher(Handler handler) {
        this.handler = handler;
    }

    public Dispatcher(){
        msgQ = MessageQueue.getMessageQueue();
    }

    public void dispatch() {
        handler.handle(msgQ.dequeue());
    }
}
