package ChatServer;

import Protocol.SimpleProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * ClientThread class for ChatServer
 *
 * @author Ishmael Aqsar
 * @version 11/03/2018
 */
public class ClientThread implements Runnable {

    private Socket clientSocket;
    private SimpleProtocol protocol;
    private boolean connected = true;
    private boolean signedIn = false;
    private PrintWriter out;
    private String[] received;
    private LocalDateTime time = LocalDateTime.now();

    ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                protocol = new SimpleProtocol();

                // If a connection is accepted
                if (connected) {
                    out.write(protocol.createMessage("send-message", "Welcome to the Server.") + "\n");
                    out.flush();
                }
                connected = false;

                while (serverIn.ready()) {
                    received = protocol.decodeMessage(serverIn.readLine());

                    if (received[0].equals("sign-up")) {
                        signUp(received[1], received[2]);
                    } else if (received[0].equals("sign-in")) {
                        signIn(received[1], received[2]);
                    }

                    while (signedIn) {
                        String[] message = protocol.decodeMessage(serverIn.readLine());
                        if (message[0].equals("get-message")) {
                            getMessages(Integer.parseInt(message[1]));
                        }
                        if (message[0].equals("send-message")) {
                            sendMessages(message);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void signUp(String username, String password) {
        int username_length = username.length();
        int password_length = password.length();
        if (Server.users.containsKey(username)) {
            out.write(protocol.createMessage("sign-up", "false",
                    "User already exists.") + "\n");
            out.flush();
        } else if (username_length < 5 || username_length > 20) {
            out.write(protocol.createMessage("sign-up", "false",
                    "username must be between 5 and 20 characters.") + "\n");
            out.flush();
        } else if (password_length < 8 || password_length > 32) {
            out.write(protocol.createMessage("sign-up", "false",
                    "Password must be between 8 and 32 characters.") + "\n");
            out.flush();
        } else {
            Server.users.put(username, password);
            out.write(protocol.createMessage("sign-up", "true",
                    "Sign up for user " + username + " successful.") + "\n");
            out.flush();
        }
    }

    private void signIn(String username, String password) {

        if (Server.users.containsKey(username)) {
            if (Server.users.get(username).equals(password)) {
                out.write(protocol.createMessage("sign-in", "true",
                        "Welcome back, " + username + ".") + "\n");
                out.flush();
                signedIn = true;
            } else {
                out.write(protocol.createMessage("sign-in", "false",
                        "Incorrect password.") + "\n");
                out.flush();
            }
        } else {
            out.write(protocol.createMessage("sign-up", "false",
                    "User does not exist.") + "\n");
            out.flush();
        }
    }

    private void getMessages(int offset) {
        if (!Server.messages.isEmpty()) {
            int length = Server.getMessages().size();
            if (offset == -1) {
                for (int i = 0; i < length; i++) {
                    out.write(protocol.createMessage("get-message", Integer.toString(i),
                            Server.getMessages().get(i)[1], Server.getMessages().get(i)[2],
                            Server.getMessages().get(i)[3]) + "\n");
                    out.flush();
                }
            } else if (offset > -1) {
                for (int i = offset; i < length; i++) {
                    out.write(protocol.createMessage("get-message", Integer.toString(i),
                            Server.getMessages().get(i)[1], Server.getMessages().get(i)[2],
                            Server.getMessages().get(i)[3]) + "\n");
                    out.flush();
                }
            }
        }
    }

    private void sendMessages(String[] message) {
        // Only give timestamp
        String timeNow = time.toString().substring(11, 16);

        out.write(protocol.createMessage("send-message", "true",
                Integer.toString(Server.getMessages().size())) + "\n");
        out.flush();

        String[] messageToSend = {Integer.toString(Server.getMessages().size()), received[1], timeNow,
                message[1]};
        Server.messages.add(messageToSend);
    }

}
