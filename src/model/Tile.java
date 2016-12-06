package model;

/**
 * Created by Nathanael on 01.11.2016.
 */
public class Tile {
    private int x;
    private int y;
    private Element element;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Element getElement() {
        return element;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
