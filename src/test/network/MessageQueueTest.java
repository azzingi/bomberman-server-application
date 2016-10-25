package test.network;

import network.Message;
import network.MessageQueue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Nathanael on 25.10.2016.
 */
public class MessageQueueTest {
    private static MessageQueue msgQ;

    @BeforeClass
    public static void setup() {
        msgQ = MessageQueue.getMessageQueue();
    }

    @Test
    public void messageQueueTest() {
        MessageThreadTest mtt1 = new MessageThreadTest(msgQ);
        MessageThreadTest mtt2 = new MessageThreadTest(msgQ);
        MessageThreadTest mtt3 = new MessageThreadTest(msgQ);

        mtt1.run();
        mtt2.run();
        mtt3.run();

        assert (msgQ.dequeue().equals(mtt1));
        assert (msgQ.dequeue().equals(mtt2));
        assert (msgQ.dequeue().equals(mtt3));
        assert (msgQ.dequeue() == null);
    }

    @AfterClass
    public static void destruct() {
        msgQ = null;
    }
}
