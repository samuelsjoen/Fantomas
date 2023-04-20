package no.uib.inf101.sem2.fantomas.model.rooms;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.sem2.grid.GridDimension;

public class Room {

    GridDimension grid;

    public static List<Door> getDoorsForRoom(int roomNumber, GridDimension grid) {
        List<Door> doors = new ArrayList<>();
        Door door1 = Door.createNew("horizontal");
        Door door2 = Door.createNew("vertical");
        if (roomNumber == 1) {
            doors.add(door1.shiftedToWall(grid, "north"));
            doors.add(door2.shiftedToWall(grid, "east"));
            return doors;
        }
        if (roomNumber == 2) {
            doors.add(door1.shiftedToWall(grid, "south"));
            doors.add(door2.shiftedToWall(grid, "east"));
            return doors;        
        }
        if (roomNumber == 3) {
            doors.add(door1.shiftedToWall(grid, "south"));
            doors.add(door2.shiftedToWall(grid, "west"));
            return doors;        
        }
        if (roomNumber == 4) {
            doors.add(door1.shiftedToWall(grid, "north"));
            doors.add(door2.shiftedToWall(grid, "west"));
            return doors;        
        }
        else {
            throw new IllegalArgumentException("No available doors for " + roomNumber);
        }
    }
}
