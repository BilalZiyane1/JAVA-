package myfirstjavaprograme;

import java.util.Scanner;
public class Exer14 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.println("Entrez la note 1 :");
	        double note1 = scanner.nextDouble();

	        System.out.println("Entrez la note 2 :");
	        double note2 = scanner.nextDouble();

	        System.out.println("Entrez la note 3 :");
	        double note3 = scanner.nextDouble();

	        System.out.println("Entrez la note 4 :");
	        double note4 = scanner.nextDouble();

	        double moyenne = (note1 + note2 + note3 + note4) / 4;

	        System.out.println("La moyenne est : " + moyenne);

	        if (moyenne >= 16) {
	            System.out.println("Mention : Très bien");
	        } else if (moyenne >= 14) {
	            System.out.println("Mention : Bien");
	        } else if (moyenne >= 12) {
	            System.out.println("Mention : Assez bien");
	        } else if (moyenne >= 10) {
	            System.out.println("Mention : Passable");
	        } else {
	            System.out.println("Mention : Insuffisant");
	        }
	    
	}

}
