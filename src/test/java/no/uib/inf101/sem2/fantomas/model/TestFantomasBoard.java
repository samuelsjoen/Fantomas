package no.uib.inf101.sem2.fantomas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import no.uib.inf101.sem2.fantomas.grid.CellPosition;

public class TestFantomasBoard {

  @Test // Test loaned from Tetris
  public void testPrettyString() {
    FantomasBoard board = new FantomasBoard(3, 4);
    board.set(new CellPosition(0, 0), 'g');
    board.set(new CellPosition(0, 3), 'y');
    board.set(new CellPosition(2, 0), 'r');
    board.set(new CellPosition(2, 3), 'b');
    String expected = String.join("\n", new String[] {
        "g--y",
        "----",
        "r--b"
    });
    assertEquals(expected, board.prettyString());
  }

}
