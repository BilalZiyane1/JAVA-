package myfirstjavaprograme;

public class Ex1 {
	public static class Voiture{
		private String matricule;
		private String modele;
		private int vitesse;
		public Voiture(String matricule1, String modele1 , int vitesse1) {
			matricule = matricule1;
			modele=modele1;
			vitesse = vitesse1;
		}
		public String getMatricule() {
			return matricule;
		}
		public String getModele(){
			return modele;
		}
		public int getVitesse() {
			return vitesse;
		}
		public void setMatricule(String matri) {
			matricule=matri;
		}
		public void setModele(String mod){
			modele=mod;
		}
		public void setVitesse(int vit) {
			vitesse = vit;
		}
	}
	public static void main(String[] args) {
		Voiture voiture1 = new Voiture("A122344","toyota",120);
		Voiture voiture2 = new Voiture("B902374","BMW",190);
		System.out.println(voiture1.getMatricule());
		voiture1.vitesse= 190;
		System.out.println(voiture1.vitesse);

		
	}
}
