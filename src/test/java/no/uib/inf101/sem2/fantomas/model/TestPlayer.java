// Some tests based on Tetris tests

package no.uib.inf101.sem2.fantomas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.grid.GridDimension;

//The classes Walls, Painting, Carpet and Door all adopt similar methods and structures as Player,
//and therefore a lot of the tests here represent all these classes. If proven to work here, they
//will work in the other classes as well. The only differences are the class objects they return
//as well as some of the parameters, which play no part in the main methods like shiftedBy() etc
//Adtionally some of the tests are based on tests from my submission of Tetris for Sem1.

public class TestPlayer {
    @Test
    public void testHashCodeAndEquals() {
        Player u1 = Player.newPlayer('U');
        Player u2 = Player.newPlayer('U');
        Player u3 = Player.newPlayer('U').shiftedBy(1, 0);
        Player r1 = Player.newPlayer('R');
        Player r2 = Player.newPlayer('R').shiftedBy(0, 0);

        assertEquals(u1, u2);
        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
        assertEquals(r1.hashCode(), r2.hashCode());
        assertNotEquals(u1, u3);
        assertNotEquals(u1, r1);
    }

    @Test
    public void PlayerIterationOfU() {
        // Create a standard 'U' Player placed at (10, 100) to test
        Player player = Player.newPlayer('U');
        player = player.shiftedBy(10, 100);

        // Collect which objects are iterated through
        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : player) {
            objs.add(gc);
        }

        // Check that we got the expected GridCell objects
        assertEquals(11, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 100), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 102), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 103), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 104), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(13, 101), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(13, 102), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(13, 101), 'U')));

    }

    @Test
    public void PlayerIterationOfR() {
        // Create a standard 'R' Player placed at (10, 100) to test
        Player player = Player.newPlayer('R');
        player = player.shiftedBy(10, 100);

        // Collect which objects are iterated through
        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : player) {
            objs.add(gc);
        }

        // Check that we got the expected GridCell objects
        assertEquals(11, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(10, 102), 'R')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'R')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'R')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 103), 'R')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'R')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 102), 'R')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 103), 'R')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(13, 101), 'R')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(13, 102), 'R')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(13, 103), 'R')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(14, 102), 'R')));
    }

    @Test
    public void testPlayerDouble() {
        Player player = Player.newPlayer('U');
        player = player.shiftedBy(10, 0);
        int pos1 = player.getPos().row();
        player = player.shiftedBy(10, 0);
        int pos2 = player.getPos().row();

        assertTrue(pos1 == pos2 / 2);
    }

    @Test
    public void testShiftedToCenter() {
        Player player = Player.newPlayer('U');
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

        player = player.shiftedToCenter(grid);

        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : player) {
            objs.add(gc);
        }

        assertTrue(objs.contains(new GridCell<>(new CellPosition(9, 9), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(9, 10), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(9, 11), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(10, 8), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(10, 9), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(10, 10), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(10, 11), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(10, 12), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 9), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 10), 'U')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 11), 'U')));

    }

    @Test
    public void testRotation() {
        Player player = Player.newPlayer('D');
        Player rotatedPlayer = player.rotatedPlayer('L');
        assertNotEquals(player, rotatedPlayer);

        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : player) {
            objs.add(gc);
        }

        assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 1), 'D')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 2), 'D')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 3), 'D')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(2, 0), 'D')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(2, 1), 'D')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(2, 2), 'D')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(2, 3), 'D')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(2, 4), 'D')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(3, 1), 'D')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(3, 2), 'D')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(3, 3), 'D')));

        List<GridCell<Character>> objs2 = new ArrayList<>();
        for (GridCell<Character> gc : rotatedPlayer) {
            objs.add(gc);
        }

        assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 2), 'L')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 1), 'L')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 2), 'L')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 3), 'L')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(2, 1), 'L')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(2, 2), 'L')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(2, 3), 'L')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(3, 1), 'L')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(3, 2), 'L')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(3, 3), 'L')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(4, 2), 'L')));

        assertNotEquals(objs, objs2);
    }

    @Test
    // Checking if the get methods are copies and don't mutate the original values.
    public void testGetMethods() {
        Player player = Player.newPlayer('D');
        CellPosition pos = player.getPos();
        char symbol = player.getSymbol();
        pos = new CellPosition(10, 10);
        symbol = 'R';
        CellPosition pos2 = player.getPos();
        char symbol2 = player.getSymbol();
        assertNotEquals(pos, pos2);
        assertNotEquals(symbol, symbol2);
    }
}
