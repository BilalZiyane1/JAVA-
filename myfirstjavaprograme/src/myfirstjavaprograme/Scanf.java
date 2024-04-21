package myfirstjavaprograme;
import java.util.Scanner;
import java.awt.*;
public class Scanf {
	
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter a line of text: ");
	        String input = scanner.nextLine(); // Read a line of text

	        System.out.println("You entered: " + input);

	        scanner.close(); // Close the scanner when you're done
	        
	    }
	

}
