package labyrinth;

import bomberman.protocol.Labyrinth;
import mapper.MatrixListMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helper class to read in labyrinth
 * Created by Nathanael on 06.12.2016.
 */
public class LabyrinthReader {
    private Scanner scanner;

    public LabyrinthReader(String filename) {
        try {
            scanner = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            scanner = null;
        }
    }

    /**
     * converts Matrix to ArrayList
     * @param matrix
     * @return
     */
    private Labyrinth matrixListToLab(ArrayList<ArrayList<Integer>> matrix) {
        Labyrinth lab = new Labyrinth(new Labyrinth.Tile[matrix.size()][matrix.get(0).size()]);
        for (int i = 0; i < matrix.size(); i++) {
            for (int i1 = 0; i1 < matrix.get(i).size(); i1++) {
                lab.getMatrix()[i][i1] = matrix.get(i).get(i1) == 0 ? Labyrinth.Tile.EMPTY : matrix.get(i).get(i1) == 1 ? Labyrinth.Tile.DESTROYABLE : Labyrinth.Tile.UNDESTROYABLE;
            }
        }
        return lab;
    }

    /**
     * reads labyrinth from file
     * @return
     */
    private Labyrinth readIn() {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                matrix.add(new ArrayList<>());
                String line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    matrix.get(matrix.size() - 1).add(Character.getNumericValue(line.charAt(i)));
                }
            }
            scanner.close();
        }
        return matrixListToLab(matrix);
    }

    public model.Labyrinth getLabyrinth() {
        return MatrixListMapper.toList(readIn());
    }
}
