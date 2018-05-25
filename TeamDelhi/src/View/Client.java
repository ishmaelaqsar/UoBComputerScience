package View;

import Protocol.SimpleProtocol;

import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

import javax.swing.JOptionPane;

/**
 * Class to send user information from the GUI to the server and receive information from the server
 *
 * @author Team Delhi
 */
public class Client {

    public ObjectOutputStream outToServer; // output stream to server
    private ObjectInputStream inFromServer; // input stream from server
    private SimpleProtocol protocol = new SimpleProtocol(); // pack and unpack
    // messages
    private Integer offset = -1; // offset of messages, at the beginning it is
    // -1. Update it to the offset of the latest
    // message
    private Socket clientSocket; // socket connecting to server
    private String host = ""; // IP address of server
    private Integer port = 0; // Port number of server

    /*
     * Read a line from server and unpack it using SimpleProtocol
     */
    public String[] getResponse() {
        try {
        	String input = (String) inFromServer.readObject();
            return protocol.decodeMessage(input);
        } catch (IOException | ClassNotFoundException | RuntimeException e) {
        	System.out.println("The server is down!");
        	JOptionPane.showMessageDialog(GameLauncher.frame, "Lost connection to server. Shutting down.", "Error message",
                    JOptionPane.ERROR_MESSAGE);
            GameLauncher.frame.dispatchEvent(new WindowEvent(GameLauncher.frame, WindowEvent.WINDOW_CLOSING));
        }
        return null;
    }

    /*
     * Send sign-up request to server, return the response to GuiSignUp
     */
    public String[] signup(String user, String pass) {
        String string = protocol.createMessage("sign-up", user, pass);
        try {
            outToServer.writeObject(string);
            // outToServer.writeObject(new String[] {"sign-up", user, pass});
            outToServer.flush();
            String[] response = getResponse();
            assert response != null;
            if (response[1].equals("true")) {
                System.out.println("Sign up successful.");
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Send sign-in request to server, return the response to GuiSignIn
     */
    public String[] signin(String user, String pass) {
        String string = protocol.createMessage("sign-in", user, pass);
        try {
            outToServer.writeObject(string);
            outToServer.flush();
            String[] response = getResponse();
            assert response != null;
            if (response[1].equals("true")) {
                System.out.println("Sign-in successful.");
            } else {
                System.out.println(response[2]);
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Send sign-out request to server, return the response to GuiSignIn
     */
    public String[] signout(String username) {
        String string = protocol.createMessage("sign-out", username);
        try {
            outToServer.writeObject(string);
            outToServer.flush();
            String[] response = getResponse();
            assert response != null;
            if (response[1].equals("true")) {
                System.out.println("Sign-out successful.");
            } else {
                System.out.println(response[2]);
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Send get-message request to server
     */
    public void get_message() {
        String string = protocol.createMessage("get-message", this.offset.toString());
        try {
            outToServer.writeBytes(string);
            outToServer.flush();
        } catch (IOException e) {
            System.out.println("Unable to get message");
            e.printStackTrace();
        }
    }

    /*
     * Send a message to server.
     */
    public void send_message(String msg) {
        String string = protocol.createMessage("send-message", msg);
        try {
            outToServer.writeBytes(string);
            outToServer.flush();
        } catch (IOException e) {
            System.out.println("Unable to send message.");
            e.printStackTrace();
        }
    }

    /*
     * Initialize socket and input/output streams
     */
    public void start() {
        try {
            clientSocket = new Socket(this.host, this.port);
            outToServer = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            outToServer.flush();
            inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println(Objects.requireNonNull(getResponse())[1]);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server is down!");
        }
    }

    /*
     * Close socket
     */
    public void stop() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String[] shipsPlaced() {
        String string = protocol.createMessage("ships-placed");
        try {
            
            outToServer.writeObject(string);
            outToServer.flush();

            String[] response = getResponse();
            assert response != null;

            if (response[1].equals("true")) {
                System.out.println("ships sending");
            } else {
                System.out.println(response[2]);
            }
            return response;

        } catch (IOException e) {
            System.out.println("Unable to get lobby");
            e.printStackTrace();
            return null;
        }
    }

    public Object sendShips() {
        try {
            outToServer.writeObject(GameLauncher.frame.localPlayer);
            outToServer.flush();
            System.out.println("sending ships.");
            Object response = inFromServer.readObject();
            assert response != null;

            return response;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Unable to get lobby");
            e.printStackTrace();
            return null;
        }
    }
    
//    public void attack(String coords, String index) {
//    	String string = protocol.createMessage("attack-sent", coords, index);
//    	
//        try {
//            outToServer.writeObject(string);
//            outToServer.flush();
//
//        }
//        
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void invitePlayer(String opponent) {
        String string = protocol.createMessage("send-invite", opponent, GameLauncher.frame.localPlayer.getUserName());
        try {
            outToServer.writeObject(string);
            outToServer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void Win() {
    	String string = protocol.createMessage("win", GameLauncher.frame.localPlayer.getUserName());
    	try {
            outToServer.writeObject(string);
            outToServer.flush();
            
            String[] response = getResponse();
            if (response[0].equals("win")) {
            	System.out.println(response[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
