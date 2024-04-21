package newone1;

import java.awt.*;

class Rectangle extends Figure {

    private Point position;
    private int width, height;

    public Rectangle() {}

    public Rectangle(Point pos, int w, int h) {
        position = pos;
        width = w;
        height = h;
    }
	
	public void setLocation(int x1, int y1) {
        position = new Point(x1, y1);
    }
	
	public void setDimension(int w, int h) {
        width = w;
        height = h;
    }

    public Point getLocation() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    @Override
    public void draw(Graphics g)
	{
		g.setColor(getColor());
		g.drawRect(position.x, position.y, width, height);
	}
	
	@Override
	public boolean contains(int x,int y)
	{
		return (x >= position.x) && (x <= (position.x + width)) && (y >= position.y) && (y <= (position.y + height));
	}
}
