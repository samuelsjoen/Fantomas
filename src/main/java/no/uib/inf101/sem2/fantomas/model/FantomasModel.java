package no.uib.inf101.sem2.fantomas.model;

import no.uib.inf101.sem2.fantomas.grid.CellPosition;

import java.util.List;

import no.uib.inf101.sem2.fantomas.controller.ControllableFantomasModel;
import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.view.ViewableFantomasModel;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.fantomas.model.rooms.Carpet;
import no.uib.inf101.sem2.fantomas.model.rooms.Door;
import no.uib.inf101.sem2.fantomas.model.rooms.Painting;
import no.uib.inf101.sem2.fantomas.model.rooms.Room;
import no.uib.inf101.sem2.fantomas.model.rooms.Walls;

public class FantomasModel implements ViewableFantomasModel, ControllableFantomasModel {

    private FantomasBoard board;
    public GameState gameState;
    public Player player;
    public int roomNumber;
    public char currentPaintingNumber;

    public FantomasModel(FantomasBoard board) {
        super();
        this.gameState = GameState.START_SCREEN;
        this.board = board;
        this.player = Player.newPlayer('U').shiftedToCenter(board);
        this.roomNumber = 1;
        this.currentPaintingNumber = '0';
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

    public void viewPainting() {
        if (isByPainting() == true) {
            setGameState(GameState.PAINTINGVIEW);
        }
    }

    public Player movePlayerToNewRoom() {
        changeRoom();
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

    public CellPosition getCellPositionInFacingDirection() {
        CellPosition pos = switch (player.getSymbol()) {
            case 'U' -> new CellPosition(-1, 0);
            case 'D' -> new CellPosition(1, 0);
            case 'R' -> new CellPosition(0, 1);
            case 'L' -> new CellPosition(0, -1);
            default -> throw new IllegalArgumentException("No symbol such as "+ player.getSymbol());
        };
        return pos;
    }

    public boolean isByPainting() {
        CellPosition newPos = getCellPositionInFacingDirection();
        Player shiftedPlayer = player.shiftedBy(newPos.row(), newPos.col());
        char[] paintingNumbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int count = 0;
        for (GridCell<Character> gc : shiftedPlayer) {
            for (char number : paintingNumbers) {
                if (board.get(new CellPosition(gc.pos().row(), gc.pos().col())) == number) {
                    count += 1;
                    currentPaintingNumber = number;
                }
            }
        }
        if (count == 3) {
            return true;
        }
        return false;
    }

    public String getPaintingPath() {
        for (Painting painting : getPaintingsForRoom()) {
            if (painting.getNumber() == currentPaintingNumber) {
                return painting.getPath();
            }
        }
        throw new IllegalArgumentException("No path found for painting");
    }

    public String getPaintingInfo() {
        for (Painting painting : getPaintingsForRoom()) {
            if (painting.getNumber() == currentPaintingNumber) {
                return painting.getPaintinginfo();
            }
        }
        throw new IllegalArgumentException("No info found for painting");
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
    public Iterable<GridCell<Character>> getWallsOnBoard() {
        Walls walls = Walls.newWalls(this.board);
        for (GridCell<Character> gc : walls) {
            board.set(gc.pos(), 'W');
        }
        return walls;
    }

    @Override
    public Iterable<GridCell<Character>> getCarpetOnBoard() {
        return Carpet.newCarpet(getCarpetColor());
    }

    private char getCarpetColor() {
        char color = switch (roomNumber) {
            case 1 -> 'M';
            case 2 -> 'Y';
            case 3 -> 'G';
            case 4 -> 'O';
            default -> throw new IllegalArgumentException("No available color for "+roomNumber);
        };
        return color;
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

    @Override
    public void gluePaintingToBoard(Painting painting) {
        for (GridCell<Character> gc : painting) {
            board.set(gc.pos(), painting.getNumber());
        }
    }

    @Override
    public List<Painting> getPaintingsForRoom() {
        return Room.getPaintingsForRoom(roomNumber, board);
    }
}
