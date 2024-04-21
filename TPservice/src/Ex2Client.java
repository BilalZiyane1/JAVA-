import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;

public class Ex2Client extends JFrame {
    private JTextArea messagesArea;
    private JTextField messageInput;
    private JButton sendButton;
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort = 12346;

    public Ex2Client() {
        try {
            socket = new DatagramSocket();
            serverAddress = InetAddress.getByName("localhost");

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
                        messagesArea.append("You: " + msg + "\n"); // Display sent message
                        DatagramPacket sendPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverAddress, serverPort);
                        socket.send(sendPacket);
                        messageInput.setText("");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            new Thread(new Runnable() {
                public void run() {
                    try {
                        byte[] buffer = new byte[1024];
                        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                        while (true) {
                            socket.receive(receivePacket);
                            String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
                            InetAddress serverAddress = receivePacket.getAddress();
                            String serverInfo = serverAddress.getHostAddress() + ":";
                            messagesArea.append("Server: "+serverInfo + " =>  " + msg + "\n"); // Display received message
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
                Ex2Client client = new Ex2Client();
                client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                client.setSize(400, 300);
                client.setVisible(true);
            }
        });
    }
}
