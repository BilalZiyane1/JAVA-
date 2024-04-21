import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class USDServer {
	public static void main(String[] args) {
		try {
			//Avoir ine BAlr
			int portr= 6969;
			DatagramSocket balr = new DatagramSocket(portr);
			// Preparer la structure d'acceuil des enveloppes
			byte [] buffer = new byte [1024];
			DatagramPacket envellope = new DatagramPacket(buffer, buffer.length);
			// attendre la reception
			System.out.println("Server waiting for a new letter arrival ......");
			balr.receive(envellope);
			//reception de la lettre
			String messageR = new String(envellope.getData());
			System.out.println("Message recu est : "+messageR);
			System.out.println("From: "+envellope.getAddress().toString());
			System.out.println("Sent from port : "+ envellope.getPort());
			//response
			
			String message_serveur ="Bonjour Client";
			byte [] papier = new byte[1024];
			papier =message_serveur.getBytes();
			DatagramPacket response = new DatagramPacket(papier, papier.length,
                    envellope.getAddress(), envellope.getPort());
			balr.send(response);
		} catch ( Exception e) {
			// TODO: handle exception
			System.out.println("Erreur :"+ e.getMessage());
		}
	}
}
