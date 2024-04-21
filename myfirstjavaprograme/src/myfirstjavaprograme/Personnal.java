package myfirstjavaprograme;

import java.util.Scanner;

public class Personnal {
	public static  int poly (int k) {
		int res = k*k;
		res = res + 2*k +15;
		return res;
	}
	
	public static void main(String[] args) {
		/*System.out.println(args.length);
		double n=100;
		int  m = (int)n;
		int h = (int)5.5;
		System.out.println(m);
		System.out.println(h);*/
		int resultat =0;
		resultat= poly(17);
		System.out.println(resultat);
		String t = "helpolm";
		String q = t.substring(3,4);
		System.out.println(q);
		int r = t.indexOf('p'  +1);
		System.out.println(r);
		
		
		}
}
