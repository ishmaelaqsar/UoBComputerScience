package Server;

import Protocol.SimpleProtocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server class that sets-up the connection with clients and then delegates
 * tasks to a handler
 *
 * @author Team Delhi
 */
public class Server {
	static Map<String, ClientThread> connectedClients = new HashMap<>();
	static Map<Integer, Game> gameList = new HashMap<>();
	static ArrayList<String> lobbyList = new ArrayList<>();
	private static int matchCount = 0;
	private static ExecutorService gamePool = Executors.newCachedThreadPool();
	private static SimpleProtocol protocol = new SimpleProtocol();

	
	public static void main(String[] args) {
		
		//Establishes sockets
		ServerSocket serverSocket;
		Socket clientSocket;

		
		try {
			//If no args given, use defaul inputs
			if (args.length < 1) {
				serverSocket = new ServerSocket(8080, 0, InetAddress.getLocalHost());
				System.out.println("\r\nRunning Server: " + "Host= " + InetAddress.getLocalHost() + " Port= " + 8080);
			} 
			
			//Otherwise some args are given, use args to find port and address
			else {
				serverSocket = new ServerSocket(Integer.parseInt(args[1]), 0, InetAddress.getByName(args[0]));
				System.out.println("\r\nRunning Server: " + "Host= " + InetAddress.getLocalHost() + " Port= " + 8080);
			}
			
			
			while (true) {

				try {

					//Create pool of threads
					ExecutorService threadPool = Executors.newCachedThreadPool();

					//When client connects to a socket
					clientSocket = serverSocket.accept();
					//Announce connection and details
					System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " connected.");

					//Allocate a new client thread for the new client
					threadPool.execute(new ClientThread(clientSocket));
				} 
				
				catch (IOException e) {
					System.out.println("Cannot connect on port: " + 8080);
				}
			}

		} 
		
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Arguments not provided. Please provide hostname and port.");
			e.printStackTrace();
		} 
		
		catch (IOException e) {
			System.out.println("I/O error occurred when opening the socket.");
			e.printStackTrace();
		}

	}

	/**
	 * Send an up-to-date version of the current lobby list to all players in a
	 * lobby
	 */
	public static void sendUpdatedList() {

		//For every connected thread in the server's list
		for (ClientThread connectedThreads : connectedClients.values()) {

			//Call updateLobby method
			connectedThreads.updateLobby();

		}
	}

	/**
	 * Creates a game thread containing the two players given
	 * 
	 * @param player1 - First player to join game
	 * @param player2 - Second player to join game
	 */
	public static void startGame(String player1, String player2) {

		//Increment match count
		matchCount++;

		//Get two client threads
		ClientThread c1 = connectedClients.get(player1);
		ClientThread c2 = connectedClients.get(player2);

		//Remove player one from lobby list and set booleans
		lobbyList.remove(c1.username);
		connectedClients.remove(c1.username);
		c1.signedIn = false;
		c1.inGame = true;

		//Remove player two from lobby list and set booleans
		lobbyList.remove(c2.username);
		connectedClients.remove(c2.username);
		c2.signedIn = false;
		c2.inGame = true;

		//Send the updated list to other clients
		sendUpdatedList();

		//Print message to both clients acknowledging game has started
		try {
			c1.serverOut.writeObject(protocol.createMessage("send-invite", "true", "Match Started."));
			c1.serverOut.flush();
			c2.serverOut.writeObject(protocol.createMessage("send-invite", "true", "Match Started."));
			c2.serverOut.flush();
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}

		//Create new game
		Game game = new Game(c1, c2);
		
		//Start game
		gamePool.execute(game);
		
		//Add game to server's list
		gameList.put(matchCount, game);
		
		c1.matchid = matchCount;
		c2.matchid = matchCount;
	}

	public static void attack(int matchid) {
		gameList.get(matchid).attack();
	}

	public static void finish(int matchid, String username) {
		gameList.get(matchid).finish(username);
	}
	
}
