package buttons;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;



public class Buttons extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final JPanel drawButtonBoard=new JPanel();
	
	private HashMap<String,JButton> listColorButton=new HashMap<>();
	private HashMap<String,JButton> listDrawButton=new HashMap<>();
	
	private JButton line=new JButton("Line");
	private JButton circle=new JButton("Circle");
	private JButton rectangle=new JButton("Rectangle");
	private JButton word=new JButton("Word");
	private JButton save=new JButton("Save");
	private JButton open=new JButton("Open");
	
	private static final JPanel colorBoard=new JPanel();
	private JButton black=new JButton("black");
	private JButton grey=new JButton("grey");
	private JButton turquoise=new JButton("turquoise");
	private JButton indigo=new JButton("indigo");
	private JButton red=new JButton("red");
	private JButton pink=new JButton("pink");
	private JButton orange=new JButton("orange");
	private JButton gold=new JButton("gold");
	private JButton yellow=new JButton("yellow");
	private JButton lightYellow=new JButton("light yellow");
	private JButton green=new JButton("green");
	private JButton lime=new JButton("lime");
	
	public Buttons() {
		GridLayout drawButtonLayout =new GridLayout(6,1);
		drawButtonBoard.setLayout(drawButtonLayout);
		initialDrawButton();
		addDrawButton();
		
		GridLayout colorBoardLayout=new GridLayout(4,3);
		colorBoard.setLayout(colorBoardLayout);
		initialColorButton();
		addColordButton();
		
		GridLayout overralLayout=new GridLayout(2,1);
		this.setLayout(overralLayout);
		this.add(drawButtonBoard);
		this.add(colorBoard);
	}	
	
	public void initialDrawButton() {
		open.setIcon(new ImageIcon(getClass().getResource("openbutton.png")));
		open.setContentAreaFilled(false);
		
		save.setIcon(new ImageIcon(getClass().getResource("savebutton.png")));
		save.setContentAreaFilled(false);
		
		circle.setIcon(new ImageIcon(getClass().getResource("circlebutton.png")));
		circle.setContentAreaFilled(false);
		
		rectangle.setIcon(new ImageIcon(getClass().getResource("rectanglebutton.png")));
		rectangle.setContentAreaFilled(false);
		
		line.setIcon(new ImageIcon(getClass().getResource("linebutton.png")));
		line.setContentAreaFilled(false);
		
		word.setIcon(new ImageIcon(getClass().getResource("wordbutton.png")));
		word.setContentAreaFilled(false);
	}
	
	public void addDrawButton() {
		listDrawButton.put(open.getText(),open);
		listDrawButton.put(save.getText(),save);
		listDrawButton.put(circle.getText(),circle);
		listDrawButton.put(rectangle.getText(),rectangle);
		listDrawButton.put(line.getText(),line);
		listDrawButton.put(word.getText(),word);
		
		drawButtonBoard.add(open);
		drawButtonBoard.add(save);
		drawButtonBoard.add(circle);
		drawButtonBoard.add(rectangle);
		drawButtonBoard.add(line);
		drawButtonBoard.add(word);
	}
	
	public void initialColorButton() {
		black.setBackground(Color.BLACK);
		black.setForeground(Color.BLACK);
		
		grey.setBackground(Color.lightGray);
		grey.setForeground(Color.lightGray);
		
		turquoise.setBackground(Color.blue);
		turquoise.setForeground(Color.blue);
		
		indigo.setBackground(Color.cyan);
		indigo.setForeground(Color.cyan);
		
		red.setBackground(Color.red);
		red.setForeground(Color.red);
		
		pink.setBackground(Color.pink);
		pink.setForeground(Color.pink);
		
		orange.setBackground(Color.orange);
		orange.setForeground(Color.orange);
		
		gold.setBackground(new Color(255,215,0));
		gold.setForeground(new Color(255,215,0));
		
		yellow.setBackground(Color.yellow);
		yellow.setForeground(Color.yellow);
		
		lightYellow.setBackground(new Color(255,255,204));
		lightYellow.setForeground(new Color(255,255,204));
		
		green.setBackground(Color.green);
		green.setForeground(Color.green);
		
		lime.setBackground(new Color(173,255,47));
		lime.setForeground(new Color(173,255,47));
	}
	
	public void addColordButton() {
		listColorButton.put("black", black);
		listColorButton.put("grey", grey);
		listColorButton.put("turquoise", turquoise);
		listColorButton.put("indigo", indigo);
		listColorButton.put("red", red);
		listColorButton.put("pink", pink);
		listColorButton.put("orange", orange);
		listColorButton.put("gold", gold);
		listColorButton.put("yellow", yellow);
		listColorButton.put("light yellow", lightYellow);
		listColorButton.put("green", green);
		listColorButton.put("lime", lime);
		
		colorBoard.add(black);
		colorBoard.add(grey);
		colorBoard.add(turquoise);
		colorBoard.add(indigo);
		colorBoard.add(red);
		colorBoard.add(pink);
		colorBoard.add(orange);
		colorBoard.add(gold);
		colorBoard.add(yellow);
		colorBoard.add(lightYellow);
		colorBoard.add(green);
		colorBoard.add(lime);
	}
	
	public HashMap<String,JButton > getColorButton()
	{
		return listColorButton;
	}
	
	public HashMap<String,JButton> getDrawButton(){
		return listDrawButton;
	}
}
