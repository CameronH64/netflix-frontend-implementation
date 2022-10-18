// Cameron Holbrook
// CSCI 3381

package netflixfrontendpackage;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainFrame {
	public static void main(String[] args) {
		
		// Create the frame that holds the panels.
		JFrame frame = new JFrame("Netflix Playground");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image netflixIcon = Toolkit.getDefaultToolkit().getImage("./images/netflixIcon.png");
		frame.setIconImage(netflixIcon);
		
		// MainPanel contains both MainGUI and LoginPanel
		MainPanel mainPanel = new MainPanel();
		frame.getContentPane().add(mainPanel);
		
		frame.pack();
		frame.setVisible(true);
		
	}
}

