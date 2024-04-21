import java.io.*;
import java.net.*;
public class Worker extends Thread{
		// TODO Auto-generated method stub
		Socket s;
		BufferedReader hautparleur;
		PrintWriter microphone;
		String message;
		public Worker(Socket ps) {
			super();
			this.s =ps;
			try {
				 hautparleur = new BufferedReader(new InputStreamReader(s.getInputStream()));
				 microphone = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
			} catch (Exception e) {
				
				// TODO: handle exception
			}
	}
		@Override
		public void run() {
			try {
				message = hautparleur.readLine();
				System.out.println(s.getInetAddress().toString()+ ">>"+ message);
				while(message.equals("bye"))
				{
					//envoyer un message
					String reponse = message + "bien recu";
					microphone.println(reponse);
					message = hautparleur.readLine();
					System.out.println("Message recu est : "+message);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

}
