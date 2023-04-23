// Based on TetrisBoard.java from Tetris

package no.uib.inf101.sem2.fantomas.model;

import java.util.ArrayList;
import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.Grid;

public class FantomasBoard extends Grid<Character> {

    // The board which the character will appear on. '-' signifies an empty cell
    public FantomasBoard(int rows, int cols) {
        super(rows, cols, '-');
    }

    /** Changes an entire row to a given value */
    public void setRow(int row, char value) {
        for (int col = 0; col < cols(); col++) {
            set(new CellPosition(row, col), value);
        }
    }

    /** Returns the board in the form of a string */
    public String prettyString() {
        ArrayList<String> rowStrings = new ArrayList<>();
        for (int row = 0; row < rows(); row++) {
            String colString = "";
            for (int col = 0; col < cols(); col++) {
                Character c = get(new CellPosition(row, col));
                colString = colString + c;
            }
            rowStrings.add(colString);
        }
        return String.join("\n", rowStrings);
    }
}
