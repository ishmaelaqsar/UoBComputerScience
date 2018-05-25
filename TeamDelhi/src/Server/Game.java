package Server;

import java.io.IOException;
import java.util.ArrayList;

import Model.Player;
import Protocol.SimpleProtocol;

/**
 * Game thread object. Constructs a game thread using two client thread inputs.
 *
 * @author Team Delhi
 */
public class Game implements Runnable {

	private ArrayList<String> inGameMessages = new ArrayList<>();
	private ClientThread client1;
	private ClientThread client2;
	private boolean shipsSwapped = false;
	private boolean gameStarted = false;
	private Player player1 = null;
	private Player player2 = null;
	private String[] player1LastAttack = { "0", "0" };
	private String[] player2LastAttack = { "0", "0" };
	private SimpleProtocol protocol = new SimpleProtocol();
	static int turnCount;

	/**
	 * @param client1
	 * @param client2
	 */
	public Game(ClientThread client1, ClientThread client2) {
		this.client1 = client1;
		this.client2 = client2;
		
		client1.clientNumber = 0;
		client2.clientNumber = 1;
	}
	
	@Override
	public void run() {
		while (true) {
			while (!gameStarted) {
				if (client1.player == null || client2.player == null) {
					try {
						Thread.sleep(1000);
						continue;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				else if (!shipsSwapped) {
					swapBoards();
				}

				else if (shipsSwapped == true && gameStarted == false) {
					client1.clientsTurn = true;
					gameStarted = true;
				}
			}
			
			if(client2.win == true){
				
				client1.Lose();
				
			}
			
				
			if (turnCount % 2 == 0){
				
				client1.gameTurnStart();
				
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				turnCount++;
				
			}
			
			
			if(client1.win == true){
				
				client2.Lose();
				
			}


			else if (turnCount % 2 == 1){
				
				client2.gameTurnStart();
				
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				turnCount++;
				
			}
			
		}
	}

	public void swapBoards() {
		player1 = client1.player;
		player2 = client2.player;

		player1.opponentsBoard = player1.playerBoard;
		player2.opponentsBoard = player2.playerBoard;

		player1.playerBoard = player2.playerBoard;
		player2.playerBoard = player1.opponentsBoard;

		client1.player.opponentsBoard = player1.playerBoard;
		client2.player.opponentsBoard = player2.playerBoard;

		try {
			client1.serverOut.writeObject(player1);
			client1.serverOut.flush();

			client2.serverOut.writeObject(player2);
			client2.serverOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		shipsSwapped = true;

		System.out.println("Ships swapped.");

	}

	public static boolean areSame(String[] array1, String[] array2) {
		boolean check = false;
		if (array1[0].equals(array2[0]) && array1[1].equals(array2[1])) {
			check = true;
		}
		return check;
	}
	
	public void attack() {
		if (!areSame(player1LastAttack, client1.lastShot)) {
			player1LastAttack = client1.lastShot;
			String string = protocol.createMessage("attack-received", player1LastAttack[0],
					player1LastAttack[1]);
			try {
				client2.serverOut.writeObject(string);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
		else if (!areSame(player2LastAttack, client2.lastShot)) {
			player2LastAttack = client2.lastShot;
			String string = protocol.createMessage("attack-received", player2LastAttack[0],
					player2LastAttack[1]);
			try {
				client1.serverOut.writeObject(string);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void finish(String winner) {
		if (winner.equals(client1.username)) {
			String string = protocol.createMessage("lose");
			try {
				client2.serverOut.writeObject(string);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			String string = protocol.createMessage("lose");
			try {
				client1.serverOut.writeObject(string);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
