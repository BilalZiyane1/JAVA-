import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TranslationServiceImpl extends UnicastRemoteObject implements TranslationService {

    // JDBC URL, username, and password of SQL Server
    private static final String url = "jdbc:sqlserver://Bilal\\SQLEXPRESS;databaseName=dictionaire;user=bilalziyane;"
    		+ "password=biLAL2004$$;encrypt=false;trustServerCertificate=false";


    protected TranslationServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String translate(String word, String sourceLanguage, String targetLanguage) throws RemoteException {
        try (Connection conn = DriverManager.getConnection(url)) {
            // Prepare SQL statement to call the stored procedure
            String sql = "{call TranslateKeyword(?, ?, ?, ?)}";
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                // Set parameters
                stmt.setString(1, word);
                stmt.setString(2, sourceLanguage);
                stmt.setString(3, targetLanguage);
                stmt.registerOutParameter(4, java.sql.Types.NVARCHAR);
                
                // Execute the stored procedure
                stmt.execute();
                
                // Get the result
                String translation = stmt.getString(4);
                return translation; // Return the translation
            }
        } catch (SQLException e) {
            // Handle database exceptions
            e.printStackTrace();
            throw new RemoteException("Translation failed: " + e.getMessage(), e);
        }
    }
}
