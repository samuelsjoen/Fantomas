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

    public static List<Painting> getPaintingsForRoom(int roomNumber, GridDimension grid) {
        List<Painting> paintings = new ArrayList<>();
        if (roomNumber == 1) {
            Painting narcissus = Painting.newPainting(9, '0', "/narcissus.jpg", "Caravaggio: Narcissus (1599)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(narcissus);
            Painting davidWithTheHeadOfGoliath = Painting.newPainting(9, '1', "/davidWithTheHeadOfGoliath.jpg", "Caravaggio: David with the Head of Goliath (1607)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, 15);
            paintings.add(davidWithTheHeadOfGoliath);
            Painting theArtOfPainting = Painting.newPainting(9, '2', "/theArtOfPainting.jpg", "Vermeer, The Art of Painting (1668)").shiftedToWall(grid, "east").shiftedBy(-15, 0);
            paintings.add(theArtOfPainting);
            Painting amorVincitOmnia = Painting.newPainting(9, '3', "/amorVincitOmnia.jpg", "Caravaggio, Amor Vincit Omnia (1602)").shiftedToWall(grid, "east").shiftedBy(15, 0);
            paintings.add(amorVincitOmnia);
            Painting theNightWatch = Painting.newPainting(7, '4', "/theNightWatch.jpg", "Rembrandt, The Night Watch (1642)").rotatedPainting().shiftedToWall(grid, "south").shiftedBy(0, 15);
            paintings.add(theNightWatch);
            Painting etInArcadiaEgo = Painting.newPainting(11, '5', "/etInArcadiaEgo.jpg", "Poussin, Et in Arcadia ego (1640)").rotatedPainting().shiftedToWall(grid, "south");
            paintings.add(etInArcadiaEgo);
            Painting christCrucified = Painting.newPainting(11, '6', "/christCrucified.jpg", "Velázquez, Christ Crucified (1632)").rotatedPainting().shiftedToWall(grid, "south").shiftedBy(0, -15);
            paintings.add(christCrucified);
            Painting womanHoldingABalance = Painting.newPainting(7, '7', "/womanHoldingABalance.jpg", "Vermeer, Woman Holding a Balance (1663)").shiftedToWall(grid, "west").shiftedBy(15, 0);
            paintings.add(womanHoldingABalance);
            Painting rokebyVenus = Painting.newPainting(11, '8', "/rokebyVenus.jpg", "Velázquez, Rokeby Venus (1647)").shiftedToWall(grid, "west");
            paintings.add(rokebyVenus);
            Painting theEntombmentOfChrist = Painting.newPainting(9, '9', "/theEntombmentOfChrist.jpg", "Caravaggio, The Entombment of Christ (1603)").shiftedToWall(grid, "west").shiftedBy(-15, 0);
            paintings.add(theEntombmentOfChrist);
            return paintings;
        }
        if (roomNumber == 2) {  
            Painting narcissus = Painting.newPainting(9, '0', "/narcissus.jpg", "Caravaggio: Narcissus (1599)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(narcissus);
            return paintings;
        }
        if (roomNumber == 3) {   
            Painting narcissus = Painting.newPainting(9, '0', "/narcissus.jpg", "Caravaggio: Narcissus (1599)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(narcissus);
            return paintings;
        }
        if (roomNumber == 4) {  
            Painting narcissus = Painting.newPainting(9, '0', "/narcissus.jpg", "Caravaggio: Narcissus (1599)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(narcissus);
            return paintings;
        }
        else {
            throw new IllegalArgumentException("No available paintings for " + roomNumber);
        }
    }
}
