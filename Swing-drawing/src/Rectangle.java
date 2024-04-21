import java.awt.*;
class Rectangle extends Figure {
    private Point position;
    private int width, height;
    public Rectangle() {}
    public Rectangle(Point pos, int width, int height) {
        position = pos;
        this.width = width;
        this.height = height;
    }
	public void setLocation(int x, int y) {
        position = new Point(x, y);
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
		  int x1 = position.x; 
	        int y1 = position.y;
	        int x2 = x1 + width; 
	        int y2 = y1 + height;  
	        return (x >= x1 && x <= x2 && y >= y1 && y <= y2);
	}
}
