import java.awt.*;

import javax.security.auth.x500.X500Principal;
import javax.swing.*;
import java.awt.event.*;
import java.awt.peer.ButtonPeer;

import javax.swing.event.*;
import java.util.*;
import java.io.*;


public class GUI extends JFrame
{
	private JButton startButton, stopButton;
	private Container c;
	private JSlider fanSpeed;
	GridBagConstraints gridConstraints = new GridBagConstraints();
	StillClock sc;
	ArcsPanel ap;
	boolean startPressed = false;
	int speed=0;
	String[] colorArray = {"Black", "Blue", "Yellow", "Green","Red"};
	JComboBox<String> colorList;
	Color fanColor;
	
	public static void main(String args[])
	{
		GUI win = new GUI();
		win.setSize(700, 300);
		//win.setResizable(false);
		win.setTitle("CLOCK/FAN");
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public GUI()
	{
		c=getContentPane();
		c.setLayout(new GridBagLayout());
		Handler h = new Handler();

		startButton = new JButton("Start");
		gridConstraints.weightx = 1.0; 
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.gridwidth = 1;
		gridConstraints.gridheight = 1;
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 0;
		c.add(startButton, gridConstraints);

		stopButton = new JButton("end");
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.weightx = 1.0;   //request any extra vertical space
		gridConstraints.gridwidth = 1;
		gridConstraints.gridheight = 1;
		gridConstraints.gridx = 4;
		gridConstraints.gridy = 0;
		c.add(stopButton, gridConstraints);


		ap = new ArcsPanel();
		gridConstraints.weightx = 1.0;   //request any extra vertical space
		gridConstraints.anchor = GridBagConstraints.CENTER;
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.gridwidth = 2;
		gridConstraints.gridheight = 2;
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 2;
		c.add(ap, gridConstraints);


		sc = new StillClock();
		gridConstraints.weightx = 1.0;   //request any extra vertical space
		gridConstraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.gridwidth = 1;		
		gridConstraints.gridheight = 1;
		gridConstraints.gridx = 4;
		gridConstraints.gridy = 2;
		c.add(sc, gridConstraints);
		
		fanSpeed = new JSlider(JSlider.HORIZONTAL,20,150,75 );
		gridConstraints.weightx = 1.0;   //request any extra vertical space
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.gridwidth = 2;
		gridConstraints.gridheight = 2;
		gridConstraints.gridx = 4;
		gridConstraints.gridy = 2;
		c.add(fanSpeed);
		
		colorList = new JComboBox<String>(colorArray);
		gridConstraints.weightx = 1.0;   //request any extra vertical space
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.gridwidth = 2;
		gridConstraints.gridheight = 2;
		gridConstraints.gridx = 4;
		gridConstraints.gridy = 2;
		c.add(colorList);

		startButton.addActionListener(h);
		stopButton.addActionListener(h);

	}

	public class Handler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Thread t1 = new Thread(new RunClock());
			Thread t2 = new Thread(new RunFan());
			if(e.getSource() == startButton)
			{
				try {
					if(startPressed==false)
					{
						t1.start();
						t2.start();
						startPressed=true;
					}
				}
				catch(IllegalThreadStateException ex)
				{
					System.out.println("illegal thread state.");
				}
			}
			if(e.getSource() == stopButton)
			{
				t1.interrupt();
				t2.interrupt();
				startPressed=false;

			}
		}
	}

	public class RunClock implements Runnable
	{

		@Override
		public void run()
		{
			try{
				while(startPressed==true)
				{
					sc.setCurrentTime();
					sc.repaint();
					Thread.sleep(100);
				}
			}catch(Exception ex)
			{

			}
		}
	}

	public class RunFan implements Runnable
	{
		@Override
		public void run()
		{
			int rotate = 0;

			try{
				while(startPressed==true)
				{
					speed=fanSpeed.getValue();
					switch(colorList.getSelectedIndex())
					{
						case 0:
							fanColor=Color.BLACK;
							break;
						case 1:
							fanColor=Color.BLUE;
							break;
						case 2:
							fanColor=Color.YELLOW;
							break;
						case 3:
							fanColor=Color.GREEN;
							break;
						case 4:
							fanColor=Color.RED;
							break;
					}
					ap.spinFan(ap.getGraphics(), rotate, fanColor);
					rotate-=5;
					Thread.sleep(speed);
				}
			}
			catch(Exception ex){

			}
		}
	}

}