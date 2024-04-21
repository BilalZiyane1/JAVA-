package newone1;

import java.awt.*;


class Line extends Figure{
	private int x1, y1, x2, y2;
	
	public Line(){}
	
	public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setExtremity1(int x, int y) {
        x1 = x;
        y1 = y;
    }

    public void setExtremity2(int x, int y) {
        x2 = x;
        y2 = y;
    }
	
	@Override
	
	public void draw(Graphics g)
	{
		g.drawLine(x1,y1,x2,y2);
		g.setColor(this.getColor());
	}
	
	@Override
	public boolean contains(int x,int y)
	{
		return (x2 - x1) / (y2 - y1) == (x - x1) / (y - y1) && x < x1 && x > x1;
	}
}
