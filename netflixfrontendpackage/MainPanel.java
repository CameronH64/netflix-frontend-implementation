// Cameron Holbrook
// CSCI 3381

package netflixfrontendpackage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import netflixbackendpackage.ShowCollection;
import netflixbackendpackage.ShowInWeek;

public class MainPanel extends JPanel {

	private final int WIDTH = 640, HEIGHT = 480;
	private int delay;

	private JPanel controlPanel;
	private ReboundPanel imagePanel;
	private JButton showIcon;
	private JButton changeSpeed;

	//-----------------------------------------------------------------
	// Sets up the panel, including the timer for the animation.
	//-----------------------------------------------------------------
	public MainPanel() {
		
		super(new BorderLayout());

		delay = 20;
		controlPanel = new JPanel();
		controlPanel.setPreferredSize (new Dimension(WIDTH / 2, HEIGHT));
		controlPanel.setBackground (Color.GRAY);
		showIcon = new JButton("show animation");
		showIcon.addActionListener(new ShowIconListener());
		controlPanel.add(showIcon);
		changeSpeed = new JButton("change speed");
		changeSpeed.addActionListener(new ChangeSpeedListener());
		controlPanel.add(changeSpeed);

		imagePanel = new ReboundPanel(WIDTH/2,HEIGHT);

		setPreferredSize (new Dimension(WIDTH, HEIGHT));
		setBackground (Color.LIGHT_GRAY);
		add(controlPanel, BorderLayout.WEST);
		add(imagePanel, BorderLayout.EAST);
	
	}
	
	private class ShowIconListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (imagePanel.isVisible())
				imagePanel.setVisible(false);
			else
				imagePanel.setVisible(true);
		}
	}
	
	private class ChangeSpeedListener implements ActionListener {
		
		public void actionPerformed (ActionEvent event) {
			do {
				try {
					String delayStr = JOptionPane.showInputDialog("Enter an integer 0-100");
					delay = Integer.parseInt(delayStr);
				} catch (Exception e) {
					delay = -1;
				}
			} while (delay < 0 || delay > 100);
			imagePanel.setDelay(delay);	
		}
	}
}