package myfirstjavaprograme;

import java.util.Scanner;
public class Exer13 {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Saisissez un texte (terminez par un point '.') :");
        String texte = scanner.nextLine();

        int compteur = 0;

        for (int i = 0; i < texte.length(); i++) {
            char caractere = texte.charAt(i);
            if (caractere == 'a') {
                compteur++;
            }
        }

        System.out.println("Le nombre de caractères 'a' dans le texte est : " + compteur);
    }
}
