import java.net.*;

public class ServerMulticast {
	public static void main(String []args) {
		try {
			MulticastSocket ms = new MulticastSocket();
			int portid = 12345;
			InetAddress group = InetAddress.getByName("224.100.100.100");
			String message = "Hi everyone";
			byte [] buffer = new byte[1024];
			buffer = message.getBytes();
			DatagramPacket p = new DatagramPacket(buffer , buffer.length , group , portid);
			ms.send(p);
			System.out.println("Message envoye au groupe");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur de Serveur :" + e.getMessage());
		}
}
}
