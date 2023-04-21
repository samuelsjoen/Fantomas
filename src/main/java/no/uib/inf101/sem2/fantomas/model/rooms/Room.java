package no.uib.inf101.sem2.fantomas.model.rooms;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.sem2.fantomas.grid.CellPosition;
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

    public static List<Painting> getPaintingsForRoom(int roomNumber, GridDimension grid) {
        List<Painting> paintings = new ArrayList<>();
        if (roomNumber == 1) {
            Painting narcissus = Painting.newPainting(9, '0').rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(narcissus);
            Painting davidWithTheHeadOfGoliath = Painting.newPainting(9, '1').rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, 15);
            paintings.add(davidWithTheHeadOfGoliath);
            Painting theArtOfPainting = Painting.newPainting(9, '2').shiftedToWall(grid, "east").shiftedBy(-15, 0);
            paintings.add(theArtOfPainting);
            Painting amorVincitOmnia = Painting.newPainting(9, '3').shiftedToWall(grid, "east").shiftedBy(15, 0);
            paintings.add(amorVincitOmnia);
            Painting rokebyVenus = Painting.newPainting(11, '4').shiftedToWall(grid, "west");
            paintings.add(rokebyVenus);
            return paintings;
        }
        if (roomNumber == 2) {  
            Painting narcissus = Painting.newPainting(9, '1').rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(narcissus); 
            return paintings;
        }
        if (roomNumber == 3) {   
            Painting narcissus = Painting.newPainting(9, '2').rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(narcissus);
            return paintings;
        }
        if (roomNumber == 4) {  
            Painting narcissus = Painting.newPainting(9, '3').rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(narcissus);
            return paintings;
        }
        else {
            throw new IllegalArgumentException("No available doors for " + roomNumber);
        }
    }
}
