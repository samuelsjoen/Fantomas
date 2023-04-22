package no.uib.inf101.sem2.fantomas.model.rooms;

import java.util.ArrayList;
import java.util.Iterator;

import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridCell;

public class Carpet implements Iterable<GridCell<Character>> {

    boolean[][] carpetGrid;
    CellPosition pos;
    char color;

    public Carpet(boolean[][] carpetGrid, CellPosition pos, char color) {
        super();
        this.carpetGrid = carpetGrid;
        this.pos = pos;
        this.color = color;
    }

    public static Carpet newCarpet(char color) {
        boolean[][] carpetGrid = new boolean[40][40];
        for (int row = 0; row < 40; row++) {
            for (int col = 0; col < 40; col++) {
                carpetGrid[row][col] = true;
            }
        }
        return new Carpet(carpetGrid, new CellPosition(5, 5), color);
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> carpetGrids = new ArrayList<GridCell<Character>>();
        for (int row = 0; row < carpetGrid.length; row++) {
            for (int col = 0; col < carpetGrid[0].length; col++) {
                if (carpetGrid[row][col] == true) {
                    CellPosition cellPos = new CellPosition(pos.row() + row, pos.col() + col);
                    GridCell<Character> cell = new GridCell<Character>(cellPos, color);
                    carpetGrids.add(cell);
                }
            }
        }
        return carpetGrids.iterator();
    }
    
}
