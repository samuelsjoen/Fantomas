// Inspired by tetromino.java from Tetris

package no.uib.inf101.sem2.fantomas.model.rooms;

import java.util.ArrayList;
import java.util.Iterator;
import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.grid.GridDimension;

public class Painting implements Iterable<GridCell<Character>> {
    private int size;
    private boolean[][] paintingGrid;
    private CellPosition pos;
    private char number;
    private String path;
    private String info;

    private Painting(int size, boolean[][] paintingGrid, CellPosition pos, char number, String path, String info) {
        super();
        this.size = size;
        this.paintingGrid = paintingGrid;
        this.pos = pos;
        this.number = number;
        this.path = path;
        this.info = info;
    }

    /**
     * Creates a new painting with given size, which number painting it is in the
     * room, the path to the image
     * file used to display the painting as well as the info about painting (arist,
     * name and date)
     */
    public static Painting newPainting(int size, char number, String path, String info) {
        boolean[][] paintingGrid = switch (size) {

            case 7 -> new boolean[][] {
                    { true },
                    { true },
                    { true },
                    { true },
                    { true },
                    { true },
                    { true }
            };
            case 9 -> new boolean[][] {
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
            case 11 -> new boolean[][] {
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
        return new Painting(size, paintingGrid, new CellPosition(0, 0), number, path, info);
    }

    /** Rotates the painting 90 degrees */
    public Painting rotatedPainting() {
        boolean[][] rotatedPaintingGrid = switch (size) {
            case 7 -> new boolean[][] {
                    { true, true, true, true, true, true, true }
            };
            case 9 -> new boolean[][] {
                    { true, true, true, true, true, true, true, true, true }
            };
            case 11 -> new boolean[][] {
                    { true, true, true, true, true, true, true, true, true, true, true }
            };
            default -> throw new IllegalArgumentException("No available painting for " + size);
        };
        return new Painting(size, rotatedPaintingGrid, pos, number, path, info);
    }

    /** Shifts the painting in the direction given within the parameters */
    public Painting shiftedBy(int deltaRow, int deltaCol) {
        CellPosition shiftedPos = new CellPosition(pos.row() + deltaRow, pos.col() + deltaCol);
        Painting shiftedPainting = new Painting(size, paintingGrid, shiftedPos, number, path, info);
        return shiftedPainting;
    }

    /** Shifts the painting to the wall within given grid */
    public Painting shiftedToWall(GridDimension grid, String wall) {

        int middleCol = grid.cols() / 2 - 4;
        int middleRow = grid.rows() / 2 - 4;

        if (wall == "west") {
            Painting shiftedPainting = shiftedBy(middleRow, 1);
            return shiftedPainting;
        }
        if (wall == "east") {
            Painting shiftedPainting = shiftedBy(middleRow, grid.cols() - 2);
            return shiftedPainting;
        }
        if (wall == "north") {
            Painting shiftedPainting = shiftedBy(1, middleCol);
            return shiftedPainting;
        }
        if (wall == "south") {
            Painting shiftedPainting = shiftedBy(grid.rows() - 2, middleCol);
            return shiftedPainting;
        } else {
            throw new IllegalArgumentException("No such wall as " + wall);
        }
    }

    /** Returns a copy of the paintings number */
    public char getNumber() {
        char paintingNumber = this.number;
        return paintingNumber;
    }

    /** Returns a copy of the paintings image file path */
    public String getPath() {
        String paintingPath = this.path;
        return paintingPath;
    }

    /** Returns a copy of the paintings info */
    public String getPaintinginfo() {
        String paintingInfo = this.info;
        return paintingInfo;
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> paintingGrids = new ArrayList<GridCell<Character>>();
        for (int row = 0; row < paintingGrid.length; row++) {
            for (int col = 0; col < paintingGrid[0].length; col++) {
                if (paintingGrid[row][col] == true) {
                    CellPosition cellPos = new CellPosition(pos.row() + row, pos.col() + col);
                    GridCell<Character> cell = new GridCell<Character>(cellPos, number);
                    paintingGrids.add(cell);
                }
            }
        }
        return paintingGrids.iterator();
    }

}
