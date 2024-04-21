import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Remote;

public interface RemodeString extends Remote{
String concatener(String s ,String p) throws RemoteException;
String miror(String s ) throws RemoteException;
}
