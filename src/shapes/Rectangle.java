package shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	public int width;
	public int height;
	private static final String description="Rectangle";
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(lineSize));
		g2.setColor(color);
		g2.drawRect(x, y, width, height);
	}
	
	public void updateRectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void moveRectangle(int horizontal,int vertical) {
		this.updateRectangle(x+horizontal, y+vertical, width, height);
	}
	
	public String getDesc() {
		return description;
	}

	@Override
	public boolean contains(int a, int b) {
		if((a>=x)&&(a<=(x+width))&&(b>=y)&&(b<=y+height))
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
		float ratio=(height)/(width*1.0f);
		width++;
		height=Math.round(ratio*width);
	}

	@Override
	public void decreaseShapeSize() {
		float ratio=(height)/(width*1.0f);
		if((width>2)&&(height>Math.round(2*ratio)))
		{
			width--;
			height=Math.round(ratio*width);
		}
	}

}
