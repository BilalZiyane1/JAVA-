import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class One {
    public static void main(String[] args) {
        // Database URL with SQL Server authentication
        String url = "jdbc:sqlserver://Bilal\\SQLEXPRESS;databaseName=dictionaire;user=bilalziyane;password=biLAL2004$$;encrypt=false;trustServerCertificate=false";

        // SQL query to execute
        String query = "SELECT * FROM translation";

        try {
            // Establish connection
            try(Connection connection = DriverManager.getConnection(url)){
                System.out.println("Connection established");

                // Create statement
                Statement statement = connection.createStatement();

                // Execute query
                ResultSet resultSet = statement.executeQuery(query);

                // Print table content
                while (resultSet.next()) {
                    // Retrieve data from each column
                    int id = resultSet.getInt("id");
                    String ar = resultSet.getString("ar");
                    String fr = resultSet.getString("fr");
                    String en = resultSet.getString("en");
                    String es = resultSet.getString("es");
                    
                    // Print data
                    System.out.println("ID: " + id);
                    System.out.println("Arabic: " + ar);
                    System.out.println("French: " + fr);
                    System.out.println("English: " + en);
                    System.out.println("Spanish: " + es);
                    System.out.println();
                }

                // Close ResultSet and Statement
                resultSet.close();
                statement.close();
            } catch(SQLException e) {
                System.out.println("Error de connection: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
