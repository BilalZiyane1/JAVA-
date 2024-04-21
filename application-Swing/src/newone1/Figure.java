package newone1;
import java.awt.*;

abstract class Figure {
	
	private Color color;
	private String name;
	
	public Figure() {}
	
	public void setColor(Color c)
	{
		color = c;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public String getName()
	{
		return name;
	}
	
	abstract void draw(Graphics g);
	
	abstract boolean contains(int x ,int y);
}
