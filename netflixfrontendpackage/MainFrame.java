// Cameron Holbrook
// CSCI 3381

package netflixfrontendpackage;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import netflixbackendpackage.ShowCollection;
import netflixbackendpackage.ShowInWeek;

public class MainFrame {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Rebound");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(800, 500);
		frame.getContentPane().add(new MainPanel());
		frame.pack();
		frame.setVisible(true);
		
	}
}
