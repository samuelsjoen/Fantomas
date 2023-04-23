// Based on TetrisBoard.java from Tetris with a lot of new methods added.

package no.uib.inf101.sem2.fantomas.model;

import java.util.List;
import no.uib.inf101.sem2.fantomas.grid.CellPosition;
import no.uib.inf101.sem2.fantomas.controller.ControllableFantomasModel;
import no.uib.inf101.sem2.fantomas.grid.GridCell;
import no.uib.inf101.sem2.fantomas.grid.GridDimension;
import no.uib.inf101.sem2.fantomas.view.ViewableFantomasModel;
import no.uib.inf101.sem2.fantomas.model.rooms.Carpet;
import no.uib.inf101.sem2.fantomas.model.rooms.Door;
import no.uib.inf101.sem2.fantomas.model.rooms.Painting;
import no.uib.inf101.sem2.fantomas.model.rooms.Room;
import no.uib.inf101.sem2.fantomas.model.rooms.Walls;

public class FantomasModel implements ViewableFantomasModel, ControllableFantomasModel {

    private FantomasBoard board;
    private GameState gameState;
    private Player player;
    private int roomNumber;
    private char currentPaintingNumber;

    public FantomasModel(FantomasBoard board) {
        super();
        this.gameState = GameState.START_SCREEN;
        this.board = board;
        this.player = Player.newPlayer('U').shiftedToCenter(board);
        this.roomNumber = 1;
        this.currentPaintingNumber = '0';
    }

    @Override
    public boolean movePlayer(int deltaRow, int deltaCol) {
        Player shiftedPlayer = player.shiftedBy(deltaRow, deltaCol);
        if (isLegalMove(shiftedPlayer) == true) {
            player = shiftedPlayer;
            return true;
        }
        return false;
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

    /**
     * Helping method used to move player to the position they would be in in a new
     * room
     * after having walked through a door
     */
    private Player movePlayerToNewRoom() {
        changeRoom();
        if (player.getPos().col() == 0) {
            rotatePlayer('R');
            Player shiftedPlayer = player.shiftedBy(0, board.cols() - 5);
            return shiftedPlayer;
        }
        if (player.getPos().col() == board.cols() - 5) {
            rotatePlayer('L');
            Player shiftedPlayer = player.shiftedBy(0, -(board.cols() - 5));
            return shiftedPlayer;
        }
        if (player.getPos().row() == 0) {
            rotatePlayer('D');
            Player shiftedPlayer = player.shiftedBy(board.rows() - 5, 0);
            return shiftedPlayer;
        }
        if (player.getPos().row() == board.rows() - 5) {
            rotatePlayer('U');
            Player shiftedPlayer = player.shiftedBy(-(board.rows() - 5), 0);
            return shiftedPlayer;
        }
        return player;
    }

    /** Helping method to check if a move is legal or not */
    private boolean isLegalMove(Player movedPlayer) {
        if (isOutOfBounds(movedPlayer) == true) {
            return false;
        }
        if (isInDoor(movedPlayer) == true) {
            player = movePlayerToNewRoom();
            return false;
        }
        for (GridCell<Character> gc : movedPlayer) {
            if (board.get(new CellPosition(gc.pos().row(), gc.pos().col())) != '-') {
                return false;
            }
        }
        return true;
    }

    /** Helping method to check if a move is within the grid */
    private boolean isOutOfBounds(Player player) {

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

    /** Helping method to change room number */
    private void changeRoom() {
        clearBoard();
        if (roomNumber == 1) {
            if (player.getPos().row() == 0) {
                this.roomNumber = 2;
            }
            if (player.getPos().col() == board.cols() - 5) {
                this.roomNumber = 4;
            }
        }
        if (roomNumber == 2) {
            if (player.getPos().row() == board.rows() - 5) {
                this.roomNumber = 1;
            }
            if (player.getPos().col() == board.cols() - 5) {
                this.roomNumber = 3;
            }
        }
        if (roomNumber == 3) {
            if (player.getPos().row() == board.rows() - 5) {
                this.roomNumber = 4;
            }
            if (player.getPos().col() == 0) {
                this.roomNumber = 2;
            }
        }
        if (roomNumber == 4) {
            if (player.getPos().row() == 0) {
                this.roomNumber = 3;
            }
            if (player.getPos().col() == 0) {
                this.roomNumber = 1;
            }
        }
    }

    /** Helping method to check if player is in a door */
    private boolean isInDoor(Player player) {
        int count = 0;
        for (GridCell<Character> gc : player) {
            if (board.get(new CellPosition(gc.pos().row(), gc.pos().col())) == 'P') {
                count += 1;
            }
        }
        if (count == 3) {
            return true;
        }
        return false;
    }

    /** Helping method to check if player is by a painting */
    private boolean isByPainting() {
        CellPosition newPos = getCellPositionInFacingDirection();
        Player shiftedPlayer = player.shiftedBy(newPos.row(), newPos.col());
        char[] paintingNumbers = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
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

    /**
     * Helping method to get the next cellpos in the direction the player is facing
     */
    private CellPosition getCellPositionInFacingDirection() {
        CellPosition pos = switch (player.getSymbol()) {
            case 'U' -> new CellPosition(-1, 0);
            case 'D' -> new CellPosition(1, 0);
            case 'R' -> new CellPosition(0, 1);
            case 'L' -> new CellPosition(0, -1);
            default -> throw new IllegalArgumentException("No symbol such as " + player.getSymbol());
        };
        return pos;
    }

    @Override
    public void viewPainting() {
        if (isByPainting() == true) {
            setGameState(GameState.PAINTINGVIEW);
        }
    }

    @Override
    public void clearBoard() {
        for (int row = 0; row < board.rows(); row++) {
            board.setRow(row, '-');
        }
    }

    @Override
    public GridDimension getDimension() {
        return this.board;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public String getRoomName() {
        String roomName = switch (roomNumber) {
            case 1 -> "Baroque";
            case 2 -> "Impressionism";
            case 3 -> "Expressionism";
            case 4 -> "Samuel Sjøen";
            default -> throw new IllegalArgumentException("No available name for " + roomNumber);
        };
        return roomName;
    }

    @Override
    public void setGameState(GameState state) {
        gameState = state;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.board;
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

    @Override
    public List<Painting> getPaintingsForRoom() {
        return Room.getPaintingsForRoom(roomNumber, board);
    }

    @Override
    public void gluePaintingToBoard(Painting painting) {
        for (GridCell<Character> gc : painting) {
            board.set(gc.pos(), painting.getNumber());
        }
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
    public String getPaintingPath() {
        for (Painting painting : getPaintingsForRoom()) {
            if (painting.getNumber() == currentPaintingNumber) {
                return painting.getPath();
            }
        }
        throw new IllegalArgumentException("No path found for painting");
    }

    @Override
    public String getPaintingInfo() {
        for (Painting painting : getPaintingsForRoom()) {
            if (painting.getNumber() == currentPaintingNumber) {
                return painting.getPaintinginfo();
            }
        }
        throw new IllegalArgumentException("No info found for painting");
    }

    /** Helping method to get the carpet color in the current room */
    private char getCarpetColor() {
        char color = switch (roomNumber) {
            case 1 -> 'M';
            case 2 -> 'Y';
            case 3 -> 'G';
            case 4 -> 'O';
            default -> throw new IllegalArgumentException("No available color for " + roomNumber);
        };
        return color;
    }
}
