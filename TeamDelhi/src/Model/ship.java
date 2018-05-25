package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class creates ship objects.
 * Ships contain the details of that particular object's size, health, name etc.
 * and are generated within the board constructor.
 * 
 *  @author Team Delhi
 */
public class ship implements Serializable {

	//ID for sending between client-server
    private static final long serialVersionUID = 7822417386117674234L;
    
    //Establishes if the ship has been sunk or placed
    public boolean sunk;
    public boolean placed;
    
    //Determines ship's orientation
    public boolean vertical;
    
    //String for relaying the most recent hit/miss event to the GUI
    public String hitMissEvent;
    
    //Establishes a string for the ship's name
    String name;
    //Establishes a permanent int for the ship's size
    int size;
    //Establishes an int that shows how much health the ship has left
    int health;
    
    //List of cells the ship covers
    ArrayList<String> cells = new ArrayList<String>();


    /**
     * Constructor for ship objects
     *
     * @param shipName - The model of the ship to be constructed
     */
    public ship(String shipName) {

        //Set name of ship
        this.name = shipName;
        this.sunk = false;

        //Set stats for patrol boat
        if (shipName.equals("patrol boat")) {

            this.size = 2;
            this.health = 2;

        }

        //Set stats for destroyer and submarine
        else if (shipName.equals("destroyer") || shipName.equals("submarine")) {

            this.size = 3;
            this.health = 3;

        }

        //Set stats for battleship
        else if (shipName.equals("battleship")) {

            this.size = 4;
            this.health = 4;

        }

        //Set stats for aircraft carrier
        else if (shipName.equals("aircraft carrier")) {

            this.size = 5;
            this.health = 5;

        }
    }

    
    /**
     * Getter for the ship's name
     *
     * @return - String of given ship's name
     */
    public String getName() {

        return this.name;

    }

    /**
     * Getter for ship's size
     *
     * @return - Integer of given ship's size
     */
    public int getSize() {

        return this.size;

    }

    /**
     * Getter for ship's current health
     *
     * @return - Integer of given ship's current health
     */
    public int getHealth() {

        return this.health;

    }

    /**
     * Getter for ship's current sunk status
     *
     * @return - Boolean of whether ship is sunk or not
     */
    public boolean getSunk() {

        return this.sunk;

    }

    /**
     * Method for manifesting the effect of a hit on a ship
     */
    public void hit() {

        //If the ship has no health, do nothing
        if (this.health == 0) {

            return;

        }

        //Otherwise
        else {

            //If the ship has one health
            if (this.health == 1) {

                //Set health to 0
                this.health = 0;

                //State ship is sunk
                this.sunk = true;

                //Declare a miss & store in hit/miss event String to relay to GUI
                hitMissEvent = "";
                hitMissEvent = this.name + " has been sunk!";


            }

            //If more than one health:
            else {

                //Reduce health by 1
                this.health = (this.health - 1);

                //Declare a hit & store in hit/miss event String to relay to GUI
                hitMissEvent = "";
                hitMissEvent = this.name + " has been hit!";

            }

        }

    }

    
    /**
     * Add cells the ship covers to its list of occupied cells
     *
     * @param coordinates - The coordinate the ship is placed on
     */
    public void addShipCells(String coordinates) {

        //Add the starting cell
        this.cells.add(coordinates);

        //Convert coordinate input into a charArray
        char[] coords = coordinates.toCharArray();

        //If the ship is vertical:
        if (this.vertical) {

            //Iterate for size of ship
            for (int i = 1; i < this.size; i++) {

                //Increase char value at 2nd position in coords by 1
                coords[1] = (char) (coords[1] + 1);

                //Turn into a string
                String s = String.valueOf(coords);

                //Add that string to the list of cells
                this.cells.add(s);

            }
        }

        //Otherwise (aka horizontal)
        else {

            //Iterate for size of ship
            for (int i = 1; i < this.size; i++) {

                //Increase char value at 1st position in coords by 1
                coords[0] = (char) (coords[0] + 1);

                //Turn into a string
                String s = String.valueOf(coords);

                //Add that string to the list of cells
                this.cells.add(s);

            }
        }
    }

    /**
     * Method to check if a shot has already been made
     *
     * @param shotCoords - Coordinates to check
     * @return Boolean indicating if a ship has been hit
     */
    public boolean checkGuess(String shotCoords) {

        //Iterate over stored cells in list
        for (String cell : this.cells) {

            //If any cell equals shotCoords, return true
            if (cell.equals(shotCoords)) {

                return true;

            }

        }

        //Otherwise, return false
        return false;

    }
}
