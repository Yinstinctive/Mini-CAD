package shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends Shape {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	private static final String description="Line";
	
	public Line(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
	}
	
	public void updateLine(int x1, int y1, int x2, int y2) 
	{
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(lineSize));
		g2.setColor(color);
		g2.drawLine(x1, y1, x2, y2);
	}
	
	public String getDesc() {
		return description;
	}

	public void moveLine(int horizontal,int vertical) {
		this.updateLine(x1+horizontal, y1+vertical, x2+horizontal, y2+vertical);
	}
	
	@Override
	public boolean contains(int m, int n) {
		double k,b;
		k=(y2-y1)/((x2-x1)*1.0);
		b=y1-k*x1;
		int minX=Math.min(x1, x2);
		int maxX=Math.max(x1, x2);
		int minY=Math.min(y1, y2);
		int maxY=Math.max(y1, y2);
		if((k*m+b-n<=this.lineSize*3)&&(m<=maxX)&&(m>=minX)&&(n<=maxY)&&(n>=minY))
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
		float k,b;
		k=(y2-y1)/((x2-x1)*1.0f);
		b=y1-k*x1;
		x1--;
		y1=(Math.round(k*x1*100+b*100))/100;
		x2++;
		y2=(Math.round(k*x2*100+b*100))/100;
	}

	@Override
	public void decreaseShapeSize() {
		if(Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2))>5)
		{
			float k,b;
			k=(y2-y1)/((x2-x1)*1.0f);
			b=y1-k*x1;
			x1++;
			y1=(Math.round(k*x1*100+b*100))/100;
			x2--;
			y2=(Math.round(k*x2*100+b*100))/100;
		}
	}

}
