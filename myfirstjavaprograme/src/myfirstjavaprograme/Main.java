package myfirstjavaprograme;
public class Main{

    public static class Voiture {
        private boolean estDemarree;
        private int vitesseMax;

        public int deQuelVitesse() {
            return vitesseMax;
        }

        public void demarrer() {
            estDemarree = true;
        }
    }

    public static void main(String[] args) {
        System.out.println("bilal is my name");
        int a = 10;
        System.out.println(a);

        Voiture mavoiture = new Voiture();
        mavoiture.demarrer();
        mavoiture.vitesseMax = 100;
        System.out.println(mavoiture.deQuelVitesse());
        System.out.print(mavoiture.getClass());
    }
}

