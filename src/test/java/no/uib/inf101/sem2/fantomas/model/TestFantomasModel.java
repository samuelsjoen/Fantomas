// Some tests based on tests from Tetris

package no.uib.inf101.sem2.fantomas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.view.ViewableFantomasModel;

public class TestFantomasModel {

    @Test
    public void initialPositionOfPlayer() {
        FantomasBoard board = new FantomasBoard(50, 50);
        ViewableFantomasModel model = new FantomasModel(board);

        List<GridCell<Character>> playerCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerOnBoard()) {
            playerCells.add(gc);
        }

        assertEquals(11, playerCells.size());
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(24, 24), 'U')));
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(24, 25), 'U')));
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(24, 26), 'U')));
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(25, 23), 'U')));
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(25, 24), 'U')));
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(25, 25), 'U')));
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(25, 26), 'U')));
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(25, 27), 'U')));
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(26, 24), 'U')));
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(26, 25), 'U')));
        assertTrue(playerCells.contains(new GridCell<>(new CellPosition(26, 26), 'U')));
    }

    @Test
    public void testMovePlayerReturnsTrue() {
        FantomasBoard board = new FantomasBoard(50, 50);
        ViewableFantomasModel model = new FantomasModel(board);
        assertTrue(model.movePlayer(1, 0));
    }

    @Test
    public void testMovePlayerIteration() {
        FantomasBoard board = new FantomasBoard(20, 10);
        ViewableFantomasModel model = new FantomasModel(board);
        List<GridCell<Character>> playerCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerOnBoard()) {
            playerCells.add(gc);
        }
        model.movePlayer(1, 0);
        List<GridCell<Character>> playerCells2 = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerOnBoard()) {
            playerCells2.add(gc);
        }
        assertTrue(playerCells != playerCells2);
    }

    @Test
    public void cantMovePlayerOutOfBoard() {
        FantomasBoard board = new FantomasBoard(20, 10);
        ViewableFantomasModel model = new FantomasModel(board);
        List<GridCell<Character>> playerCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerOnBoard()) {
            playerCells.add(gc);
        }
        assertFalse(model.movePlayer(100, 0));
        model.movePlayer(100, 0);
        List<GridCell<Character>> playerCells2 = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerOnBoard()) {
            playerCells2.add(gc);
        }
        assertTrue(playerCells.equals(playerCells2));
    }

    @Test
    public void cantMovePlayerInOccupiedSpace() {
        FantomasBoard board = new FantomasBoard(50, 50);
        ViewableFantomasModel model = new FantomasModel(board);
        board.set(new CellPosition(26, 27), 'W');
        assertFalse(model.movePlayer(0, 1));
    }

    @Test
    public void testMovePlayerToNewRoom() {
        FantomasBoard board = new FantomasBoard(50, 50);
        ViewableFantomasModel model = new FantomasModel(board);
        assertTrue(model.getRoomName() == "Baroque");
        board.setRow(0, 'P');
        model.movePlayer(-23, 0);
        model.movePlayer(-1, 0);
        List<GridCell<Character>> playerCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getPlayerOnBoard()) {
            playerCells.add(gc);
        }
        assertFalse(playerCells.contains(new GridCell<>(new CellPosition(0, 24), 'U')));
        assertTrue(model.getRoomName() == "Impressionism");
    }

    @Test
    public void testClearBoard() {
        FantomasBoard board = new FantomasBoard(50, 50);
        ViewableFantomasModel model = new FantomasModel(board);
        board.setRow(0, 'P');
        String board1 = board.prettyString();
        model.clearBoard();
        String board2 = board.prettyString();
        assertNotEquals(board1, board2);
    }
}
