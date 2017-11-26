package field;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import buttons.Buttons;

import picture.Picture;

import shapes.Circle;
import shapes.Line;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Word;

public class View extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Picture pic=new Picture(1000,800);

	private MouseHandler mouse = new MouseHandler();
	private Selector mouseSelector=new Selector();;
	private Shape shape;
	private String usingShape;
	private String text;
	private KeyboardHandler keyboard = new KeyboardHandler();
	private static final Buttons buttons=new Buttons();
	
	public View(int width,int height) {
		initial();
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setTitle("Mini CAD");
		this.setVisible(true);
	}
	
	public void initial() {
		buttons.getDrawButton().get("Circle").addActionListener(new DrawButtonHandler());
		buttons.getDrawButton().get("Line").addActionListener(new DrawButtonHandler());
		buttons.getDrawButton().get("Rectangle").addActionListener(new DrawButtonHandler());
		buttons.getDrawButton().get("Word").addActionListener(new DrawButtonHandler());
		buttons.getDrawButton().get("Open").addActionListener(new OpenFile());
		buttons.getDrawButton().get("Save").addActionListener(new SaveFile());
		
		for(Iterator<String> iterator=buttons.getColorButton().keySet().iterator();iterator.hasNext();) {
		 	String s=iterator.next();
			buttons.getColorButton().get(s).addActionListener(new ColorChange());
		}
		
		this.getContentPane().add(buttons,BorderLayout.EAST);
		this.getContentPane().add(pic,BorderLayout.CENTER);
	}
	
	private void activateKeyboard() {
		pic.addKeyListener(keyboard);
		pic.requestFocus();
	}
	
	private void activateSelector() {
		pic.addMouseListener(mouseSelector);
		pic.addMouseMotionListener(mouseSelector);
		pic.requestFocus();
	}
	
	private void closeSelector() {
		pic.removeMouseListener(mouseSelector);
		pic.removeMouseMotionListener(mouseSelector);
	}
	
	private void activateMouseListener() {
		pic.addMouseListener(mouse);
		pic.addMouseMotionListener(mouse); 
		pic.requestFocus();
	}
	
	private void closeMouseListener() {
		pic.removeMouseListener(mouse);
		pic.removeMouseMotionListener(mouse);
	}
	
	private class KeyboardHandler implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			char key=e.getKeyChar();
			if(key==',')
			{
				shape.decreaseLineSize();
			}
			else if(key=='.')
			{
				shape.increaseLineSize();
			}
			else if(key=='+')
			{
				shape.increaseShapeSize();
			}
			else if(key=='-') {
				shape.decreaseShapeSize();
			}
			else if(key=='r')
			{
				pic.getListShape().remove(shape);
			}
			pic.repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
	}
	
	private class Selector implements MouseMotionListener, MouseListener{
		private int[] x=new int[2];
		private int[] y=new int[2];
		boolean shapeSelected=false;
		int dx,dy;
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			for(int i=0;i<pic.getListShape().size();i++)
			{	
				if(pic.getListShape().get(i).contains(e.getX(),e.getY()))
				{
					shape=pic.getListShape().get(i);
					x[0]=e.getX();
					y[0]=e.getY();
					shapeSelected=true;
					activateKeyboard();
					break;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			shapeSelected=false;
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			if (shapeSelected==true) 
			{
				x[1]=e.getX();
				y[1]=e.getY();
				dx=x[1]-x[0];
				dy=y[1]-y[0];
				if(shape.getDesc().equals("Circle"))
				{
					((Circle)shape).moveCircle(dx, dy);;
				}
				else if(shape.getDesc().equals("Line"))
				{
					((Line)shape).moveLine(dx,dy);
				}
				else if(shape.getDesc().equals("Rectangle"))
				{
					((Rectangle)shape).moveRectangle(dx, dy);;
				}
				else if(shape.getDesc().equals("Word"))
				{
					((Word)shape).moveWord(dx, dy,((Word)shape).getFontSize());
				}
				x[0]=x[1];
				y[0]=y[1];
				pic.repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
		
	}
	
	private class MouseHandler implements MouseMotionListener, MouseListener{
		private int[] x=new int[2];
		private int[] y=new int[2];
		@Override
		public void mouseDragged(MouseEvent e) {
			x[1]=e.getX();
			y[1]=e.getY();
			if(usingShape.equals("Line"))
			{
				((Line)shape).updateLine(x[0], y[0], x[1], y[1]);
			}
			else if(usingShape.equals("Circle"))
			{
				int r=(int)((Math.sqrt((x[0]-x[1])*(x[0]-x[1])+(y[0]-y[1])*(y[0]-y[1])))/2.0);
				((Circle)shape).updateCircle((x[0]+x[1])/2, (y[0]+y[1])/2, r);
			}
			else if(usingShape.equals("Rectangle")) 
			{
				((Rectangle)shape).updateRectangle(x[0], y[0], Math.abs(x[0]-x[1]), Math.abs(y[0]-y[1]));
			}
			else if(usingShape.equals("Word")) 
			{
				((Word)shape).updateWord(x[0], y[0],(int)Math.sqrt((x[0]-x[1])*(x[0]-x[1])+(y[0]-y[1])*(y[0]-y[1])));
			}
			pic.repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			                   
		}

		@Override
		public void mouseEntered(MouseEvent e) {

			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			x[0]=e.getX();
			y[0]=e.getY();
			if(usingShape.equals("Circle"))
				shape=new Circle(0,0,0);
			else if (usingShape.equals("Line"))
				shape=new Line(1,1,2,2);
			else if (usingShape.equals("Rectangle"))
				shape=new Rectangle(0,0,0,0);
			else if (usingShape.equals("Word"))
				shape=new Word(0,0,text);
			pic.add(shape);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			closeMouseListener();
			activateSelector();
		}
		
	}
	
	private class DrawButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			usingShape=((JButton)(e.getSource())).getText();
			closeSelector();
			activateMouseListener();
			if (usingShape.equals("Word"))
			text=JOptionPane.showInputDialog(pic, "输入文字","输入对话框", JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	
	public class ColorChange implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				if (shape!=null)
				{
				shape.changeColor(((JButton)(e.getSource())).getText());
				pic.repaint();
				}
		}
		
	}
	
	public class OpenFile implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {  
	            ObjectInputStream in = new ObjectInputStream(new FileInputStream(  
	            		"example.CAD"));
	            ArrayList<Shape> temp=new ArrayList<>();
	            pic.getListShape().clear();
	            temp=(ArrayList<Shape>)in.readObject();
	            for(Iterator<Shape> iterator = temp.iterator();iterator.hasNext();)
	            {
	            	pic.getListShape().add((Shape)iterator.next());
	            }
	            pic.repaint();
	            in.close();  
	        } catch (FileNotFoundException e1) {  
	            e1.printStackTrace();  
	        } catch (IOException e1) {  
	            e1.printStackTrace();  
	        } catch (ClassNotFoundException e1) {  
	            e1.printStackTrace();  
	        }
		}
	}
	
	public class SaveFile implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {  
	            ObjectOutputStream out = new ObjectOutputStream(  
	                    new FileOutputStream("example.CAD"));
	            out.writeObject(pic.getListShape());
	            out.close();  
	        } catch (FileNotFoundException e1) {  
	            e1.printStackTrace();  
	        } catch (IOException e1) {  
	            e1.printStackTrace();  
	        }  
		}
	}
	
	public static void main(String args[]) {
		View theView=new View(1440,810);
	}
}
