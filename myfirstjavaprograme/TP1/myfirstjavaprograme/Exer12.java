package myfirstjavaprograme;

import java.util.Scanner;
public class Exer12 {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Entrez un entier naturel n : ");
	        int n = scanner.nextInt();

	        int produit = 1;

	        for (int i = 1; i <= n; i++) {
	            produit *= i;
	        }

	        System.out.println("Le produit de 1 à " + n + " est : " + produit);
	    }
}
