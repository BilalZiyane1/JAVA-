import java.sql.*;
public class Premiere {
    public static void main(String[] args) throws Exception {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp3", "root", "200405");
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM livre ORDER BY specialite");
            while (result.next()) {
                int code = result.getInt("code");
                String titre = result.getString("titre");
                String auteur = result.getString("auteur");
                String specialite = result.getString("specialite");
                System.out.println("Code: " + code + ", Titre: " + titre + ", Auteur: " 
                + auteur + ", Specialite: " + specialite);    
            }
            conn.close(); 
        }    
}
