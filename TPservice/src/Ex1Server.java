import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ex1Server {
    public static void main(String[] args) {
        try {
            int serverPort = 6999;
            DatagramSocket mailbox = new DatagramSocket(serverPort);
            byte[] buffer = new byte[10];
            DatagramPacket envelope = new DatagramPacket(buffer, buffer.length);
            System.out.println("Server waiting for a new letter arrival ......");
            mailbox.receive(envelope);
            String messageReceived = new String(envelope.getData()).trim(); // Trim to remove extra bytes
            System.out.println("Message received: " + messageReceived);
            System.out.println("From: " + envelope.getAddress().toString());
            System.out.println("Sent from port: " + envelope.getPort());

            // Mirror the message
            StringBuilder mirroredMessage = new StringBuilder(messageReceived).reverse();
            byte[] mirroredPaper = mirroredMessage.toString().getBytes();
            DatagramPacket response = new DatagramPacket(mirroredPaper, mirroredPaper.length,
                    envelope.getAddress(), envelope.getPort());
            mailbox.send(response);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
