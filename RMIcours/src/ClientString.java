import java.rmi.Naming;
public class ClientString {
	public static void main(String[] args) {
		try {
			RemodeString s = (RemodeString)Naming.lookup("rmi://127.0.0.1/chaine");
			System.out.println("test de concat: " + s.concatener("Bonjour", " Mr Bilal"));
			System.out.println("test de miror: "+ s.miror("Bilal"));
		} catch (Exception e) {
			System.out.println("Error :"+e.getMessage());
		}
	}
}
