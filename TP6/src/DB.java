
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private Connection connection;

    public DB() {
        connectToDB();
    }

    private void connectToDB() {
        try {
            String url = "jdbc:mysql://localhost:3306/tp6";
            String username = "root";
            String password = "200405";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
}
