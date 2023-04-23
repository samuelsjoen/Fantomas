// Inspired by tetromino.java from Tetris

package no.uib.inf101.sem2.fantomas.model.rooms;

import java.util.ArrayList;
import java.util.Iterator;
import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.grid.GridDimension;

public class Door implements Iterable<GridCell<Character>> {

    private String direction;
    private boolean[][] orientation;
    private CellPosition pos;

    private Door(String direction, boolean[][] orientation, CellPosition pos) {
        super();
        this.direction = direction;
        this.orientation = orientation;
        this.pos = pos;
    }

    /** Creates a new orientation to be used in creation of door */
    private static boolean[][] newOrientation(String direction) {
        boolean[][] orientation = switch (direction) {

            case "vertical" -> new boolean[][] {
                    { true },
                    { true },
                    { true },
                    { true },
                    { true },
                    { true },
                    { true },
                    { true },
                    { true }
            };
            case "horizontal" -> new boolean[][] {
                    { true, true, true, true, true, true, true, true, true }
            };
            default -> throw new IllegalArgumentException("No available door for " + direction);
        };
        return orientation;
    }

    /** Creates a new door in the given direction */
    public static Door newDoor(String direction) {
        return new Door(direction, newOrientation(direction), new CellPosition(0, 0));
    }

    /** Shifts the door by given parameters */
    public Door shiftedBy(int deltaRow, int deltaCol) {
        CellPosition shiftedPos = new CellPosition(pos.row() + deltaRow, pos.col() + deltaCol);
        Door shiftedDoor = new Door(direction, orientation, shiftedPos);
        return shiftedDoor;
    }

    /** Shifts the door to the wall within given grid */
    public Door shiftedToWall(GridDimension grid, String wall) {

        int middleCol = grid.cols() / 2 - 4;
        int middleRow = grid.rows() / 2 - 4;

        if (wall == "west") {
            Door shiftedDoor = shiftedBy(middleRow, 0);
            return shiftedDoor;
        }
        if (wall == "east") {
            Door shiftedDoor = shiftedBy(middleRow, grid.cols() - 1);
            return shiftedDoor;
        }
        if (wall == "north") {
            Door shiftedDoor = shiftedBy(0, middleCol);
            return shiftedDoor;
        }
        if (wall == "south") {
            Door shiftedDoor = shiftedBy(grid.cols() - 1, middleCol);
            return shiftedDoor;
        }

        Door shiftedDoor = shiftedBy(0, 0);
        return shiftedDoor;
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> orientations = new ArrayList<GridCell<Character>>();
        for (int row = 0; row < orientation.length; row++) {
            for (int col = 0; col < orientation[0].length; col++) {
                if (orientation[row][col] == true) {
                    CellPosition cellPos = new CellPosition(pos.row() + row, pos.col() + col);
                    GridCell<Character> cell = new GridCell<Character>(cellPos, 'P');
                    orientations.add(cell);
                }
            }
        }
        return orientations.iterator();
    }

}
