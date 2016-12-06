package model;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class Block extends Element {
    private boolean destructable;

    public boolean isDestructable() {
        return destructable;
    }

    public void setDestructable(boolean destructable) {
        this.destructable = destructable;
    }
}
