package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class creates the player object.
 * Each player object contains the details of that player's current instance of the game.
 * Upon construction, it generates a board, an opponent's board and their respective 5 ships.
 * 
 *  @author Team Delhi
 */
public class Player implements Serializable {

	//ID for sending between client-server
    private static final long serialVersionUID = 6447401561349202633L;
    
    //String usernames
    String userName;
    String opponentName;
    
    //The player's board and the opponent's board
    public board playerBoard;
    public board opponentsBoard;
    
    //boolean indicating if it's this players turn or not
    public boolean playerTurn;
    
    //Booleans indicating whether the player is currently in a game
    public boolean gameState;
    
    //Booleans indicating the players win/lose state
    public boolean winState;
    public boolean loseState;
    
    //List of the hits/misses coordinates
    public int[] ships;
    public int[] hits;
    public int[] misses;
    public int[] opponentHits;
    public int[] opponentMisses;
    
    //The player's score
    int playerScore;
    //Create an arrayList to record location of ship tiles
    ArrayList<String> shipCoords = new ArrayList<String>();

    //Create an arrayList to store the player's shots taken
    ArrayList<String> playerShots = new ArrayList<String>();

    //Create an arrayList to store the opponenet's shots taken
    ArrayList<String> oppShots = new ArrayList<String>();

    /**
     * Constructs the player object
     * 
     * @param username
     */
    public Player(String username) {
        this.userName = username;

        //Establishes an empty board for the player
        this.playerBoard = new board();
        this.opponentsBoard = new board();

        //Establishes win/lose state as false
        this.winState = false;
        this.loseState = false;

        //Establishes new arrays of size 100 for indexes
        this.ships = new int[100];
        this.hits = new int[100];
        this.misses = new int[100];
        this.opponentHits = new int[100];
        this.opponentMisses = new int[100];
        
        //this.playerTurn = false;
        //this.playerScore = 0;

    }

    
    /**
     * Username getter
     * @return - String username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Username setter
     * @return - void
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Player's board getter
     * @return - Player's board object
     */
    public board getPlayerBoard() {
        return playerBoard;
    }

    /**
     * Username setter
     * @return - void
     */
    public void setPlayerBoard(board playerBoard) {
        this.playerBoard = playerBoard;
    }

    
    /**
     * Player's score getter
     * @return - Int of player's score
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Username setter
     * @return - void
     */
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    /**
     * Hit index getter
     * @return - Int[] of hits
     */
    public int[] getHits() {
        return hits;
    }

    /**
     * Hits index setter
     * @return - void
     */
    public void setHits(int[] hits) {
        this.hits = hits;
    }

    /**
     * Miss index getter
     * @return - Int[] of misses
     */
    public int[] getMisses() {
        return misses;
    }

    /**
     * Misses index setter
     * @return - void
     */
    public void setMisses(int[] misses) {
        this.misses = misses;
    }

    /**
     * Ship index getter
     * @return - Int[] of ships
     */
    public int[] getShips() {
        return ships;
    }

    /**
     * Ship index setter
     * @return - void
     */
    public void setShips(int[] ships) {
        this.ships = ships;
    }
    
    /**
     * Username getter
     * @return - String username
     */
    public String getOpponentName() {
        return opponentName;
    }

    /**
     * Username setter
     * @return - void
     */
    public void setOpponentName(String userName) {
        this.opponentName = userName;
    }

}
