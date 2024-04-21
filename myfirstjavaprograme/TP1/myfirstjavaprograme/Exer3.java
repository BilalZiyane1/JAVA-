package myfirstjavaprograme;

import java.util.Scanner;

public class Exer3 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Entrez le premier nombre : ");
	        int nombre1 = scanner.nextInt();

	        System.out.print("Entrez le deuxième nombre : ");
	        int nombre2 = scanner.nextInt();

	        System.out.print("Entrez le troisième nombre : ");
	        int nombre3 = scanner.nextInt();
	        int somme = nombre1 + nombre2 + nombre3;

	        int produit = nombre1 * nombre2 * nombre3;

	        double moyenne = (double) somme / 3;

	        System.out.println("La somme est : " + somme);
	        System.out.println("Le produit est : " + produit);
	        System.out.println("La moyenne est : " + moyenne);
	    
	}

}
