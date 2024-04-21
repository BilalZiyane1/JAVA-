import java.io.*;
import java.net.*;

public class MultiClientServer {
    
    public static void main(String[] args) {
        try {
            // It should be on a different port than 80 if you're using it for TCP
            int port = 80;
            ServerSocket service = new ServerSocket(port);
            Worker w;
            while (true) {
                // Accepting client connection
                Socket s = service.accept();
                InetAddress ipd = s.getInetAddress();
                System.out.println("Le client :" + ipd.toString() + " Viens de se connecter");
                w = new Worker(s);
                w.start();
            }
        } catch (IOException e) {
            System.out.println("Erreur de Serveur :" + e.getMessage());
        }
    }
}
