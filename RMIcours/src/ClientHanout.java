import java.rmi.Naming;
public class ClientHanout {
	public static void main(String [] args) {
		try {
			Pave mika =(Pave)Naming.lookup("rmi://127.0.0.1/L7sab");
			System.out.println("Test de somme:"+ mika.getSom(100,300));
			System.out.println("Test de produit:"+mika.getProd(100,2));
		}catch(Exception e) {
			System.out.println("Error: "+ e.getMessage());
		}
	}
}
