package myfirstjavaprograme;

import java.util.Scanner;

public class Exer2 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in); 
	        System.out.print("Entrez la quantité : ");
	        int quantite = scanner.nextInt();
	        System.out.print("Entrez le prix unitaire : ");
	        double prixUnitaire = scanner.nextDouble();
	        double montant = quantite * prixUnitaire;
	        System.out.println("Le montant à payer est : " + montant);
	        scanner.close();
	}

}
