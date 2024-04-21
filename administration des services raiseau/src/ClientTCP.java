import java.io.*;
import java.net.*;
public class ClientTCP {
	public static void main(String []args) {
		try {
			//il faut etre abonne au service
			int port = 80;
			InetAddress ips = InetAddress.getByName("localhost");
			System.out.println("demande de connexion");
			Socket s = new Socket(ips,port);
			System.out.println("Connexion Etablit");
			BufferedReader hautparleur = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter microphone = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
			System.out.println("preparation de ioStream");
			microphone.println("Salam Setrver");
			//read the server response .
			System.out.println(hautparleur.readLine());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur de Serveur :" + e.getMessage());
		}
	
	}
}
