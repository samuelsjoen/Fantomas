package no.uib.inf101.sem2.fantomas.model.rooms;

import java.util.ArrayList;
import java.util.List;
import no.uib.inf101.sem2.fantomas.grid.GridDimension;

public class Room {

    GridDimension grid;

    /**Returns a list of the doors in the room indicated by the roomnumber and board which the room is on*/
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

    /**Returns a list of the paintings in the room indicated by the roomnumber and board which the room is on*/ 
    public static List<Painting> getPaintingsForRoom(int roomNumber, GridDimension grid) {
        List<Painting> paintings = new ArrayList<>();
        if (roomNumber == 1) {
            Painting narcissus = Painting.newPainting(9, '0', "/paintings/narcissus.jpg", "Caravaggio: Narcissus (1599)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(narcissus);
            Painting davidWithTheHeadOfGoliath = Painting.newPainting(9, '1', "/paintings/davidWithTheHeadOfGoliath.jpg", "Caravaggio: David with the Head of Goliath (1607)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, 15);
            paintings.add(davidWithTheHeadOfGoliath);
            Painting theArtOfPainting = Painting.newPainting(9, '2', "/paintings/theArtOfPainting.jpg", "Vermeer, The Art of Painting (1668)").shiftedToWall(grid, "east").shiftedBy(-15, 0);
            paintings.add(theArtOfPainting);
            Painting amorVincitOmnia = Painting.newPainting(9, '3', "/paintings/amorVincitOmnia.jpg", "Caravaggio, Amor Vincit Omnia (1602)").shiftedToWall(grid, "east").shiftedBy(15, 0);
            paintings.add(amorVincitOmnia);
            Painting theNightWatch = Painting.newPainting(7, '4', "/paintings/theNightWatch.jpg", "Rembrandt, The Night Watch (1642)").rotatedPainting().shiftedToWall(grid, "south").shiftedBy(0, 15);
            paintings.add(theNightWatch);
            Painting etInArcadiaEgo = Painting.newPainting(11, '5', "/paintings/etInArcadiaEgo.jpg", "Poussin, Et in Arcadia ego (1640)").rotatedPainting().shiftedToWall(grid, "south");
            paintings.add(etInArcadiaEgo);
            Painting christCrucified = Painting.newPainting(11, '6', "/paintings/christCrucified.jpg", "Velázquez, Christ Crucified (1632)").rotatedPainting().shiftedToWall(grid, "south").shiftedBy(0, -15);
            paintings.add(christCrucified);
            Painting womanHoldingABalance = Painting.newPainting(7, '7', "/paintings/womanHoldingABalance.jpg", "Vermeer, Woman Holding a Balance (1663)").shiftedToWall(grid, "west").shiftedBy(15, 0);
            paintings.add(womanHoldingABalance);
            Painting rokebyVenus = Painting.newPainting(11, '8', "/paintings/rokebyVenus.jpg", "Velázquez, Rokeby Venus (1647)").shiftedToWall(grid, "west");
            paintings.add(rokebyVenus);
            Painting theEntombmentOfChrist = Painting.newPainting(9, '9', "/paintings/theEntombmentOfChrist.jpg", "Caravaggio, The Entombment of Christ (1603)").shiftedToWall(grid, "west").shiftedBy(-15, 0);
            paintings.add(theEntombmentOfChrist);
            return paintings;
        }
        if (roomNumber == 2) {  
            Painting womanWithAParasol = Painting.newPainting(9, '0', "/paintings/womanWithAParsol.jpg", "Monet: Woman with a Parasol (1875)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(womanWithAParasol);
            Painting applePickingAtEragnySurEpte = Painting.newPainting(11, '1', "/paintings/applePickingAtEragnySurEpte.jpg", "Pissaro: Apple Picking at Eragny-sur-Epte (1888)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -1);
            paintings.add(applePickingAtEragnySurEpte);
            Painting theBoulevardMontmartreAtNight = Painting.newPainting(9, '2', "/paintings/theBoulevardMontmartreAtNight.jpg", "Pissaro: The Boulevard Montmartre at Night (1897)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, 15);
            paintings.add(theBoulevardMontmartreAtNight);
            Painting bouilloireEtFruits = Painting.newPainting(9, '3', "/paintings/bouilloireEtFruits.jpg", "Cezanne: Bouilloire et fruits (1890)").shiftedToWall(grid, "east").shiftedBy(-15, 0);
            paintings.add(bouilloireEtFruits);
            Painting theBeachSunset = Painting.newPainting(9, '4', "/paintings/theBeachSunset.jpg", "Courbet: The Beach, Sunset (1867)").shiftedToWall(grid, "east").shiftedBy(15, 0);
            paintings.add(theBeachSunset);
            Painting chateauNoir = Painting.newPainting(9, '5', "/paintings/chateauNoir.jpg", "Cezanne: Chateau Noir (1904)").rotatedPainting().shiftedToWall(grid, "south").shiftedBy(0, 15);
            paintings.add(chateauNoir);
            Painting theMannerportNearEtretat = Painting.newPainting(9, '6', "/paintings/theMannerportNearEtretat.jpg", "Monet: The Mannerport near Etretat (1886)").rotatedPainting().shiftedToWall(grid, "south").shiftedBy(0, -15);
            paintings.add(theMannerportNearEtretat);
            Painting thePoplarsTheCouture = Painting.newPainting(7, '7', "/paintings/thePoplarsTheCouture.jpg", "Martin: The Poplars - The Couture (date unknown)").shiftedToWall(grid, "west").shiftedBy(15, 0);
            paintings.add(thePoplarsTheCouture);
            Painting flockOfSheep = Painting.newPainting(11, '8', "/paintings/flockOfSheep.jpg", "Pissaro: Flock of Sheep (1888)").shiftedToWall(grid, "west");
            paintings.add(flockOfSheep);
            Painting theGleaners = Painting.newPainting(11, '9', "/paintings/theGleaners.jpg", "Pissaro: The Gleaners (1889)").shiftedToWall(grid, "west").shiftedBy(-15, 0);
            paintings.add(theGleaners);
            return paintings;
        }
        if (roomNumber == 3) {   
            Painting deadMother = Painting.newPainting(9, '0', "/paintings/deadMother.jpg", "Schiele: Dead Mother (1910)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(deadMother);
            return paintings;
        }
        if (roomNumber == 4) {  
            Painting narcissus = Painting.newPainting(9, '0', "/paintings/narcissus.jpg", "Caravaggio: Narcissus (1599)").rotatedPainting().shiftedToWall(grid, "north").shiftedBy(0, -15);
            paintings.add(narcissus);
            return paintings;
        }
        else {
            throw new IllegalArgumentException("No available paintings for " + roomNumber);
        }
    }
}
