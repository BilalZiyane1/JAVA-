import java.sql.*;
import java.util.Scanner;
public class Troisieme {
    public static void main(String[] args) {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp3",
            		"root", "200405");
            Scanner scanner = new Scanner(System.in);          
            System.out.println("Saisir le code du livre : ");
            int code = scanner.nextInt();
            System.out.println("Saisir le titre du livre : ");
            String titre = scanner.nextLine();            
            System.out.println("Saisir l'auteur du livre : ");
            String auteur = scanner.nextLine();            
            System.out.println("Saisir la spécialité du livre : ");
            String specialite = scanner.nextLine();            
            System.out.println("Saisir le numero du livre : ");
            String numero = scanner.nextLine();
            String query = "INSERT INTO livre (code, titre, auteur, specialite, numero)"
            		+ " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, code);
            statement.setString(2, titre);
            statement.setString(3, auteur);
            statement.setString(4, specialite);
            statement.setString(5, numero);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Les données du livre ont été insérées avec succès !");
            } else {
                System.out.println("Erreur lors de l'insertion des données.");
            }

            conn.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
