package netflixfrontendpackage;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Push Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = Toolkit.getDefaultToolkit().getImage("./images/netflixIcon.png");
		frame.setIconImage(icon);
		
		MainPanel panel = new MainPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
		
	}
}

