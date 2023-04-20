package no.uib.inf101.sem2.fantomas.model.rooms;

import no.uib.inf101.sem2.grid.GridDimension;

import java.util.ArrayList;
import java.util.Iterator;

import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridCell;

public class Painting implements Iterable<GridCell<Character>> {
    String size;
    boolean[][] paintingGrid;
    CellPosition pos;

    public Painting(String size, boolean[][] paintingGrid, CellPosition pos) {
        super();
        this.size = size;
        this.paintingGrid = paintingGrid;
        this.pos = pos;
    }

    public static Painting newPainting(String size) {
        boolean[][] paintingGrid = switch (size) {

            case "small" -> new boolean[][] {
                { true },
                { true },
                { true },
                { true },
                { true },
                { true },
                { true }
            };
            case "medium" -> new boolean[][] {
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
            case "large" -> new boolean[][] {
                { true },
                { true },
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
            default -> throw new IllegalArgumentException("No available painting for " + size);
        };
        return new Painting(size, paintingGrid, new CellPosition(0, 0));
    }

    public Painting rotatedPainting() {
        boolean[][] rotatedPaintingGrid = switch (size) {
            case "small" -> new boolean[][] {
                { true, true, true, true, true, true, true }
            };
            case "medium" -> new boolean[][] {
                { true, true, true, true, true, true, true, true, true }
            };
            case "large" -> new boolean[][] {
                { true, true, true, true, true, true, true, true, true, true, true }
            };
            default -> throw new IllegalArgumentException("No available painting for " + size);
        };
        return new Painting(size, rotatedPaintingGrid, pos);
    }

    // Shifts the player in the direction indicated in the parameters
    public Painting shiftedBy(int deltaRow, int deltaCol) {
        CellPosition shiftedPos = new CellPosition(pos.row() + deltaRow, pos.col() + deltaCol);
        Painting shiftedPainting = new Painting(size, paintingGrid, shiftedPos);
        return shiftedPainting;
    }

    // Shifts the door to the wall
    public Painting shiftedToWall(GridDimension grid, String wall) {

        int middleCol = grid.cols() / 2 - 4;
        int middleRow = grid.rows() / 2 - 4;
        
        if (wall == "west") { 
            Painting shiftedPainting = shiftedBy(middleRow, 1);
            return shiftedPainting;
        }
        if (wall == "east") {
            Painting shiftedPainting = shiftedBy(middleRow, grid.cols()-2);
            return shiftedPainting;
        }
        if (wall == "north") {
            Painting shiftedPainting = shiftedBy(1, middleCol);
            return shiftedPainting;
        }
        if (wall == "south") {
            Painting shiftedPainting = shiftedBy(grid.cols()-2, middleCol);
            return shiftedPainting;
        }
        else {
            throw new IllegalArgumentException("No such wall as " + wall);
        }
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> paintingGrids = new ArrayList<GridCell<Character>>();
        for (int row = 0; row < paintingGrid.length; row++) {
            for (int col = 0; col < paintingGrid[0].length; col++) {
                if (paintingGrid[row][col] == true) {
                    CellPosition cellPos = new CellPosition(pos.row() + row, pos.col() + col);
                    GridCell<Character> cell = new GridCell<Character>(cellPos, 'A');
                    paintingGrids.add(cell);
                }
            }
        }
        return paintingGrids.iterator();
    }
    
}
