// Loaned from Tetris

package no.uib.inf101.sem2.fantomas.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {
    private List<List<E>> gridList;

    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }

    // The grid which makes up the board
    public Grid(int rows, int cols, E defaultValue) {
        gridList = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            List<E> columnList = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
                columnList.add(defaultValue);
            }

            gridList.add(columnList);
        }
    }

    @Override
    public int rows() {
        return gridList.size();
    }

    @Override
    public int cols() {
        if (gridList.size() == 0)
            return 0;

        return gridList.get(0).size();
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        ArrayList<GridCell<E>> gridCells = new ArrayList<GridCell<E>>();
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                CellPosition pos = new CellPosition(row, col);
                GridCell<E> cell = new GridCell<>(pos, get(pos));
                gridCells.add(cell);
            }
        }
        return gridCells.iterator();
    }

    @Override
    public void set(CellPosition pos, E value) {
        if (!positionIsOnGrid(pos))
            throw new IndexOutOfBoundsException("Position " + pos + " is not on grid!");

        List<E> row = gridList.get(pos.row());
        row.set(pos.col(), value);
    }

    @Override
    public E get(CellPosition pos) {
        if (!positionIsOnGrid(pos))
            throw new IndexOutOfBoundsException("Position " + pos + " is not on grid!");

        List<E> row = gridList.get(pos.row());
        E column = row.get(pos.col());
        return column;
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        if (pos.row() < 0 || pos.col() < 0)
            return false;
        if (pos.row() >= rows())
            return false;
        if (pos.col() >= cols())
            return false;
        return true;
    }

}
