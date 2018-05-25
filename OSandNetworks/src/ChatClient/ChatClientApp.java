package ChatClient;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ChatClientApp extends JFrame {

    public static ChatClientApp frame = null;
    public GuiSignin guiSignin = new GuiSignin();
    public GuiSignUp guiSignUp = new GuiSignUp();
    public GuiChatroom guiChatroom = new GuiChatroom();
    public Client client = new Client();

    /**
     * Create the frame.
     */
    public ChatClientApp() {
        setTitle("Sign in");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        add(guiChatroom);
        add(guiSignin);
        add(guiSignUp);
        setContentPane(guiSignin);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ChatClientApp();
                    frame.setVisible(true);
                    frame.client.setHost(args[0]);
                    frame.client.setPort(Integer.parseInt(args[1]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
