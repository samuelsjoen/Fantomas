//Test loaned from Tetris

package no.uib.inf101.sem2.fantomas.view;

import org.junit.jupiter.api.Test;
import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridDimension;
import no.uib.inf101.sem2.fantomas.model.FantomasBoard;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.geom.Rectangle2D;

public class TestCellPositionToPixelConverter {
    @Test
    public void sanityTest() {
        GridDimension gd = new FantomasBoard(3, 4);
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                new Rectangle2D.Double(29, 29, 340, 240), gd, 30);
        Rectangle2D expected = new Rectangle2D.Double(214, 129, 47.5, 40);
        assertEquals(expected, converter.getBoundsForCell(new CellPosition(1, 2)));
    }
}
