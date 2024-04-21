import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pave extends Remote{
public int getSom(int x ,int y) throws RemoteException;
public int getProd(int x,int y) throws RemoteException;
}
