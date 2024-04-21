import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the translation service implementation
            TranslationService translationService = new TranslationServiceImpl();

            // Create and start the RMI registry on the default port (1099)
            Registry registry = LocateRegistry.createRegistry(6968);

 // Bind the translation service implementation to the registry with the name "TranslationService"
            registry.rebind("TranslationService", translationService);

            System.out.println("Translation service is running...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

