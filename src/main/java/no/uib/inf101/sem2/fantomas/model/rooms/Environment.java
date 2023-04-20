package no.uib.inf101.sem2.fantomas.model.rooms;

import no.uib.inf101.sem2.grid.GridDimension;

public interface Environment {
    public boolean[][] newOrientation(String direction);
    public void createNew(String direction);
    public void shiftedBy(int deltaRow, int deltaCol);
    public void shiftedToWall(GridDimension grid, String wall);
}
