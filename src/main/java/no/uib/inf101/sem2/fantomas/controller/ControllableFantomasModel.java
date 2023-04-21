package no.uib.inf101.sem2.fantomas.controller;

import no.uib.inf101.sem2.fantomas.model.GameState;

public interface ControllableFantomasModel {
    // Moves the charcter in the direction specified with the parameters
    public boolean movePlayer(int deltaRow, int deltaCol);
    // Rotates the charcter in the direction specified with the parameters
    public boolean rotatePlayer(char c);
    // Gets the current game state, either ACTIVE, START_SCREEN, PAUSE or GAME_OVER
    public GameState getGameState();
    // Changes the game state
    public void setGameState(GameState state);
    // Resets the board
    public void clearBoard();
    public void viewPainting();
}
