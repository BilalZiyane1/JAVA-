import java.sql.*;

public class DatabaseConnection {
    // JDBC URL, username, and password of SQL Server
	// JDBC URL, username, and password of SQL Server
	private static final String url = "jdbc:sqlserver://Bilal\\SQLEXPRESS;databaseName=dictionaire;user=bilalziyane;password=biLAL2004$$;encrypt=false;trustServerCertificate=false";

	



    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establishing a connection to the database
            conn = DriverManager.getConnection(url);

            // Creating a statement
            stmt = conn.createStatement();

            // Executing a SQL query to select all records from the "translation" table
            String sql = "SELECT * FROM translation";
            rs = stmt.executeQuery(sql);

            // Processing the result set
            while (rs.next()) {
                // Retrieving and printing the values of each column
                int id = rs.getInt("id");
                String ar = rs.getString("ar");
                String fr = rs.getString("fr");
                String en = rs.getString("en");
                String es = rs.getString("es");

                System.out.println("ID: " + id + ", Arabic: " + ar + ", French: " + fr + ", English: " + en + ", Spanish: " + es);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the result set, statement, and connection
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
