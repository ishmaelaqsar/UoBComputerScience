package ChatServer;

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
 * Server class for ChatRoom
 *
 * @author Ishmael Aqsar
 * @version 11/03/2018
 */
public class Server {

    static Map<String, String> users = new HashMap<>();
    static ArrayList<String[]> messages = new ArrayList<>();

    /**
     * @return list of messages
     */
    public static ArrayList<String[]> getMessages() {
        return messages;
    }

    public static void main(String[] args) {
        ExecutorService threads = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        Socket clientSocket;
        try {
            InetAddress ip = InetAddress.getByName(args[0]);
            int portNumber = Integer.parseInt(args[1]);

            serverSocket = new ServerSocket(portNumber, 50, ip);

            System.out.println("\r\nRunning Server: " +
                    "Host=" + ip +
                    " Port=" + portNumber);

            while (true) {
                try {
                    clientSocket = serverSocket.accept();
                    System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " connected.");
                    threads.execute(new ClientThread(clientSocket));
                } catch (IOException e) {
                    System.out.println("Cannot connect on port: " + portNumber);
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Arguments not provided. Please provide hostname and port.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O error occurred when opening the socket.");
            e.printStackTrace();
        }
    }
}
