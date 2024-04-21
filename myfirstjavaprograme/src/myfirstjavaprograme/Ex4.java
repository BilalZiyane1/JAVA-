package myfirstjavaprograme;
import java.util.Scanner;
public class Ex4 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("enter votre chaine de caractere :");
		String input = scan.nextLine();
		char c = input.charAt(4);
		System.out.println(c);
		int count = 0;
		for(int i =0 ; i< input.length();i++) {
			
			if(input.charAt(i)==c) {
				count++;
			}
		}
		System.out.println("the number of occurence of the charactere is:" + count);
		scan.close();
	}
}
