import java.io.*;
import java.net.*;
public class TCPServer {
	public static void main(String [] args) {
		try {
			//il faut etre abonne au service
			int port = 80;
			ServerSocket service = new ServerSocket(port);
			System.out.println("Server is waiting for a new incoming call ...");
			Socket s = service.accept();
			BufferedReader hautparleur = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter microphone = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
			System.out.println("preparation de ioStream");
			//reception
			String message = hautparleur.readLine();
			System.out.println("Message recu est : "+message);
			//envoyer un message
			String response ="<html><body><h1>Je suis un Petit serveur web comme Apach</h1> <h2>Realiser par Bilal</h2></body></html>";
			String response1="wa3alaykom salam";
			microphone.println(response);
			s.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur de Serveur :" + e.getMessage());
		}
	}
}
