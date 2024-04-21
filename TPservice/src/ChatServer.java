import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatServer extends JFrame {
    private JTextArea messagesArea;
    private ServerSocket serverSocket;

    public ChatServer() {
        try {
            setTitle("ChatApp");
            serverSocket = new ServerSocket(12346);

            setLayout(new BorderLayout());
            messagesArea = new JTextArea();
            messagesArea.setEditable(false);
            add(new JScrollPane(messagesArea), BorderLayout.CENTER);

            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            Socket clientSocket = serverSocket.accept();
                            new Thread(new ClientHandler(clientSocket)).start();
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

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private String clientAddress;
        private boolean connected;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            this.clientAddress = clientSocket.getInetAddress().getHostAddress();
            this.connected = true;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String msg;
                while (connected && (msg = in.readLine()) != null) {
                    if (msg.equalsIgnoreCase("over")) {
                        out.println("Communication ended.");
                        messagesArea.append("Communication ended. with the client : "+ clientAddress);
                        connected = false;
                    } else {
                        messagesArea.append("Client " + clientAddress + ": " + msg + "\n"); 

                 
                        out.println(msg + " OK");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChatServer server = new ChatServer();
                server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                server.setSize(400, 300);
                server.setVisible(true);
            }
        });
    }
}
