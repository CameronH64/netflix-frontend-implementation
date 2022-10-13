// Cameron Holbrook
// CSCI 3381

package netflixfrontendpackage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import netflixbackendpackage.ShowCollection;
import netflixbackendpackage.ShowInWeek;

public class ReboundPanel extends JPanel {

	private int delay = 20;
	private int height;
	private int width;
	private ImageIcon image;
	private Timer timer;
	private int x, y, moveX, moveY;

	//-----------------------------------------------------------------
	// Sets up the panel, including the timer for the animation.
	//-----------------------------------------------------------------
	public ReboundPanel(int w, int h) {
		width = w;
		height = h;
		timer = new Timer(delay, new ReboundListener());
		image = new ImageIcon (this.getClass().getResource("/Dog.gif"));
	    image = new ImageIcon (this.getClass().getResource("/" + this.getClass().getPackageName() + "/sun.gif"));		// Get package name is more reliable.
		x = 0;
		y = 40;
		moveX = moveY = 3;
		
		setPreferredSize(new Dimension(w, h));
		setBackground(Color.LIGHT_GRAY);
		timer.start();
		
	}
	
	public void setDelay (int d) {
		timer.setDelay(d);
	}
	
	//-----------------------------------------------------------------
	// Draws the image in the current location.
	//-----------------------------------------------------------------
	public void paintComponent (Graphics page) {
		super.paintComponent (page);
		image.paintIcon (this, page, x, y);
		
	}
	
	//*****************************************************************
	// Represents the action listener for the timer.
	//*****************************************************************
	private class ReboundListener implements ActionListener	{
		//--------------------------------------------------------------
		// Updates the position of the image and possibly the direction
		// of movement whenever the timer fires an action event.
		//--------------------------------------------------------------
		public void actionPerformed (ActionEvent event) {
			
			x += moveX;
			y += moveY;
			
			if (x <= 0 || x >= width-image.getIconWidth())
				moveX = moveX * -1;
			if (y <= 0 || y >= height-image.getIconHeight())
				moveY = moveY * -1;

			repaint();
			
		}
	}
}
