import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class UDPCClient {
	public static void main (String []args) {
		
		try {
			// reflechir au contenu de la lettre
			String message = "bonjour";
			// adapter le contenu de la lettre au support de transmission
			byte [] papier = new byte[1024];
			papier =message.getBytes();
			// preparer l'enveloppe
			InetAddress ipd = InetAddress.getByName("localhost");
			int portd = 6969;
			DatagramPacket enveloppe = new DatagramPacket(papier, papier.length, ipd, portd);
			// cherecher B.A.L
			DatagramSocket bal = new DatagramSocket();
			// Envoyer la lettre
			bal.send(enveloppe);
			System.out.println("Lettre envoye depuis le client");
			
			
			//recevoir la reponse de serveur
			byte [] buffer = new byte[1024];
			DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            bal.receive(responsePacket);
            String messageR = new String(responsePacket.getData());
			System.out.println("Message recu est : "+messageR);
			System.out.println("From: "+responsePacket.getAddress().toString());
			System.out.println("Sent from port : "+ responsePacket.getPort());
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur : "+ e.getMessage());
		}
		
		}

}
