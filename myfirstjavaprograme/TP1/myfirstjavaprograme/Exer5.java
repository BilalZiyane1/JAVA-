package myfirstjavaprograme;

import java.util.Scanner;

public class Exer5 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Entrez le premier nombre réel (a) : ");
	        double a = scanner.nextDouble();

	        System.out.print("Entrez le deuxième nombre réel (b) : ");
	        double b = scanner.nextDouble();

	        double maximum = Math.max(a, b);

	        System.out.println("Le maximum entre " + a + " et " + b + " est : " + maximum);
	    
	}

}
