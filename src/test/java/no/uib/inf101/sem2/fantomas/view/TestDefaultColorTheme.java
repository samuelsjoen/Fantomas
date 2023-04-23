//Test loaned from Tetris

package no.uib.inf101.sem2.fantomas.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.awt.Color;
import org.junit.jupiter.api.Test;

public class TestDefaultColorTheme {
    @Test
    public void sanityTestDefaultColorTheme() {
        ColorTheme colors = new DefaultColorTheme();
        assertEquals(Color.black, colors.getGameOverColor());
        assertEquals(Color.white, colors.getFrameColor());
        assertEquals(Color.black, colors.getCellColor('U'));
        assertEquals(new Color(255, 228, 185), colors.getCellColor('-'));
        assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));
    }
}
