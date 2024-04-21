import java.io.*;
import java.net.*;

public class TokenServer {
    private static final int MAX_TOKENS = 5;
    private static int currentTokens = MAX_TOKENS; 

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Serveur de jetons démarré. En attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexion acceptée de " + clientSocket.getInetAddress());
                if (currentTokens > 0) {
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("Token granted");
                    currentTokens--;
                    System.out.println("Jeton envoyé à " + clientSocket.getInetAddress() + ". Jetons restants : " + currentTokens);
                } else {
                    
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("No tokens available");
                    System.out.println("Aucun jeton disponible pour " + clientSocket.getInetAddress());
                }

                
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
