import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TranslationService extends Remote {
    String translate(String word, String sourceLanguage, String targetLanguage)
    		throws RemoteException;
}
