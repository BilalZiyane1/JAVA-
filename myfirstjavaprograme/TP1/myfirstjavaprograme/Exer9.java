package myfirstjavaprograme;

import java.util.Scanner;

public class Exer9 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Entrez le montant d'achat (M) : ");
	        double montantAchat = scanner.nextDouble();

	        double tauxReduction;

	        if (montantAchat < 5000) {
	            tauxReduction = 0.05;
	        } else if (montantAchat < 8000) {
	            tauxReduction = 0.08;
	        } else {
	            tauxReduction = 0.10;
	        }

	        double montantReduit = montantAchat * tauxReduction;

	        System.out.println("Le montant réduit est : " + montantReduit);
	    
	}

}
