package no.uib.inf101.sem2.fantomas.model;

import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.controller.ControllableFantomasModel;
import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.view.ViewableFantomasModel;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.fantomas.model.player.Player;

public class FantomasModel implements ViewableFantomasModel, ControllableFantomasModel {

    private FantomasBoard board;
    public GameState gameState;
    public Player player;
    public Door door;

    public FantomasModel(FantomasBoard board) {
        super();
        this.gameState = GameState.START_SCREEN;
        this.board = board;
        this.player = Player.newPlayer('U').shiftedToCenter(board);
        this.door = Door.newDoor("Vertical");
    }

    @Override
    public GridDimension getDimension() {
        return this.board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.board;
    }

    @Override
    public boolean movePlayer(int deltaRow, int deltaCol) {
        Player shiftedPlayer = player.shiftedBy(deltaRow, deltaCol);
        if (isNewRoom(player, shiftedPlayer) == true) {
            Player newRoomPlayer = movePlayerToNewRoom();
            player = newRoomPlayer;
            return true;
        }

        else if (isLegalMove(shiftedPlayer) == true) {
            player = shiftedPlayer;
            return true;
        } else {
            return false;
        }

    }


    public Player movePlayerToNewRoom() {

        if (player.pos.col() == 0) {
            Player shiftedPlayer = player.shiftedBy(board.cols()-5, 0);
            return shiftedPlayer;
        }
        if (player.pos.col() == board.cols()-5) {
            Player shiftedPlayer = player.shiftedBy(-board.cols()-5, 0);
            return shiftedPlayer;
        }
        if (player.pos.row() == 0) {
            Player shiftedPlayer = player.shiftedBy(0, board.rows()-5);
            return shiftedPlayer;
        }
        if (player.pos.row() == board.rows()-5) {
            Player shiftedPlayer = player.shiftedBy(-board.cols()-5, 0);
            return shiftedPlayer;
        }
        return player;
    }
    
    @Override
    public boolean rotatePlayer(char c) {
        Player rotatedPlayer = player.rotatedPlayer(c);
        if (isLegalMove(rotatedPlayer) == true) {
            player = rotatedPlayer;
            return true;
        } else {
            return false;
        } 
    }

    @Override
    public void clearBoard() {
        for (int row = 0; row < board.rows(); row++) {
            board.setRow(row, '-');
        }
    }

    public boolean isOutOfBounds(Player player) {

        for (GridCell<Character> gc : player) {
            if (gc.pos().col() >= board.cols()) {
                return true;
            }
            if (gc.pos().col() < 0) {
                return true;
            }
            if (gc.pos().row() >= board.rows()) {
                return true;
            }
            if (gc.pos().row() < 0) {
                return true; 
            }
        }
        return false;
    }

    public boolean isNewRoom(Player player, Player shiftedPlayer) {
        for (GridCell<Character> gc : player) {
            if (board.get(new CellPosition(gc.pos().row(), gc.pos().col())) == 'W') {
                if (isOutOfBounds(shiftedPlayer) == true) {
                    return true;
                }
            }
        }
        return false;
    }


    // Checks if the charaacters position is legal and returns a boolean value in accordance
    public boolean isLegalMove(Player player) {

        for (GridCell<Character> gc : player) {
            if (isOutOfBounds(player) == true) {
                return false;
            }
            if (board.get(new CellPosition(gc.pos().row(), gc.pos().col())) != '-') {
                return false;
            }
        }
        return true;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState state) {
        gameState = state;
    }

    @Override
    public Iterable<GridCell<Character>> getPlayerOnBoard() {
        return this.player;
    }

    @Override
    public Iterable<GridCell<Character>> getDoorOnBoard() {
        return this.door;
    }

}
