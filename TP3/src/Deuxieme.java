import java.sql.*;
import java.util.Scanner;

public class Deuxieme {

	public static void main(String[] args) throws Exception {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter le nom du bibliotheque : ");
		String biblio_nom = scan.nextLine();
		
		//Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp3","root","200405");
		PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM livre, bibliotheque WHERE bibliotheque.numero = "
                + "livre.numero AND bibliotheque.nom = ?");
        preparedStatement.setString(1, biblio_nom);

        ResultSet result = preparedStatement.executeQuery();
		
		while(result.next())
		{
			String Code = result.getString("code");
			String Titre = result.getString("titre");
			String Auteur = result.getString("auteur");
			String Specialite = result.getString("specialite");
			
			System.out.println("Code : "+Code+", Tire : "+Titre+", Auteur : "+Auteur+", Specialite : "+Specialite+".");	
		}
		
		conn.close();
	}

}