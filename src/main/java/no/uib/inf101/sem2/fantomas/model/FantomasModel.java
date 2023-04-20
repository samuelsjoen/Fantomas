package no.uib.inf101.sem2.fantomas.model;

import no.uib.inf101.sem2.fantomas.grid.CellPosition;

import java.util.List;

import no.uib.inf101.sem2.fantomas.controller.ControllableFantomasModel;
import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.view.ViewableFantomasModel;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.fantomas.model.player.Player;
import no.uib.inf101.sem2.fantomas.model.rooms.Door;
import no.uib.inf101.sem2.fantomas.model.rooms.Room;

public class FantomasModel implements ViewableFantomasModel, ControllableFantomasModel {

    private FantomasBoard board;
    public GameState gameState;
    public Player player;
    public int roomNumber;

    public FantomasModel(FantomasBoard board) {
        super();
        this.gameState = GameState.START_SCREEN;
        this.board = board;
        this.player = Player.newPlayer('U').shiftedToCenter(board);
        this.roomNumber = 1;
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
        if (isLegalMove(shiftedPlayer) == true) {
            player = shiftedPlayer;
            return true;
        }
        if (isByDoor(shiftedPlayer) == true) {
            player = movePlayerToNewRoom();
        }
        return false;   
    }


    public Player movePlayerToNewRoom() {
        changeRoom();
        System.out.println(player.pos);
        if (player.pos.col() == 0) {
            Player shiftedPlayer = player.shiftedBy(0, board.cols()-5);
            return shiftedPlayer;
        }
        if (player.pos.col() == board.cols()-5) {
            Player shiftedPlayer = player.shiftedBy(0, -(board.cols()-5));
            return shiftedPlayer;
        }
        if (player.pos.row() == 0) {
            Player shiftedPlayer = player.shiftedBy(board.rows()-5, 0);
            return shiftedPlayer;
        }
        if (player.pos.row() == board.rows()-5) {
            Player shiftedPlayer = player.shiftedBy(-(board.rows()-5), 0);
            return shiftedPlayer;
        }
        return player;
    }

    @Override
    public String getRoomName() {
        String roomName = switch(roomNumber) {
            case 1 -> "Baroque";
            case 2 -> "Impressionism";
            case 3 -> "Expressionism";
            case 4 -> "Samuel Sjøen";
            default -> throw new IllegalArgumentException("No available name for " + roomNumber);
        };
        return roomName;
    }

    public void changeRoom() {
        clearBoard();
        if (roomNumber == 1) {;
            if (player.symbol == 'U') {
                this.roomNumber = 2;
            }
            if (player.symbol == 'R') {
                this.roomNumber = 4;
            }
        }
        if (roomNumber == 2) {
            if (player.symbol == 'D') {
                this.roomNumber = 1;
            }
            if (player.symbol == 'R') {
                this.roomNumber = 3;
            }
        }
        if (roomNumber == 3) {
            if (player.symbol == 'D') {
                this.roomNumber = 4;
            }
            if (player.symbol == 'L') {
                this.roomNumber = 2;
            }
        }
        if (roomNumber == 4) {
            if (player.symbol == 'U') {
                this.roomNumber = 3;
            }
            if (player.symbol == 'L') {
                this.roomNumber = 1;
            }
        }
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

    public boolean isByDoor(Player shiftedPlayer) {
        int count = 0;
        for (GridCell<Character> gc : shiftedPlayer) {
            if (board.get(new CellPosition(gc.pos().row(), gc.pos().col())) == 'P') {
                count += 1;
            }
        }
        if (count == 3) {
            return true;
        }
        return false;
    }


    // Checks if the charaacters position is legal and returns a boolean value in accordance
    public boolean isLegalMove(Player player) {

        for (GridCell<Character> gc : player) {
            if (board.get(new CellPosition(gc.pos().row(), gc.pos().col())) != '-') {
                return false;
            }
            if (isOutOfBounds(player) == true) {
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
    public List<Door> getDoorsForRoom() {
        return Room.getDoorsForRoom(roomNumber, board);
    }

    @Override
    public void glueDoorToBoard(Door door) {
        for (GridCell<Character> gc : door) {
            board.set(gc.pos(), 'P');
        }
    }

}
