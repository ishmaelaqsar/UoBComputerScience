package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Player;
import Protocol.SimpleProtocol;

/**
 * Client Handler class to manage interactions between a client and the server
 *
 * @author Team Delhi
 */
public class ClientThread implements Runnable {
	boolean signedIn = false;
	boolean inGame = false;
	boolean shipsSet = false;
	boolean clientsTurn = false;
	int localShipHits = 0;
	Object received;
	ObjectInputStream serverIn;
	ObjectOutputStream serverOut;
	Player player = null;
	Socket clientSocket;
	private SimpleProtocol protocol;
	String username;
	private Database user;
	String[] lastShot = {"0", "0"};
	int matchid = 0;
	int clientNumber;
	public boolean win = false;
	private String password;

	/**
	 * @param socket
	 */
	ClientThread(Socket socket) {
		clientSocket = socket;
		this.protocol = new SimpleProtocol();

		try {

			this.serverIn = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			this.serverOut = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));

			serverOut.writeObject(protocol.createMessage("send-message", "Welcome to the Server."));
			serverOut.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (true) {

			received = null;
			try {

				received = serverIn.readObject();
				if (received == null) {
					Thread.sleep(1000);
					continue;
				}

			}

			catch (SocketException | EOFException e) {
				JOptionPane.showMessageDialog(new JFrame(), "Opponent has disconnected. Shutting down.", "Error message",
	                    JOptionPane.ERROR_MESSAGE);
				(new Thread() {
					@Override
					public void run() {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					}
				}).start();
				System.exit(-1);
				
			} 
			
			
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} 
			
			catch (InterruptedException e) {
				e.printStackTrace();
			}

			String[] inputMessage = null;
			// If object received was a string
			if (received instanceof String) {
				// Decode string
				inputMessage = protocol.decodeMessage((String) received);

				System.out.println(Arrays.toString(inputMessage));

				// Switch case depending on request
				switch (inputMessage[0]) {

				case "sign-up":
					signup(inputMessage[1], inputMessage[2]);
					break;

				case "sign-in":
					signin(inputMessage[1], inputMessage[2]);
					break;

				case "update-lobby":
					updateLobby();
					break;

				case "sign-out":
					signOut(inputMessage[1]);
					break;

				case "send-invite":
					sendInvite(inputMessage[1], inputMessage[2]);
					// received = null;
					break;

				case "ships-placed":
					setShips();
					break;

//				case "attack-sent":
//					attackSent(inputMessage[1], inputMessage[2]);
//					break;
//
//				case "attack-received":
//					attackReceived(inputMessage[1], inputMessage[2]);
//					break;
				
				case "win":
					win();
					break;
				}

			} else if (received instanceof Player) {
				player = (Player) received;
				System.out.println("player set");
			}

		}
	}

	private void win() {
		System.out.println(username + " is the winner");
		win = true;
		Server.finish(matchid, username);
		user = new Database(username, password);
		user.updateWinsAndLosses(username);
	}

//	private void attackSent(String coords, String index) {
//		System.out.println("last shot set.");
//		lastShot[0] = coords;
//		lastShot[1] = index;
//		Server.attack(matchid);
//	}

//	private void attackReceived(String coords, String index) {
//
//		lastShot[0] = coords;
//		lastShot[1] = index;
//
//	}

	private void setShips() {
		try {
			serverOut.writeObject(protocol.createMessage("ships-placed", "true"));
			serverOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Lose() {
		try {
			serverOut.writeObject(protocol.createMessage("lose"));
			serverOut.flush();
			user = new Database(username, password);
			user.updateLosses(username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param opponent
	 * @param player
	 */
	private void sendInvite(String opponent, String player) {
		Server.startGame(player, opponent);
	}

	/**
	 * @param username
	 * @param password
	 */
	private void signup(String username, String password) {
		this.password = password;
		user = new Database(username, password);

		if (user.checkUsername()) {
			try {
				serverOut.writeObject(
						protocol.createMessage("sign-up", "false", "Username already in use, please choose another."));
				serverOut.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			user.register();

			try {
				serverOut.writeObject(
						protocol.createMessage("sign-up", "true", "Registration for " + username + " successful."));
				serverOut.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param username
	 * @param password
	 */
	private void signin(String username, String password) {

		user = new Database(username, password);

		try {

			if (!user.checkUsername()) {

				serverOut.writeObject(protocol.createMessage("sign-in", "false", "User does not exist."));
				serverOut.flush();

			} else if (!user.checkPassword()) {

				serverOut.writeObject(protocol.createMessage("sign-in", "false", "Password is incorrect."));
				serverOut.flush();

			} else {

				serverOut.writeObject(protocol.createMessage("sign-in", "true", "Welcome back, " + username + "."));
				serverOut.flush();
				signedIn = true;
				this.username = username;
				Server.connectedClients.put(username, this);
				Server.lobbyList.add(username);
				Server.sendUpdatedList();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param username
	 */
	private void signOut(String username) {

		Server.lobbyList.remove(username);
		Server.connectedClients.remove(username);
		this.signedIn = false;
		try {
			serverOut.writeObject(protocol.createMessage("sign-out", "true", "Sign-out successful"));
			Server.sendUpdatedList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 */
	public void updateLobby() {

		// Create new arrayList to store messages
		ArrayList<String> responseList = new ArrayList<>();

		// Add response type
		responseList.add("update-lobby");

		// Iterate over all elements in lobbyList
		for (int i = 0; i < Server.lobbyList.size(); i++) {
			if (!Server.lobbyList.get(i).equals(username)) {
				// Get sender of current message
				responseList.add(Server.lobbyList.get(i));
			}
		}

		// Convert arrayList of responses into a string array
		String[] response = new String[responseList.size()];
		response = responseList.toArray(response);

		// Send response to client
		try {
			serverOut.writeObject(protocol.createMessage(response));
			serverOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void gameTurnStart() {
		try {
			clientsTurn = false;
			serverOut.writeObject(protocol.createMessage("game-start"));
			serverOut.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// /**
	// *
	// * @param offset
	// */
	// public void getMessages(int offset) {
	//
	// try {
	//
	// if (!Server.messages.isEmpty()) {
	// int length = Server.getMessages().size();
	//
	// if (offset == -1) {
	//
	// for (int i = 0; i < length; i++) {
	//
	// serverOut.writeObject(protocol.createMessage("get-message",
	// Integer.toString(i),
	// Server.getMessages().get(i)[1], Server.getMessages().get(i)[2],
	// Server.getMessages().get(i)[3]) + "\n");
	// serverOut.flush();
	// }
	//
	// }
	//
	// else if (offset > -1) {
	//
	// for (int i = offset; i < length; i++) {
	//
	// serverOut.writeObject(protocol.createMessage("get-message",
	// Integer.toString(i),
	// Server.getMessages().get(i)[1], Server.getMessages().get(i)[2],
	// Server.getMessages().get(i)[3]) + "\n");
	// serverOut.flush();
	// }
	// }
	// }
	//
	// }
	//
	// catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	//
	// /**
	// *
	// * @param message0
	// */
	// public void sendMessages(Object message0) {
	//
	// String[] received = (String[]) this.received;
	// String[] message = (String[]) message0;
	// LocalDateTime time = LocalDateTime.now();
	// String timeNow = time.toString().substring(11, 16);
	//
	// try {
	// serverOut.writeObject(
	// protocol.createMessage("send-message", "true",
	// Integer.toString(Server.getMessages().size()))
	// + "\n");
	// serverOut.flush();
	// }
	//
	// catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// String[] messageToSend = { Integer.toString(Server.getMessages().size()),
	// received[1], timeNow, message[1] };
	// Server.messages.add(messageToSend);
	// }

	/**
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

}
