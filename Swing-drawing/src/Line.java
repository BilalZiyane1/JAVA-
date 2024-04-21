import java.awt.*;
class Line extends Figure{
	private int x1, y1, x2, y2;
	public Line(){}
	public Line(int x_1, int y_1, int x_2, int y_2) {
        x1 = x_1;
        y1 = y_1;
        x2 = x_2;
        y2 = y_2;
    }
    public void setExtremity1(int x, int y) {
        x1 = x;
        y1 = y;
    }
    public int getX1() {
    	return x1;
    }
    public int getX2() {
    	return x2;
    }public int gety1() {
    	return y1;
    }public int gety12() {
    	return y2;
    }
    public void setExtremity2(int x, int y) {
        x2 = x;
        y2 = y;
    }
	@Override
	public void draw(Graphics g)
	{
		g.setColor(this.getColor());
		g.drawLine(x1,y1,x2,y2);
	}
	@Override
	public boolean contains(int x,int y)
	{
		double distance = Math.abs((y2 - y1) * x - (x2 - x1) * y + x2 * y1 - y2 * x1) /
                Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
        return distance < 3; 
	}
}
