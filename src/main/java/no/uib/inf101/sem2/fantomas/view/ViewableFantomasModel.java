package no.uib.inf101.sem2.fantomas.view;

import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.model.GameState;
import no.uib.inf101.sem2.grid.GridDimension;

public interface ViewableFantomasModel {
    // Returns the dimension of the grid that makes up the board
    public GridDimension getDimension();
    // Allows the tiles on the board to be iterated through
    public Iterable<GridCell<Character>> getTilesOnBoard();
    public Iterable<GridCell<Character>> getPlayerOnBoard();
    public Iterable<GridCell<Character>> getDoorOnBoard();
    // Returns the current game state
    public GameState getGameState();
    // Moves the player in the direction given by the parameters if the move is legal
    public boolean movePlayer(int deltaRow, int deltaCol);
    // Changes the game state 
    public void setGameState(GameState state);

}
