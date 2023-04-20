package no.uib.inf101.sem2.fantomas.view;

import java.util.List;

import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.model.GameState;
import no.uib.inf101.sem2.fantomas.model.rooms.Door;
import no.uib.inf101.sem2.fantomas.model.rooms.Painting;
import no.uib.inf101.sem2.grid.GridDimension;

public interface ViewableFantomasModel {
    // Returns the dimension of the grid that makes up the board
    public GridDimension getDimension();
    // Allows the tiles on the board to be iterated through
    public Iterable<GridCell<Character>> getTilesOnBoard();
    public Iterable<GridCell<Character>> getPlayerOnBoard();
    public List<Door> getDoorsForRoom();
    public List<Painting> getPaintingsForRoom();
    public String getRoomName();
    public void glueDoorToBoard(Door door);
    public void gluePaintingToBoard(Painting painting);
    public Iterable<GridCell<Character>> getWallsOnBoard();
    // Returns the current game state
    public GameState getGameState();
    // Moves the player in the direction given by the parameters if the move is legal
    public boolean movePlayer(int deltaRow, int deltaCol);
    // Changes the game state 
    public void setGameState(GameState state);

}
