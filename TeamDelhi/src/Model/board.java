package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Creates the board object and generates five ships inside it.
 * 
 * @author Team Delhi
 */
public class board implements Serializable {
	
	//ID for sending between client-server
    private static final long serialVersionUID = 599046471333725282L;

    //Sets some valid inputs
    public static final Pattern pValidInput = Pattern.compile("[a-j][1-9]|[a-j][1][0]|q");

    //Sets no. of columns and rows of board
    final int rows = 10;
    final int columns = 10;
    
    //Establishes the board as a two-dimensional int array
    public int[][] board;
    
    //Hit/Miss arrays to correspond with GUI button values
    public int[] shipIndex;
    public int[] hitIndex;
    public int[] missIndex;
    
    //List of ship objects present on this board
    public ArrayList<ship> ships;
    
    //String storing the result of the most recent shot
    public String hitMissEvent;

    /**
     * Constructor for the board object
     */
    public board() {

        //Sets the board as a 2D-integer array with the amounts of rows & columns specified
        this.board = new int[rows][columns];

        //Initialise Hit/Miss arrays to correspond with GUI
        this.shipIndex = new int[100];
        this.hitIndex = new int[100];
        this.missIndex = new int[100];

        //Initialises the ArrayList of ships - adds each ship to the list
        this.ships = new ArrayList<ship>();
        ships.add(new ship("patrol boat"));
        ships.add(new ship("destroyer"));
        ships.add(new ship("submarine"));
        ships.add(new ship("battleship"));
        ships.add(new ship("aircraft carrier"));

    }


    /**
     * Places a ship using the given coordinates
     * @param ship - Ship to be placed
     * @param coordinates - coordinates of the ship
     * @return - boolean indicating if palcement was successful
     */
    public boolean placeShip(ship ship, String coordinates) {

        //Establishes empty integers for parsing input into column & row values
        int column;
        int row;

        //Converts coordinate input into a charArray
        char[] coords = coordinates.toCharArray();

        //Switch statement on first char of coordinates (should be a letter)
        switch (coords[0]) {

            //If the coordinate given begins with a, must be in column 0
            case 'a':
                column = 0;
                break;

            case 'b':
                column = 1;
                break;

            case 'c':
                column = 2;
                break;

            case 'd':
                column = 3;
                break;

            case 'e':
                column = 4;
                break;

            case 'f':
                column = 5;
                break;

            case 'g':
                column = 6;
                break;

            case 'h':
                column = 7;
                break;

            case 'i':
                column = 8;
                break;

            case 'j':
                column = 9;
                break;

            default:
                return false;

        }


        //If length of coordinates was 3, the row must be 10 (i.e. b10)
        if (coords.length == 3) {

            row = 9;

        }

        //Otherwise, only 2 digits in the coordinate
        else {

            //Minus the 2nd char by 49 to get actual int value
            row = coords[1] - 49;

        }


        //If given inputs are not valid, print message
        if (!(isValidLocation(column, row, ship.size, ship.vertical))) {

            System.out.println("Error Message 1");
            return false;

        }

        //If reached this stage, inputs must have been valid
        else {


            //If the orientation was set as vertical
            if (ship.vertical == true) {

                //Iterate for size of ship (i.e. 3 places)
                for (int i = 0; i < ship.size; i++) {

                    //Increase value of row by 1 each iteration
                    //Set value at that location to 1
                    this.board[row + i][column] = 1;

                }
            }

            //If the orientation was set as horizontal
            else if (ship.vertical == false) {

                //Iterate for size of ship (i.e. 3 places)
                for (int i = 0; i < ship.size; i++) {

                    //Increase value of column by 1 each iteration
                    //Set value at that location to 1
                    this.board[row][column + i] = 1;

                }
            }

            //Adds the cells the ship covers to its internal list
            ship.addShipCells(coordinates);

            ship.placed = true;

            //Ship placement was successful - return true
            return true;
        }

    }

    /**
     * Method to check to see if a location is a valid input
     * @param column - Column of placement
     * @param row - Row of placement
     * @param size - Size of ship to be placed
     * @param vertical - Orientation of ship
     * @return - boolean indicating if location is valid
     */
    public boolean isValidLocation(int column, int row, int size, boolean vertical) {

        //If the size of the ship if zero - return false
        if (size == 0) {

            return false;

        }

        try {

            //If the orientation is vertical
            if (vertical == true) {

                //Iterate over size of ship no. of places
                for (int i = 0; i < size; i++) {

                    //If any value at those locations is not equal to zero (i.e. already a ship there or otherwise non-zero)
                    if (!(this.board[row + i][column] == 0)) {

                        //Return false
                        return false;

                    }

                }

                //If passed, return true
                return true;
            }

            //If the orientation is horizontal
            else if (vertical == false) {

                //Iterate over size of ship no. of places
                for (int i = 0; i < size; i++) {

                    //If any value at those locations is not equal to zero (i.e. already a ship there or otherwise non-zero)
                    if (!(this.board[row][column + i] == 0)) {

                        //Return false
                        return false;

                    }
                }

                //If passed, return true
                return true;
            }

            //For any other input, return false
            else {

                return false;

            }
        }

        //If OutOfBounds exception triggers, return false
        catch (ArrayIndexOutOfBoundsException error) {

            return false;

        }

    }

    /**
     * Method for declaring shots
     * @param player - Player being shot at
     * @param shotCoords - Coordinates of shot
     * @param arrayIndex - Index value of corresponding button in GUI
     */
    public void shot(Player player, String shotCoords, int arrayIndex) {

        //Add the shot to the player's list of shot coordinates
        //		battleship.playerShots.add(shotCoords);

        //Establishes empty integers for column & row values
        int column;
        int row;

        //Converts coordinate input into a charArray
        char[] coords = shotCoords.toCharArray();

        //Switch statement on first char of coordinates (should be a letter)
        switch (coords[0]) {

            //If the coordinate given begins with a, must be in column 0
            case 'a':
                column = 0;
                break;

            case 'b':
                column = 1;
                break;

            case 'c':
                column = 2;
                break;

            case 'd':
                column = 3;
                break;

            case 'e':
                column = 4;
                break;

            case 'f':
                column = 5;
                break;

            case 'g':
                column = 6;
                break;

            case 'h':
                column = 7;
                break;

            case 'i':
                column = 8;
                break;

            case 'j':
                column = 9;
                break;

            default:
                return;

        }

        //If length of coordinates was 3, the row must be 10 (i.e. b10)
        if (coords.length == 3) {

            row = 9;

        }

        //Otherwise, only 2 digits in the coordinate
        else {

            //Minus the 2nd char by 49 to get actual int value
            row = coords[1] - 49;

        }


        //If the value at that location is 1
        if (this.board[row][column] == 1) {

            //Declare a hit
            System.out.println("Hit!");

            //Reduce value at location by one
            this.board[row][column] = this.board[row][column] - 1;

            player.hits[arrayIndex] = 1;

        }

        //Otherwise, declare miss
        else {

            hitMissEvent = "";
            System.out.println("Miss!");
            hitMissEvent = "Miss!";
            player.misses[arrayIndex] = 1;

        }


        //Iterate over ships in board's list
        for (ship ship : this.ships) {

            //If any ships cover the coordinates that were shot at: call hit on it
            if (ship.checkGuess(shotCoords)) {

                hitMissEvent = "";
                ship.hit();
                hitMissEvent = ship.hitMissEvent;

            }
        }
    }

    //Determines if the board is empty (aka game is over)
    //Alternatively, could iterate through array of ships and determine if all sunk
    public boolean isBoardEmpty() {

        //Iterate through all values in board
        for (int i = 0; i < this.board.length; i++) {

            for (int j = 0; j < this.board[i].length; j++) {

                //If any are non-zero: return false
                if (this.board[i][j] == 1) {

                    return false;

                }
            }
        }

        //Else return true
        return true;

    }

    /**
     * Method for interpreting a string of coordinates into its corresponding index
     * @param coordinates - Coordinates to be interpreted
     * @return - Integer value of index
     */
    public int coordToInt(String coordinates) {

        int index = 0;

        char[] coords = coordinates.toCharArray();

        //Switch statement on first char of coordinates (should be a letter)
        switch (coords[0]) {

            //If the coordinate given begins with a, must be in column 0
            case 'a':
                index += 0;
                break;

            case 'b':
                index += 10;
                break;

            case 'c':
                index += 20;
                break;

            case 'd':
                index += 30;
                break;

            case 'e':
                index += 40;
                break;

            case 'f':
                index += 50;
                break;

            case 'g':
                index += 60;
                break;

            case 'h':
                index += 70;
                break;

            case 'i':
                index += 80;
                break;

            case 'j':
                index += 90;
                break;

            default:
                break;

        }

        if (coords.length == 3) {

            index += 9;

        } else {

            //Switch statement on first char of coordinates (should be a letter)
            switch (coords[1]) {

                case '1':
                    index += 0;
                    break;

                case '2':
                    index += 1;
                    break;

                case '3':
                    index += 2;
                    break;

                case '4':
                    index += 3;
                    break;

                case '5':
                    index += 4;
                    break;

                case '6':
                    index += 5;
                    break;

                case '7':
                    index += 6;
                    break;

                case '8':
                    index += 7;
                    break;

                case '9':
                    index += 8;
                    break;

                default:
                    break;

            }

        }

        return index;

    }

    
    /**
     * Method for printing out the board
     */
    public void printBoard() {

        //Establish upper-board markings
        String outerLine = "     a   b   c   d   e   f   g   h   i   j\n";

        //Establish inner-board lines
        String boardLine = "   +---+---+---+---+---+---+---+---+---+---+\n";

        //Initialise inner-board
        String innerBoard = "";

        //Iterate through board columns/rows
        for (int i = 0; i < board.length; i++) {

            //Add inner board lines at start of new line
            innerBoard += boardLine;

            //Print board row number
            if (i == 9) {

                //Row 9 is special case as row number '10' is two digits wide - spacing is reduced to compensate
                innerBoard += (1 + i) + " |";
            } else {

                //Every other row number is only 1 digit - spacing does not need to be adjusted
                innerBoard += (1 + i) + "  |";

            }


            //Iterate through every value in current row
            for (int j = 0; j < board[i].length; j++) {

                //Add spacing, value, and marking to innerBoard string
                innerBoard += " " + board[i][j] + " |";

            }

            //Move to next line
            innerBoard += "\n";
        }

        //Add boardLine at end of pieces array
        innerBoard += boardLine;

        //Create full board object of innerBoard surrounded by outer markings
        String board = outerLine + innerBoard;

        //Prints out the board
        System.out.println(board);

    }

}
