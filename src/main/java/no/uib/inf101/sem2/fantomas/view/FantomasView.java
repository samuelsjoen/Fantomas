package no.uib.inf101.sem2.fantomas.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.model.GameState;
import no.uib.inf101.sem2.fantomas.model.rooms.Door;
import no.uib.inf101.sem2.fantomas.model.rooms.Painting;


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
            g2.drawString("FANTOMAS", this.getWidth()/2-130, this.getHeight()/2-55);
            g2.setColor(theme.textColor2());
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString("Press key", this.getWidth()/2-40, this.getHeight()/2);
            g2.drawString("'down' to begin", this.getWidth()/2-70, this.getHeight()/2+20);

        }

        // Draws the game screen including the board, the player.
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            Rectangle2D board = new Rectangle2D.Double(5, 5, (this.getWidth() - 5), (this.getHeight() - 40));
            g2.setColor(theme.getFrameColor());
            g2.fill(board);
            CellPositionToPixelConverter gridPixel = new CellPositionToPixelConverter(board, model.getDimension(), 0);
            drawCells(g2, model.getTilesOnBoard(), gridPixel, theme);
            drawCells(g2, model.getWallsOnBoard(), gridPixel, theme);
            drawCells(g2, model.getCarpetOnBoard(), gridPixel, theme);
            drawCells(g2, model.getPlayerOnBoard(), gridPixel, theme);
            drawDoors(gridPixel, g2);
            drawPaintings(gridPixel, g2);
            g2.setColor(theme.textColor3());
            g2.setFont(new Font("Arial", Font.BOLD, 15));
            // g2.drawString("ROOM: "+model.get(), 5, this.getHeight()-23);
            g2.drawString("ROOM: "+model.getRoomName(), 5, this.getHeight()-7);
            
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

        if (model.getGameState() == GameState.PAINTINGVIEW) {
            Rectangle2D paintViewScreen = new Rectangle2D.Double(5, 5, (this.getWidth() - 5), (this.getHeight() - 5));
            g2.setColor(theme.getPaintViewColor());
            g2.fill(paintViewScreen);
            showPainting(g2);
            g2.setColor(theme.textColor2());
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString("Press key", this.getWidth()/2-50, this.getHeight()-40);
            g2.drawString("'down' to resume", this.getWidth()/2-80, this.getHeight()-20);
            String paintingInfo = model.getPaintingInfo();
            g2.drawString(paintingInfo, (this.getWidth()/2)-(paintingInfo.length()*10/2), this.getHeight()/10);
        }
    }

    // Helping method to draw the cells that make up the board as well as the player and environment
    private void drawCells(Graphics2D g2, Iterable<GridCell<Character>> gc, CellPositionToPixelConverter cptopixel, ColorTheme theme) {
        for (GridCell<Character> grid : gc) {
            Rectangle2D cell = cptopixel.getBoundsForCell(grid.pos());
            g2.setColor(theme.getCellColor(grid.value()));
            g2.fill(cell);
        }
    }

    private void drawDoors(CellPositionToPixelConverter gridPixel, Graphics2D g2) {
        for (Door door : model.getDoorsForRoom()) {
            model.glueDoorToBoard(door);
            drawCells(g2, door, gridPixel, theme);
        }     
    }

    
    private void drawPaintings(CellPositionToPixelConverter gridPixel, Graphics2D g2) {
        for (Painting painting : model.getPaintingsForRoom()) {
            model.gluePaintingToBoard(painting);
            drawCells(g2, painting, gridPixel, theme);
        }     
    }

    private void showPainting(Graphics2D g2) {
        BufferedImage image = Inf101Graphics.loadImageFromResources(model.getPaintingPath());
        double x = getWidth()/2;
        double y = getHeight()/2;
        Inf101Graphics.drawCenteredImage(g2, image, x, y, 0.5);
    }

    private void drawCarpet(CellPositionToPixelConverter gridPixel, Graphics2D g2) {
        
    }
}
