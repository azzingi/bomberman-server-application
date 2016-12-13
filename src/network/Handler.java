package network;

/**
 * Handler abstract for all handlers to extend
 * Created by Nathanael on 01.11.2016.
 */
public abstract class Handler {
    public abstract void handle(Message msg);

    public abstract boolean canHandle(Message msg);
}
