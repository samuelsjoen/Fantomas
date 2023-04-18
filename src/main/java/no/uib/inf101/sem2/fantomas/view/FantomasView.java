package no.uib.inf101.sem2.fantomas.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.model.GameState;


public class FantomasView extends JPanel {

    ViewableFantomasModel model;
    ColorTheme theme;

    int dimensionX = 750;
    int dimensionY = 750;

    // Constructor
    public FantomasView(ViewableFantomasModel model) {
        this.theme = new DefaultColorTheme();
        this.setBackground(theme.getBackgroundColor());
        this.model = model;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(dimensionX, dimensionY));

    }

    // The paintComponent method is called by the Java Swing framework every time
    // either the window opens or resizes, or we call .repaint() on this object.
    // Note: NEVER call paintComponent directly yourself
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    // Draws the different components that make up the game
    private void drawGame(Graphics2D g2) {

        // Draws the start screen and denotes how to start the screen
        if (model.getGameState() == GameState.START_SCREEN) {
            Rectangle2D startScreen = new Rectangle2D.Double(5, 5, (this.getWidth() - 5), (this.getHeight() - 5));
            g2.setColor(theme.getStartScreenColor());
            g2.fill(startScreen);
            g2.setColor(theme.textColor());
            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.drawString("FANTOMAS", this.getWidth()/2-90, this.getHeight()/2-55);
            g2.setColor(theme.textColor3());
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString("Press key", this.getWidth()/2-45, this.getHeight()/2);
            g2.drawString("'down' to begin", this.getWidth()/2-75, this.getHeight()/2+20);

        }

        // Draws the game screen including the board, the player.
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            Rectangle2D board = new Rectangle2D.Double(5, 5, (this.getWidth() - 5), (this.getHeight() - 40));
            g2.setColor(theme.getFrameColor());
            g2.fill(board);
            CellPositionToPixelConverter gridPixel = new CellPositionToPixelConverter(board, model.getDimension(), 0);
            drawCells(g2, model.getTilesOnBoard(), gridPixel, theme);
            drawCells(g2, model.getPlayerOnBoard(), gridPixel, theme);
            drawCells(g2, model.getDoorOnBoard(), gridPixel, theme);
            g2.setColor(theme.textColor3());
            g2.setFont(new Font("Arial", Font.BOLD, 15));
            // g2.drawString("SCORE: "+model.getScore(), 5, this.getHeight()-23);
            // g2.drawString("LEVEL: "+model.getDifficulty(), 5, this.getHeight()-7);
        }

        // Draws the pause screen as well as denoting how to return to the game
        if (model.getGameState() == GameState.PAUSE) {
            Rectangle2D pauseScreen = new Rectangle2D.Double(5, 5, (this.getWidth() - 5), (this.getHeight() - 5));
            g2.setColor(theme.getPauseColor());
            g2.fill(pauseScreen);
            g2.setColor(theme.textColor2());
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            g2.drawString("PAUSE", this.getWidth()/2-50, this.getHeight()/2);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString("Press key", this.getWidth()/2-50, this.getHeight()-40);
            g2.drawString("'down' to resume", this.getWidth()/2-80, this.getHeight()-20);
        }
    }

    // Helping method to draw the cells that make up the board as well as the player and environment
    private void drawCells(Graphics2D g2, Iterable<GridCell<Character>> gc, CellPositionToPixelConverter cptopixel, ColorTheme theme) {
        for (GridCell<Character> grid : gc) {
            Rectangle2D cell = cptopixel.getBoundsForCell(grid.pos());
            g2.setColor(theme.getCellColor(grid.value()));
            // System.out.println(theme.getCellColor(grid.value()));
            g2.fill(cell);
            // System.out.println(grid.value());
        }
    }
}