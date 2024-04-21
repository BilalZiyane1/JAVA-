import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class Circuit extends UnicastRemoteObject implements Pave{
	public Circuit()throws RemoteException
	{
		super();
	}
	public int getSom(int x, int y) throws RemoteException{
		return x+y;
	}
	public int getProd(int x, int y) throws RemoteException{
		return x*y;
	}

}
