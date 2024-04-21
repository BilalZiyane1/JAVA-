import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;

public class Ex2Server extends JFrame {
    private JTextArea messagesArea;
    private JTextField messageInput;
    private JButton sendButton;
    private DatagramSocket socket;
    private int serverPort = 12346;
    private DatagramPacket receivePacket;

    public Ex2Server() {
        try {
        	setTitle("ChatApp");
            socket = new DatagramSocket(serverPort);
            byte[] buffer = new byte[1024];
            receivePacket = new DatagramPacket(buffer, buffer.length);

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
                        DatagramPacket sendPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
                        		receivePacket.getAddress(),receivePacket.getPort());
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
                        while (true) {
                            socket.receive(receivePacket);
                            String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
                            InetAddress serverAddress = receivePacket.getAddress();
                            String serverInfo =  serverAddress.getHostAddress() + ":";
                            messagesArea.append("Client: "+ serverInfo + " =>  " +msg + "\n");
                            
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
                Ex2Server server = new Ex2Server();
                server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                server.setSize(400, 300);
                server.setVisible(true);
            }
        });
    }
}
