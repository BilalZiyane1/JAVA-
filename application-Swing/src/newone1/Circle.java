package newone1;
import java.awt.*;
class Circle extends Figure{

	private int x1,y1,r;
	
	public Circle() {}
	
	public Circle(int x1,int y1,int r)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.r = r;
	}
	
	public void setLocation(int x1,int y1)
	{
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public void setRayon(int r)
	{
		this.r = r;
	}
	
@Override
	public void draw(Graphics g)
	{
		g.drawOval(x1-r,y1-r,r*2,r*2);
		g.setColor(getColor());
	}
		
@Override
	public boolean contains(int x,int y)
	{
		return ((x-x1) * (x-x1) + (y-y1) * (y-y1) <= r*r);
	}
}

