package myfirstjavaprograme;

public class Ex2 {
	public static class FormeGeo{
		private String nom;
		private String couleur;
		public FormeGeo(String no,String co) {
			nom=no;
			couleur=co;
		}
	}
	public static class Cercle extends FormeGeo{
		private int x;
		private int y;
		private int rayon;
		public Cercle(String no,String co,int x,int y,int rayon) {
			super(no,co);
			this.x =x;
			this.y = y;
			this.rayon = rayon;
		}
	}
}
