package myfirstjavaprograme;

public class ExempleSurcharge {
	public void afficherMessage() {
		System.out.println("bonjour");	
		}

	 public void afficherMessage(String message) {
	        System.out.println("Message : " + message);
	    }

	 public void afficherMessage(String message, int nombre) {
	        System.out.println("Message : " + message + ", Nombre : " + nombre);
	    }

	 public static void main(String[] args) {
	        ExempleSurcharge exemple = new ExempleSurcharge();
	        exemple.afficherMessage();
	        exemple.afficherMessage("Bonjour"); 
	        exemple.afficherMessage("Bonjour", 42); 
	    }
}
