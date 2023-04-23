// Inspired by tetromino.java from Tetris

package no.uib.inf101.sem2.fantomas.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.grid.GridDimension;

public class Player implements Iterable<GridCell<Character>> {

    private char symbol;
    private boolean[][] orientation;
    private CellPosition pos;

    private Player(char symbol, boolean[][] orientation, CellPosition pos) {
        super();
        this.symbol = symbol;
        this.orientation = orientation;
        this.pos = pos;
    }

    /** Creates a new orientation to be used in creating aa player */
    private static boolean[][] newOrientation(char symbol) {
        boolean[][] orientation = switch (symbol) {

            case 'U' -> new boolean[][] {
                    { false, false, false, false, false },
                    { false, true, true, true, false },
                    { true, true, true, true, true },
                    { false, true, true, true, false },
                    { false, false, false, false, false }
            };
            case 'D' -> new boolean[][] {
                    { false, false, false, false, false },
                    { false, true, true, true, false },
                    { true, true, true, true, true },
                    { false, true, true, true, false },
                    { false, false, false, false, false }
            };
            case 'L' -> new boolean[][] {
                    { false, false, true, false, false },
                    { false, true, true, true, false },
                    { false, true, true, true, false },
                    { false, true, true, true, false },
                    { false, false, true, false, false }
            };
            case 'R' -> new boolean[][] {
                    { false, false, true, false, false },
                    { false, true, true, true, false },
                    { false, true, true, true, false },
                    { false, true, true, true, false },
                    { false, false, true, false, false }
            };
            default -> throw new IllegalArgumentException("No available player for " + symbol);
        };
        return orientation;
    }

    /** Creates new player based on the input symbol */
    public static Player newPlayer(char symbol) {
        return new Player(symbol, newOrientation(symbol), new CellPosition(0, 0));
    }

    /** Shifts the player in the direction indicated in the parameters */
    public Player shiftedBy(int deltaRow, int deltaCol) {
        CellPosition shiftedPos = new CellPosition(pos.row() + deltaRow, pos.col() + deltaCol);
        Player shiftedPlayer = new Player(symbol, orientation, shiftedPos);
        return shiftedPlayer;
    }

    /** Shifts the player to the centre of the grid */
    public Player shiftedToCenter(GridDimension grid) {
        int adjustmentForCharacterSize = 2;
        int middleCol = grid.cols() / 2 - adjustmentForCharacterSize;
        int middleRow = grid.rows() / 2 - adjustmentForCharacterSize;
        Player shiftedPlayer = shiftedBy(middleRow, middleCol);
        return shiftedPlayer;
    }

    /** Rotates the player */
    public Player rotatedPlayer(char newOrientation) {
        Player rotatedPlayer = new Player(newOrientation, newOrientation(newOrientation), pos);
        return rotatedPlayer;
    }

    /** Returns a copy of the players symbol */
    public char getSymbol() {
        char playerSymbol = this.symbol;
        return playerSymbol;
    }

    /** Returns a copy of the players position */
    public CellPosition getPos() {
        CellPosition playerPos = this.pos;
        return playerPos;
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> orientations = new ArrayList<GridCell<Character>>();
        for (int row = 0; row < orientation.length; row++) {
            for (int col = 0; col < orientation[0].length; col++) {
                if (orientation[row][col] == true) {
                    CellPosition cellPos = new CellPosition(pos.row() + row, pos.col() + col);
                    GridCell<Character> cell = new GridCell<Character>(cellPos, symbol);
                    orientations.add(cell);
                }
            }
        }
        return orientations.iterator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.symbol, Arrays.deepHashCode(this.orientation), this.pos);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (other == null)
            return false;
        if (getClass() != other.getClass())
            return false;
        Player otherPlayer = (Player) other;
        return Objects.equals(otherPlayer.symbol, this.symbol)
                && Arrays.deepEquals(otherPlayer.orientation, this.orientation)
                && Objects.equals(otherPlayer.pos, this.pos);
    }
}
