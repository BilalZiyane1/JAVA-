import java.io.*;
import java.net.*;
import java.util.concurrent.ForkJoinWorkerThread;

public class MulticastClient {
	public static void main(String[] args) {
		try {
			int portr=12345;
			MulticastSocket ms= new MulticastSocket(portr);
			InetAddress groupe = InetAddress.getByName("225.100.100.100");
			System.out.println("Client has joinned the groupe");
			ms.joinGroup(groupe);
			byte [] buffer = new byte[1024];
			DatagramPacket p = new DatagramPacket(buffer, buffer.length);
			ms.receive(p);
			String message = new String(p.getData());
			System.out.println("message recu est :"+ message);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur: "+e);
		}
	}
}
