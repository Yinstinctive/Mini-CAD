package shapes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Shape extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected float lineSize=1.0f;
	protected Color color=Color.black;
	
	public abstract void draw(Graphics g);
	public abstract String getDesc();
	public abstract boolean contains(int a,int b);
	public abstract void increaseLineSize();
	public abstract void decreaseLineSize();
	public abstract void increaseShapeSize();
	public abstract void decreaseShapeSize();
	public void changeColor(String s) {
		switch(s) 
		{
		case "black":
			color=Color.black;
			break;
		case "grey":
			color=Color.lightGray;
			break;
		case "turquoise":
			color=Color.blue;
			break;
		case "indigo":
			color=Color.cyan;
			break;
		case "red":
			color=Color.red;
			break;
		case "pink":
			color=Color.pink;
			break;
		case "orange":
			color=Color.orange;
			break;
		case "gold":
			color=new Color(255,215,0);
			break;
		case "yellow":
			color=Color.yellow;
			break;
		case "light yellow":
			color=new Color(255,255,204);
			break;
		case "green":
			color=Color.green;
			break;
		case "lime":
			color=new Color(173,255,47);
			break;
		}
	}
}



