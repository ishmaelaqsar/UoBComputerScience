import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
package View;


import Protocol.SimpleProtocol;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
 
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
 
import static junit.framework.TestCase.assertEquals;
 
public class tests {
 
    private SimpleProtocol protocol = new SimpleProtocol();   // pack and unpack messages
    private Socket clientSocket;
    ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;
 
    // Test 0
    @org.junit.Test
    public void test0() {
        String[] actualResponse = null;
        try {
            InetAddress hostname = InetAddress.getLocalHost();
            int port = 8080;
            clientSocket = new Socket(hostname, port);
            outToServer = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            outToServer.flush();
            inFromServer = new ObjectInputStream(clientSocket.getInputStream());
 
            actualResponse = protocol.decodeMessage((String) inFromServer.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String expectedResponse = "Welcome to the Server.\n";
 
        assertEquals(expectedResponse, actualResponse[1]);
    }
 
    // Test 1
    @org.junit.Test
    public void test1() {
        String username = "Test1";
        String password = "password";
 
        String expectedResponse = "Registration for Test1 successful.\n";
        String[] actualResponse = sendObject(username, password, "sign-up");
 
        assertEquals(expectedResponse, actualResponse[2]);
    }
 
    // Test 2
    @org.junit.Test
    public void test2() {
        String username = "Test1";
        String password = "password";
 
        String expectedResponse = "Username already in use, please choose another.\n";
        String[] actualResponse = sendObject(username, password, "sign-up");
 
        assertEquals(expectedResponse, actualResponse[2]);
    }
 
    // Test 3
    @org.junit.Test
    public void test3() {
        String username = "Test1";
        String password = "password";
 
        String expectedResponse = "Welcome back, Test1.\n";
        String[] actualResponse = sendObject(username, password, "sign-in");
 
        assertEquals(expectedResponse, actualResponse[2]);
    }
 
    // Test 4
    @org.junit.Test
    public void test4() {
        String username = "Test4";
        String password = "password";
 
        String expectedResponse = "User does not exist.\n";
        String[] actualResponse = sendObject(username, password, "sign-in");
 
        assertEquals(expectedResponse, actualResponse[2]);
    }
 
    // Test 5
    @org.junit.Test
    public void test5() {
        String username = "Test1";
        String password = "wrong-password";
 
        String expectedResponse = "Password is incorrect.\n";
        String[] actualResponse = sendObject(username, password, "sign-in");
 
        assertEquals(expectedResponse, actualResponse[2]);
    }
 
    private String[] sendObject(String username, String password, String task) {
        String[] actualResponse = null;
        try {
            clientSocket = new Socket(InetAddress.getLocalHost(), 8080);
            outToServer = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            outToServer.flush();
            inFromServer = new ObjectInputStream(clientSocket.getInputStream());
 
            outToServer.writeObject(protocol.createMessage(task, username, password));
            outToServer.flush();
 
            actualResponse = protocol.decodeMessage((String) inFromServer.readObject());
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return actualResponse;
    }
 
}