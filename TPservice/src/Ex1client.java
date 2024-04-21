import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ex1client {
    public static void main(String[] args) {
        try {
            String message = "123456789012345";
            byte[] paper = message.getBytes();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 6999;
            DatagramPacket envelope = new DatagramPacket(paper, paper.length, serverAddress, serverPort);
            DatagramSocket postbox = new DatagramSocket();
            postbox.send(envelope);
            System.out.println("Letter sent from the client");

            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            postbox.receive(responsePacket);
            String mirroredMessage = new String(responsePacket.getData()).trim(); 
            System.out.println("Mirrored message received: " + mirroredMessage);
            System.out.println("From: " + responsePacket.getAddress().toString());
            System.out.println("Sent from port: " + responsePacket.getPort());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
