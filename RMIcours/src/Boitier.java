import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
public class Boitier {
	public static void main(String [] args) {
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("Creation du register");
			Circuit odC = new Circuit();
			RemodeStringImp odS = new RemodeStringImp();
			System.out.println("Creation des object distant");
			Naming.rebind("rmi://127.0.0.1/L7sab", odC);
			System.out.println("Deploiement des object distant circuit");
			Naming.rebind("rmi://127.0.0.1/chaine", odS);
			System.out.println("deploiement des object distant String ");
			
		} catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
	}
}
