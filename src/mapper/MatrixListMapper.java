package mapper;

import bomberman.protocol.Labyrinth;
import model.Block;
import model.Element;
import model.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to convert labyrinth to various other formats
 * Created by Nathanael on 29.11.2016.
 */
public class MatrixListMapper {
    /**
     * converts labyrinth to matrix with enums
     * @param labyrinth
     * @return
     */
    public static Labyrinth toMatrix(model.Labyrinth labyrinth) {
        int maxX = 0;
        int maxY = 0;

        for (Tile t : labyrinth.getTiles()) {
            if (t.getX() > maxX) {
                maxX = t.getX();
            }

            if (t.getY() > maxY) {
                maxY = t.getY();
            }
        }

        Labyrinth.Tile[][] l = new Labyrinth.Tile[maxX][maxY];
        for (Tile t : labyrinth.getTiles()) {
            if (t.getElement() instanceof Block) {
                Block b = ((Block) t.getElement());
                if (b.isDestructable()) {
                    l[t.getX()][t.getY()] = Labyrinth.Tile.DESTROYABLE;
                } else {
                    l[t.getX()][t.getY()] = Labyrinth.Tile.UNDESTROYABLE;
                }
            } else if (t.getElement() == null) {
                l[t.getX()][t.getY()] = Labyrinth.Tile.EMPTY;
            }
        }

        return new Labyrinth(l);
    }

    /**
     * converts labyrinth matrix to list with Tiles
     * @param labyrinth
     * @return
     */
    public static model.Labyrinth toList(Labyrinth labyrinth) {
        List<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < labyrinth.getMatrix().length; i++) {
            for (int j = 0; j < labyrinth.getMatrix()[i].length; j++) {
                Tile tile = new Tile();
                tile.setX(i);
                tile.setY(j);

                if (labyrinth.getMatrix()[i][j] != Labyrinth.Tile.EMPTY) {
                    Block element = new Block();
                    element.setDestructable(labyrinth.getMatrix()[i][j] == Labyrinth.Tile.DESTROYABLE);
                }

                tiles.add(tile);
            }
        }
        return new model.Labyrinth(tiles);
    }
}
