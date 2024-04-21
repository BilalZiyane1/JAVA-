import java.io.*;
import java.net.*;

public class TokenClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Request token");

            String response = in.readLine();
            System.out.println("Réponse du serveur : " + response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
