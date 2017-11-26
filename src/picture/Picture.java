package picture;

import javax.swing.JPanel;

import shapes.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Picture extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> listShape=new ArrayList<>();
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for ( Shape s : listShape )
		{
			s.draw(g);
		}
	}
	
	public void add(Shape s)
	{
		listShape.add(s);
	}
	
	public void remove(Shape s)
	{
		listShape.remove(s);
	}
	
	public Picture(int width, int height)
	{
		this.setSize(width, height);
		this.setBackground(Color.white);
		this.setVisible(true);
	}
	
	public ArrayList<Shape> getListShape(){
		return listShape;
	}
}
