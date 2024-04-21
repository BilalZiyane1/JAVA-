package myfirstjavaprograme;

public class ExceptionHandling {
	    public static void verifierVal(int num) {
	        if (num < 0) {
	            throw new IllegalArgumentException("La valeur ne peut pas être négative");
	        }
	        System.out.println("La valeur est : " + num);
	    }

	    public static void main(String[] args) {
	        try {
	            verifierVal(4);  
	            verifierVal(-1);  
	        } catch (IllegalArgumentException e) {
	            System.out.println("Exception : " + e.getMessage());
	        }
	    }

	
}
