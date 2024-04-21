import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestGraphic extends Frame {

    private Point rect_point = new Point(150, 220);
    private Circle circle1 = new Circle(200, 200, 100);
    private Circle circle2 = new Circle(160, 150, 20);
    private Circle circle3 = new Circle(240, 150, 20);
    private Rectangle rectangle = new Rectangle(rect_point, 100, 40);
    private Figure selectedFigure = null;

    public TestGraphic() {
        this.setVisible(true);
        this.setSize(400, 400);
        this.setTitle("Test Graphic");

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Figure clickedFigure = getClickedFigure(e.getX(), e.getY());
                
                if (clickedFigure != null) {
                    changeColor(clickedFigure);
                }
            }
        });


        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

	public void paint(Graphics g)
	{
		rectangle.draw(g);
		circle1.draw(g);
		circle2.draw(g);
		circle3.draw(g);
	}
	
	private void changeColor(Figure f) {
	    Color[] colorList = {Color.black, Color.blue, Color.cyan, Color.red, Color.green,
	    		Color.magenta, Color.orange, Color.yellow};
	    Color currentColor = f.getColor();
	    int currentIndex = 0;

	    for (int i = 0; i < colorList.length; i++) {
	        if (colorList[i].equals(currentColor)) {
	            currentIndex = i;
	            break;
	        }
	    }

	    int nextIndex = (currentIndex + 1) % colorList.length;

	    f.setColor(colorList[nextIndex]);
	    repaint();
	}

	
	private Figure getClickedFigure(int x, int y) {
        if (rectangle.contains(x, y)) {
            return rectangle;
        } else if (circle1.contains(x, y)) {
            return circle1;
        } else if (circle2.contains(x, y)) {
            return circle2;
        } else if (circle3.contains(x, y)) {
            return circle3;
        } else {
            return null;
        }
    }
	
	public static void main(String[] args) {
		TestGraphic DrawComponent = new TestGraphic();
		
	}
}