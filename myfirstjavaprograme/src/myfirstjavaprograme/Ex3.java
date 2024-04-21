package myfirstjavaprograme;

class Mouse{
    public int price;
    private int size;

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }
}

class Monitor{
    private int dimention;
    private String resolution;
}

class Mother{
    private String ram;
    private String storage;
    private String CPU;
    public String getRam() {
    	return ram;
    }
}

class CentralUnit{
    private Mother mother;

    public CentralUnit(){
        mother = new Mother();
        
        
    }
}

class Computer {
    private Mouse mouse;
    private Monitor monitor;
    private CentralUnit centralunit;

    public Computer(int prix) {
        mouse = new Mouse();
        mouse.price = prix;
        monitor = new Monitor();
        centralunit = new CentralUnit();
    }

    public Mouse getMouse() {
        return mouse;
    }
}

public class Ex3 {
    public static void main(String[] args) {
        Computer cmp = new Computer(120);
        System.out.println(cmp.getMouse().getPrice());
    }
}

