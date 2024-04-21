package myfirstjavaprograme;
import java.awt.*;

public class Ex1_tp2 {
	abstract class Figure {
	    private Color color;
	    private String name;
	    public Figure() {
	        this.color = Color.BLACK; // Default color
	    }

	    public void setColor(Color c) {
	        this.color = c;
	    }

	    public String getName() {
	        return this.name;
	    }

	    public Color getColor() {
	        return this.color;
	    }

	    public abstract void draw(Graphics g);

	    public abstract boolean contains(int x, int y);
	}

	// Class Line
	class Line extends Figure {
	    private int x1, y1, x2, y2;

	    public Line() {
	        this(0, 0, 0, 0);
	    }

	    public Line(int x1, int y1, int x2, int y2) {
	        this.x1 = x1;
	        this.y1 = y1;
	        this.x2 = x2;
	        this.y2 = y2;
	    }

	    public void setExtremity1(int x, int y) {
	        this.x1 = x;
	        this.y1 = y;
	    }

	    public void setExtremity2(int x, int y) {
	        this.x2 = x;
	        this.y2 = y;
	    }

	    @Override
	    public void draw(Graphics g) {
	        g.setColor(getColor());
	        g.drawLine(x1, y1, x2, y2);
	    }

	    @Override
	    public boolean contains(int x, int y) {
	       
	        double distance = Math.abs((y2 - y1) * x - (x2 - x1) * y + x2 * y1 - y2 * x1) /
	                Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
	        return distance < 3; 
	    }
	}

	// Class Rectangle
	class Rectangle extends Figure {
	    private Point pos;
	    private int width, height;

	    public Rectangle() {
	        this(new Point(0, 0), 0, 0);
	    }

	    public Rectangle(Point pos, int w, int h) {
	        this.pos = pos;
	        this.width = w;
	        this.height = h;
	    }

	    public void setLocation(int x, int y) {
	        this.pos.setLocation(x, y);
	    }

	    public void setDimension(int w, int h) {
	        this.width = w;
	        this.height = h;
	    }

	    public Point getLocation() {
	        return pos;
	    }

	    public int getWidth() {
	        return width;
	    }

	    public int getHeight() {
	        return height;
	    }

	    @Override
	    public void draw(Graphics g) {
	        g.setColor(getColor());
	        g.drawRect(pos.x, pos.y, width, height);
	    }

	    @Override
	    public boolean contains(int x, int y) {
	        int x1 = pos.x; // x-coordinate of the top-left corner of the rectangle
	        int y1 = pos.y; // y-coordinate of the top-left corner of the rectangle
	        int x2 = x1 + width; // x-coordinate of the bottom-right corner of the rectangle
	        int y2 = y1 + height; // y-coordinate of the bottom-right corner of the rectangle

	        // Check if the point (x, y) is within the rectangle's boundaries
	        return (x >= x1 && x <= x2 && y >= y1 && y <= y2);
	    }
	}

	// Class Circle
	class Circle extends Figure {
	    private Point center;
	    private int radius;

	    public Circle() {
	        this(new Point(0, 0), 0);
	    }

	    public Circle(int x, int y, int r) {
	        this(new Point(x, y), r);
	    }

	    public Circle(Point center, int r) {
	        this.center = center;
	        this.radius = r;
	    }

	    public void setLocation(int x, int y) {
	        this.center.setLocation(x, y);
	    }

	    public void setRadius(int r) {
	        this.radius = r;
	    }

	    public Point getLocation() {
	        return center;
	    }

	    public int getRadius() {
	        return radius;
	    }

	    @Override
	    public void draw(Graphics g) {
	        g.setColor(getColor());
	        int diameter = radius * 2;
	        g.drawOval(center.x - radius, center.y - radius, diameter, diameter);
	    }

	    @Override
	    public boolean contains(int x, int y) {
	        // Calculer la distance entre le point (x, y) et le centre du cercle
	        double distance = Math.sqrt(Math.pow(x - center.x, 2) + Math.pow(y - center.y, 2));

	        // Vérifier si la distance est inférieure ou égale au rayon du cercle
	        return distance <= radius;
	    }
	}

	public static class MainTest extends Frame {
	    private static final long serialVersionUID = 1L;

	    public MainTest() {
	        setSize(400, 400);
	        setVisible(true);
	    }

	    @Override
	    public void paint(Graphics g) {
	        // Création d'une instance de la classe Ex1_tp2
	        Ex1_tp2 ex1_tp2 = new Ex1_tp2();

	        // Création d'un cercle avec un centre (x, y) et un rayon
	        Ex1_tp2.Circle circle = ex1_tp2.new Circle(200, 200, 100);

	        // Point à vérifier s'il appartient au cercle
	        int pointToCheckX = 250;
	        int pointToCheckY = 250;

	        // Dessiner le cercle
	        g.setColor(Color.BLACK);
	        circle.draw(g);

	        // Vérifier si le point (pointToCheckX, pointToCheckY) appartient au cercle
	        if (circle.contains(pointToCheckX, pointToCheckY)) {
	            g.setColor(Color.RED);
	            g.fillOval(pointToCheckX - 5, pointToCheckY - 5, 10, 10);
	        }
	    }

	    public static void main(String[] args) {
	        MainTest testFrame = new MainTest();
	    }
	}
}




