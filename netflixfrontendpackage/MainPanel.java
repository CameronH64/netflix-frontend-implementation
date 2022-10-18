// Cameron Holbrook
// CSCI 3381

package netflixfrontendpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class MainPanel extends JPanel {
	
	private MainGUI mainGUI;
	private LoginPanel loginPanel;
	private final int WIDTH = 850, HEIGHT = 500;
	
	MainPanel(){

		super(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// Create the login panel
		loginPanel = new LoginPanel();
		add(loginPanel, BorderLayout.WEST);
		
		// Create the main GUI panel
		mainGUI = new MainGUI();
		add(mainGUI, BorderLayout.CENTER);
					
	}
	
}
