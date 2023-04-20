package no.uib.inf101.sem2.fantomas.model.rooms;

import java.util.ArrayList;
import java.util.Iterator;

import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridCell;

public class Walls implements Iterable<GridCell<Character>> {

    boolean[][] wallGrid;
    CellPosition pos;

    public Walls(boolean[][] wallGrid, CellPosition pos) {
        super();
        this.wallGrid = wallGrid;
        this.pos = pos;
    }

    public static Walls newWalls(GridDimension grid) {

        boolean[][] wallGrid = new boolean[grid.rows()][grid.cols()];

        for (int row = 0; row < grid.rows(); row++) { 
            for (int col = 0; col < grid.cols(); col++) {
                if (row == 0 || row == grid.rows()-1) {   
                    wallGrid[row][col] = true;
                }
                else if (col == 0 || col == grid.cols()-1) {
                    wallGrid[row][col] = true;
                }
                else {
                    wallGrid[row][col] = false; 
                }
            }
        }
        return new Walls(wallGrid, new CellPosition(0, 0));
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> wallGrids = new ArrayList<GridCell<Character>>();
        for (int row = 0; row < wallGrid.length; row++) {
            for (int col = 0; col < wallGrid[0].length; col++) {
                if (wallGrid[row][col] == true) {
                    CellPosition cellPos = new CellPosition(pos.row() + row, pos.col() + col);
                    GridCell<Character> cell = new GridCell<Character>(cellPos, 'W');
                    wallGrids.add(cell);
                }
            }
        }
        return wallGrids.iterator();
    }
}
