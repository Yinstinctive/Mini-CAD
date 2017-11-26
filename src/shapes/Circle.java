package shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circle extends Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String description="Circle";
	private int x;
	private int y;
	private int radius;
	
	
	public Circle(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(lineSize));
		g2.setColor(color);
		g2.drawOval(x-radius, y-radius, radius*2, radius*2);
	}
	
	public void updateCircle(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	public String getDesc() {
		return description;
	}
	
	public void moveCircle(int horizontal,int vertical) {
		this.updateCircle(x+horizontal, y+vertical, radius);
	}
	
	@Override
	public boolean contains(int a, int b) {
		if(Math.sqrt((x-a)*(x-a)+(y-b)*(y-b))<=radius)
			return true;
		else
			return false;
	}
	@Override
	public void increaseLineSize() {
		this.lineSize+=0.2;
	}
	@Override
	public void decreaseLineSize() {
		if(this.lineSize>=1)
		this.lineSize-=0.2;
	}
	@Override
	public void increaseShapeSize() {
		radius++;
		
	}
	@Override
	public void decreaseShapeSize() {
		if(radius>=1)
		radius--;
	}
}
