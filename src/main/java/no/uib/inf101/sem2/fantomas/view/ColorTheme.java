package no.uib.inf101.sem2.fantomas.view;

import java.awt.Color;

public interface ColorTheme {
    // Returns the color for cells depending on the given value
    public Color getCellColor(char character);
    // Returns the color for the frame
    public Color getFrameColor();
    // Returns the color for the background
    public Color getBackgroundColor();
    // Returns the color for the game over screen
    public Color getGameOverColor();
    // Returns the color for the start screen
    public Color getStartScreenColor();
    // Returns various different text colors
    public Color getDoorColor();
    public Color textColor();
    public Color textColor2();
    public Color textColor3();
    // Returns the color for the pause screen
    public Color getPauseColor();
    public Color getPaintingColor();
}
