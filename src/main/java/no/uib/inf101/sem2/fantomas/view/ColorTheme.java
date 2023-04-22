// Loaned and expanded upon from Tetris

package no.uib.inf101.sem2.fantomas.view;

import java.awt.Color;

public interface ColorTheme {
    /** Returns the color for cells depending on the given value */
    public Color getCellColor(char character);
    /**Returns the color for the frame*/
    public Color getFrameColor();
    /**Returns the color for the doors*/
    public Color getDoorColor();
    /**Returns the color for the paintings*/
    public Color getPaintingColor();
    /**Returns the color for the background*/
    public Color getBackgroundColor();
    /**Returns the color for the game over screen*/
    public Color getGameOverColor();
    /**Returns the color for the start screen*/
    public Color getStartScreenColor();
    /**Returns the color for the pause screen*/
    public Color getPauseColor();
    /**Returns the color for the paintview screen*/
    public Color getPaintViewColor();
    /**Returns the color for textcolor 1*/
    public Color textColor();
    /**Returns the color for textcolor 2*/
    public Color textColor2();
    /**Returns the color for textcolor 3*/
    public Color textColor3();
}
