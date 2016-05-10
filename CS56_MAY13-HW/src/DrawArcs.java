import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.effect.ColorAdjust;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;

public class DrawArcs extends JFrame {
	public DrawArcs() 
	{
		setTitle("DrawArcs");
		add(new ArcsPanel());
	}

	/** Main method */
	public static void main(String[] args) {
		ArcsPanel ap = new ArcsPanel();
		DrawArcs frame = new DrawArcs();
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(250, 300);
		frame.setVisible(true);
		//ap.spinFan(30,g);
	}
}

// The class for drawing arcs on a panel
class ArcsPanel extends JPanel 
{
	Color[] colorArray = {Color.RED,Color.ORANGE, Color.YELLOW, Color.GREEN,Color.BLUE,Color.cyan,Color.PINK};

	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;
		int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);
		int x = xCenter - radius;
		int y = yCenter - radius;
		//g.setColor(Color.GREEN);
		g.fillArc(x, y, 2 * radius, 2 * radius, 0  , 30);
		//g.setColor(Color.BLUE);
		g.fillArc(x, y, 2 * radius, 2 * radius, 90  , 30);
		//g.setColor(Color.RED);
		g.fillArc(x, y, 2 * radius, 2 * radius, 180  , 30);
		//g.setColor(Color.YELLOW);
		g.fillArc(x, y, 2 * radius, 2 * radius, 270  , 30);
	}

	public synchronized void spinFan(Graphics g,int rotate, Color fanColor)
	{
		super.paintComponent(g);

		int ran =(int)(Math.random() * (6 + 1));

		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;
		int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);
		int x = xCenter - radius;
		int y = yCenter - radius;

		g.setColor(fanColor);
		g.fillArc(x, y, 2 * radius, 2 * radius, 0 + rotate, 30);
		g.fillArc(x, y, 2 * radius, 2 * radius, 90 + rotate, 30);
		g.fillArc(x, y, 2 * radius, 2 * radius, 180 + rotate, 30);
		g.fillArc(x, y, 2 * radius, 2 * radius, 270 + rotate, 30);
	}
}

