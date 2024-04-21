package myfirstjavaprograme;

import java.util.Scanner;

public class Exer6 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Entrez la valeur de x : ");
	        int x = scanner.nextInt();

	        System.out.print("Entrez la valeur de y : ");
	        int y = scanner.nextInt();

	        System.out.println("Avant l'échange :");
	        System.out.println("x = " + x);
	        System.out.println("y = " + y);
	        int temp = x;
	        x = y;
	        y = temp;

	        System.out.println("\nAprès l'échange :");
	        System.out.println("x = " + x);
	        System.out.println("y = " + y);
	    
	}

}
