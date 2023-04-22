package no.uib.inf101.sem2.fantomas.view;

import java.util.List;

import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.grid.GridDimension;
import no.uib.inf101.sem2.fantomas.model.GameState;
import no.uib.inf101.sem2.fantomas.model.rooms.Door;
import no.uib.inf101.sem2.fantomas.model.rooms.Painting;

public interface ViewableFantomasModel {
    /**Returns the dimension of the grid that makes up the board */
    public GridDimension getDimension();
    /**Returns iterable grid for the tiles*/
    public Iterable<GridCell<Character>> getTilesOnBoard();
    /**Returns iterable grid for the plyer*/
    public Iterable<GridCell<Character>> getPlayerOnBoard();
    /**Returns iterable grid for the carpet*/
    public Iterable<GridCell<Character>> getCarpetOnBoard();
    /**Returns iterable grid for the walls*/
    public Iterable<GridCell<Character>> getWallsOnBoard();
    /**Returns list of doors in current room*/
    public List<Door> getDoorsForRoom();
    /**Glues doors to the board*/
    public void glueDoorToBoard(Door door);
    /**Returns list of paintings in current room*/
    public List<Painting> getPaintingsForRoom();
    /**Glues painting to the board*/
    public void gluePaintingToBoard(Painting painting);
    /**Returns the file path for painting*/
    public String getPaintingPath();
    /**Returns info about painting*/
    public String getPaintingInfo();
    /**Returns room name*/
    public String getRoomName();
    /**Returns the current game state*/
    public GameState getGameState();
    /**Changes the game state*/
    public void setGameState(GameState state);
    /**Moves the player in the direction given by the parameters if the move is legal*/
    public boolean movePlayer(int deltaRow, int deltaCol);
}
