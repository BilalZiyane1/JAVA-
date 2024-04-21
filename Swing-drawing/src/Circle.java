import java.awt.*;
class Circle extends Figure{
	private int x,y,r;
	public Circle() {}
	public Circle(int x,int y,int r)
	{
		this.x = x;
		this.y = y;
		this.r = r;
	}
	public void setLocation(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	public int getXrayon() {
		return x;
	}
	public int getYrayon() {
		return y;
	}
	public void setRayon(int r)
	{
		this.r = r;
	}
	public int getRayon() {
		return r;
	}
@Override
	public void draw(Graphics g)
	{
		g.drawOval(x-r,y-r,r*2,r*2);
		g.setColor(getColor());
	}	
@Override
	public boolean contains(int px,int py)
	{
	return ((px - x) * (px - x) + (py - y) * (py - y) <= r * r);
	}
}
