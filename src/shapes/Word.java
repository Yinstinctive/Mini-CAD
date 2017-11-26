package shapes;

import java.awt.Font;
import java.awt.Graphics;

public class Word extends Shape{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private String str;
	private static final String description="Word";
	private int size=12;
	private Font font;
	
	public Word(int x,int y,String str) {
		this.x=x;
		this.y=y;
		this.str=str;
	}
	
	@Override
	public void draw(Graphics g) {
		font=new Font("Helvetica", Font.PLAIN,size);
		g.setFont(font);
		g.setColor(color);
		g.drawString(str, x, y);
	}
	
	public void updateWord(int x,int y, int s)
	{
		this.x=x;
		this.y=y;
		if(s>5)
			this.size=s;
		else
			this.size=5;
	}
	
	public String getDesc() {
		return description;
	}
	
	public int getFontSize() {
		return size;
	}

	@Override
	public boolean contains(int a, int b) {
		if((Math.sqrt((x-a)*(x-a)+(y-b)*(y-b))<=size*str.length())&&(a>=x)&&(b<=y)&&(b>=y-size))
			return true;
		else
			return false;
	}
	
	public void moveWord(int horizontal,int vertical, int s) {
		this.updateWord(x+horizontal, y+vertical,size);
	}

	@Override
	public void increaseLineSize() {

	}

	@Override
	public void decreaseLineSize() {

	}

	@Override
	public void increaseShapeSize() {
		size++;
		
	}

	@Override
	public void decreaseShapeSize() {
		if(size>=1)
		size--;
	}
}
