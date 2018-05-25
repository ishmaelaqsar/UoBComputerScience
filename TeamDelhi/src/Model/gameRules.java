package Model;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Static object containing a collection of methods for parsing String inputs and applying game rules.
 * 
 * @author Team Delhi
 */
public class gameRules {

    //Establishes two input patterns
    public static final Pattern pValidInput = Pattern.compile("[a-j][1-9]|[a-j][1][0]");
    public static final Pattern pValidInput2 = Pattern.compile("horizontal|vertical");

    /**
     * Setup Player's board phase
     * 
     * @param player - The player that wishes to set up their board
     */
    public static void setUpBoard(Player player) {

        //Print empty board
        player.playerBoard.printBoard();

        //Create string variable for storing user input
        String userPlacement;
        String orientation;

        //Establish new scanner to attain user input
        Scanner scanner = new Scanner(System.in);

        //Iterate through ships in ship list
        for (int i = 0; i < player.playerBoard.ships.size(); ) {

            try {

                //Prompt user to place the current ship
                System.out.println("Place your " + player.playerBoard.ships.get(i).getName() + ":");

                //Scan for valid coordinates
                userPlacement = scanner.next(pValidInput);

                //Prompt user to declare orientation
                System.out.println("Orientation (horizontal or vertical):");


                //Scan for valid orientation
                orientation = scanner.next(pValidInput2);

                //If orientation is horizontal: set ship's boolean to match
                if (orientation.equals("horizontal")) {

                    player.playerBoard.ships.get(i).vertical = false;

                }

                //If orientation is vertical: set ship's boolean to match
                else if (orientation.equals("vertical")) {

                    player.playerBoard.ships.get(i).vertical = true;

                }

                //Take user's input and call placeShip on the playerBoard
                if (player.playerBoard.placeShip(player.playerBoard.ships.get(i), userPlacement) == true) {

                    //Print the playerBoard (should now include a new ship)
                    player.playerBoard.printBoard();

                    player.playerBoard.ships.get(i).placed = true;

                    i++;

                }
            }

            //Catch errors with unexpected input
            catch (InputMismatchException e) {

                System.out.println("Unexpected input");
                scanner = new Scanner(System.in);

            }
        }

        //When all ships are placed, informs player
        System.out.println("Ships Placed!\n");
        scanner.close();

    }


    /**
     * Method for establishing two players opponent's boards
     * 
     * @param player1 - One of two players to set up board
     * @param player2 - Second of two players to set up board
     */
    public static void setUpOpponentBoards(Player player1, Player player2) {

        player1.opponentsBoard = player2.playerBoard;
        player2.opponentsBoard = player1.playerBoard;

    }


    /**
     * Method for calling shots on a loop.
     * 
     * @param player - First player to take a shot
     * @param player2 - Second player to take a shot
     */
    public static void gameShot(Player player, Player player2) {

        //Create string variable for storing user input
        String userShot;

        //Establish new scanner to attain user input
        Scanner scannerShot = new Scanner(System.in);

        //While gameState is true
        while (player.playerTurn != false) {

            //Prompt for attack coords
            System.out.println("Enter your attack:");

            try {

                //Save next valid input as user's shot
                userShot = scannerShot.next(pValidInput);

                //If player's shot list contains that, declare repeated
                if (player.playerShots.contains(userShot)) {

                    System.out.println("Repeated!");
                    continue;

                } else {

                    //Call shot method on given coords
                    player.opponentsBoard.shot(player, userShot, 0);

                    player.playerShots.add(userShot);

                    player.playerTurn = false;

                }

                //Print shots so far
                System.out.println(player.playerShots);

                //Print board
                player.opponentsBoard.printBoard();

                checkWinLose(player);


            }

            //Catch unexpected inputs
            catch (InputMismatchException e) {

                System.out.println("Unexpected input");
                scannerShot = new Scanner(System.in);

            }
        }

        scannerShot.close();
        player2.playerTurn = true;

    }


    /**
     * Checks if the player's board or local opponent's board is empty
     * @param player - The player to be checked
     */
    public static void checkWinLose(Player player) {

        if (player.opponentsBoard.isBoardEmpty()) {

            player.gameState = false;
            player.winState = true;
            System.out.println("You win!");

        } else if (player.playerBoard.isBoardEmpty()) {

            player.gameState = false;
            player.loseState = true;
            System.out.println("You lose!");

        }

    }


    /**
     * Method to check if all ships on board are placed
     * @param player - Player to be checked
     * @return - Boolean indicating if all ships placed
     */
    public static boolean checkShipsPlaced(Player player) {

        for (ship ship : player.playerBoard.ships) {

            if (ship.placed == false) {

                return false;

            }

        }

        return true;

    }


    /**
     * Method to check if all ships on opponents board are placed
     * @param player - Player to be checked
     * @return - Boolean indicating if player's opponent's ships are placed
     */
    public static boolean checkOppShipsPlaced(Player player) {

        for (ship ship : player.opponentsBoard.ships) {

            if (ship.placed == false) {

                return false;

            }
        }

        return true;
    }

}
