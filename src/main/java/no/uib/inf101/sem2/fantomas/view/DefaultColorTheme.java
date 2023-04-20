package no.uib.inf101.sem2.fantomas.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(char character) {
        Color color = switch (character) {
            case '-' -> new Color(255, 228, 185);
            case 'W' -> new Color(100, 0, 5);
            case 'A' -> new Color(180, 114, 0);
            case 'P' -> new Color(255, 228, 185);
            case 'U' -> Color.black;
            case 'R' -> Color.black;
            case 'D' -> Color.black;
            case 'L' -> Color.black;
            default -> throw new IllegalArgumentException("No available color for " + character);
        };
        return color;
    }

    @Override
    public Color getFrameColor() {
        return Color.white;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.white;
    }

    @Override
    public Color getGameOverColor() {
        return Color.black;
    }

    @Override
    public Color textColor() {
        return Color.orange;
    }

    @Override
    public Color getStartScreenColor() {
        return Color.white;
    }

    @Override
    public Color textColor2() {
        return Color.white;
    }

    @Override
    public Color textColor3() {
        return Color.black;
    }

    @Override
    public Color getPauseColor() {
        return Color.cyan;
    }

    @Override
    public Color getDoorColor() {
        return Color.white;
    }

}
