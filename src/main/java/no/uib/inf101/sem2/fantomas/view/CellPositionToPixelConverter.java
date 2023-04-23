// Loaned from Tetris

package no.uib.inf101.sem2.fantomas.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.grid.GridDimension;

public class CellPositionToPixelConverter {
    Rectangle2D box;
    GridDimension gd;
    double margin;

    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        super();
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

    /** Gets the dimensions of each cell in the grid and creates it */
    public Rectangle2D getBoundsForCell(CellPosition cp) {
        double cellWidth = (box.getWidth() - (margin * (gd.cols() + 1))) / gd.cols();
        double cellHeight = ((box.getHeight() - (margin * (gd.rows() + 1))) / gd.rows());
        double cellX = cellX(cp, cellWidth);
        double cellY = cellY(cp, cellHeight);

        Rectangle2D cell = new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
        return cell;
    }

    /**
     * Returns the cellX value used to place the cell in it's correct place on the
     * board
     */
    private double cellX(CellPosition cp, double cellWidth) {
        if (cp.col() == 0)
            return box.getX() + margin;
        else
            return box.getX() + margin + (cp.col() * (margin + cellWidth));
    }

    /**
     * Returns the cellY value used to place the cell in it's correct place on the
     * board
     */
    private double cellY(CellPosition cp, double cellHeight) {
        if (cp.row() == 0)
            return box.getY() + margin;
        else
            return box.getY() + margin + (cp.row() * (margin + cellHeight));
    }
}
