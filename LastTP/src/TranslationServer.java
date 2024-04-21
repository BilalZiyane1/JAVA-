//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class TranslationServer implements TranslationService {
//
//    // JDBC URL, username, and password of SQL Server
//    private static final String DB_URL = "jdbc:sqlserver://DESKTOP-D5MMLHR\\SQLEXPRESS:1433;databaseName=languages";
//    private static final String USER = "DESKTOP-D5MMLHR\\hp";
//    private static final String PASSWORD = "";
//
//    public TranslationServer() {
//        super();
//    }
//
//    @Override
//    public String translate(String word, String sourceLanguage, String targetLanguage) throws RemoteException {
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
//            // Prepare SQL statement to call the stored procedure
//            String sql = "{call TranslateKeyword(?, ?, ?, ?)}";
//            try (CallableStatement stmt = conn.prepareCall(sql)) {
//                // Set parameters
//                stmt.setString(1, word);
//                stmt.setString(2, sourceLanguage);
//                stmt.setString(3, targetLanguage);
//                stmt.registerOutParameter(4, java.sql.Types.NVARCHAR);
//                
//                // Execute the stored procedure
//                stmt.execute();
//                
//                // Get the result
//                String translation = stmt.getString(4);
//                return translation;
//            }
//        } catch (SQLException e) {
//            // Handle database exceptions
//            e.printStackTrace();
//            throw new RemoteException("Translation failed: " + e.getMessage(), e);
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            // Create an instance of your server
//            TranslationServer server = new TranslationServer();
//
//            // Export the remote object
//            TranslationService stub = (TranslationService) UnicastRemoteObject.exportObject(server, 0);
//
//            // Get the RMI registry
//            Registry registry = LocateRegistry.createRegistry(6969); // Using the default port
//
//            // Bind the stub of the remote object to the registry with a specific name
//            registry.bind("TranslationService", stub);
//
//            System.out.println("Translation service is ready.");
//        } catch (Exception e) {
//            System.err.println("Translation service failed to start: " + e.toString());
//            e.printStackTrace();
//        }
//    }
//}