package no.uib.inf101.sem2.fantomas.model.rooms;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.grid.GridDimension;

//This test represents the shiftedToWall method for the class Painting as well
public class TestDoor {
    @Test
    public void testShiftedToWall() {
        Door door = Door.newDoor("horizontal");
        GridDimension grid = new GridDimension() {

            @Override
            public int rows() {
                int rows = 20;
                return rows;
            }

            @Override
            public int cols() {
                int cols = 20;
                return cols;
            }

        };
        door = door.shiftedToWall(grid, "south");

        ArrayList<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : door) {
            objs.add(gc);
        }

        assertTrue(objs.contains(new GridCell<>(new CellPosition(19, 6), 'P')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(19, 7), 'P')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(19, 8), 'P')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(19, 9), 'P')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(19, 10), 'P')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(19, 11), 'P')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(19, 12), 'P')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(19, 13), 'P')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(19, 14), 'P')));

    }
}
