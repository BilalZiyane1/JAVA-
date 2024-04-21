package newone1;

import java.awt.*;

class TestGraphic extends Frame{
	
		Point rect_point = new Point(150,220);
		
		Circle circle1 = new Circle(200,200,100);
		Circle circle2 = new Circle(160,150,20);
		Circle circle3 = new Circle(240,150,20);
		Rectangle rectangle = new Rectangle(rect_point,100,40);
	TestGraphic()
	{
		this.setVisible(true);
		this.setSize(400,400);
		this.setLayout(null);
		this.setTitle("Test Graphic");
		//Pour fermer la fenetre d'execution
		/*this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing( java.awt.event.WindowEvent e ) {
                dispose() ;
                System.exit(0);
              }
              });*/
	}
	public void paint(Graphics g)
	{
		rectangle.draw(g);
		circle1.draw(g);
		circle2.draw(g);
		circle3.draw(g);
	}
	public static void main(String[] args) {
		TestGraphic DrawComponent = new TestGraphic();
	}
}

