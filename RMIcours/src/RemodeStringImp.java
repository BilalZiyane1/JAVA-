import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class RemodeStringImp extends UnicastRemoteObject implements RemodeString {
public RemodeStringImp() throws RemoteException{
	super();
}
public String concatener(String s,String p) throws RemoteException{
	return s+p;
}
public String miror(String s ) throws RemoteException{
	String res="";
	for(int i=s.length()-1 ;i>=0 ; i--) {
		res=res+s.charAt(i);
	}
	
	return res;
}
}
