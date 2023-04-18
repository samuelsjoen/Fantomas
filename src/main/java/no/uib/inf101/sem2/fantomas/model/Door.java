package no.uib.inf101.sem2.fantomas.model;

import java.util.ArrayList;
import java.util.Iterator;

import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridCell;

public class Door implements Iterable<GridCell<Character>> {

    public String direction;
    public boolean[][] orientation;
    public CellPosition pos;

    public Door(String direction, boolean[][] orientation, CellPosition pos) {
        super();
        this.direction = direction;
        this.orientation = orientation;
        this.pos = pos;
    }

    public static boolean[][] newOrientation(String direction) {
        boolean[][] orientation = switch (direction) {

            case "Vertical" -> new boolean[][] {
                { true },
                { true },
                { true },
                { true },
                { true }
            };
            case "Horizontal" -> new boolean[][] {
                { true, true, true, true, true }
            };
            default -> throw new IllegalArgumentException("No available door for " + direction);
        };
        return orientation;
    }

    // Creates new door based on the input symbol
    public static Door newDoor(String direction) {
        return new Door(direction, newOrientation(direction), new CellPosition(0, 0));
    }

    // Shifts the player in the direction indicated in the parameters
    public Door shiftedBy(int deltaRow, int deltaCol) {
        CellPosition shiftedPos = new CellPosition(pos.row() + deltaRow, pos.col() + deltaCol);
        Door shiftedDoor = new Door(direction, orientation, shiftedPos);
        return shiftedDoor;
    }

    // Shifts the door to the wall
    public Door shiftedToWall(GridDimension grid, String wall) {
        if (direction == "Vertical") {
            if (wall == "west") { 
                int newCol = -4;
            }
            if (wall == "east") {
                int newCol = pos.col();
            }
        }
        int middleCol = grid.cols() / 2 - adjustmentForDoorSize;
        int middleRow = grid.rows() / 2 - adjustmentForDoorSize;
        Door shiftedDoor = shiftedBy(middleRow, middleCol);
        return shiftedDoor;
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> orientations = new ArrayList<GridCell<Character>>();
        for (int row = 0; row < orientation.length; row++) {
            for (int col = 0; col < orientation[0].length; col++) {
                if (orientation[row][col] == true) {
                    CellPosition cellPos = new CellPosition(pos.row() + row, pos.col() + col);
                    GridCell<Character> cell = new GridCell<Character>(cellPos, 'W');
                    orientations.add(cell);
                }
            }
        }
        return orientations.iterator();
    }
    
}
