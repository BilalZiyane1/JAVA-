import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends JFrame {
    private JTextArea messagesArea;
    private JTextField messageInput;
    private JButton sendButton;
    private Socket socket;
    private PrintWriter out;

    public ChatClient() {
        try {
            socket = new Socket("localhost", 12346);
            out = new PrintWriter(socket.getOutputStream(), true);

            setLayout(new BorderLayout());
            messagesArea = new JTextArea();
            messagesArea.setEditable(false);
            add(new JScrollPane(messagesArea), BorderLayout.CENTER);

            Box box = Box.createHorizontalBox();
            add(box, BorderLayout.SOUTH);
            messageInput = new JTextField();
            sendButton = new JButton("Send");
            box.add(messageInput);
            box.add(sendButton);

            sendButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        String msg = messageInput.getText();
                        messagesArea.append("You: " + msg + "\n"); 
                        out.println(msg);
                        messageInput.setText("");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            new Thread(new Runnable() {
                public void run() {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String msg;
                        while ((msg = in.readLine()) != null) {
                            messagesArea.append("Server: " + msg + "\n"); 
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChatClient client = new ChatClient();
                client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                client.setSize(400, 300);
                client.setVisible(true);
            }
        });
    }
}
