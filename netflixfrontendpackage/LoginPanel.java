// Cameron Holbrook
// CSCI 3381

package netflixfrontendpackage;
import netflixbackendpackage.ShowCollection;
import netflixbackendpackage.ShowInWeek;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class LoginPanel extends JPanel{
	
	private JPasswordField passwordFieldPassword;
	
	public LoginPanel() {
				
		setLayout(null);
		
		// *****************************************************************************
		// Basic Panel settings.
		// *****************************************************************************
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(850, 500));
		
		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setBounds(262, 260, 85, 14);
		add(labelPassword);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(340, 257, 201, 20);
		add(passwordFieldPassword);
		
		JLabel labelNetflixLogin = new JLabel("Enter Password");
		labelNetflixLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelNetflixLogin.setBounds(378, 195, 218, 31);
		add(labelNetflixLogin);

		JLabel labelInvalidPasswordMessage = new JLabel("Invalid Password; try again");
		labelInvalidPasswordMessage.setBounds(577, 254, 237, 20);
		add(labelInvalidPasswordMessage);
		labelInvalidPasswordMessage.setVisible(false);
		
		// *****************************************************************************
		// The login page only needs one action listener, which is for the login button.
		// *****************************************************************************
		JButton buttonLogin = new JButton("Login");
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				char[] input = passwordFieldPassword.getPassword();
				
				// Check if password matches the one in the system (it's defined in the isPasswordCorrect function).
				if (isPasswordCorrect(input)) {
					
					setVisible(false);
					
				} else {					
					labelInvalidPasswordMessage.setVisible(true);
				}
			}
		});
		
		buttonLogin.setBounds(340, 288, 201, 23);
		add(buttonLogin);
		
		JLabel netflixPicture = new JLabel("");
		netflixPicture.setIcon(new ImageIcon(LoginPanel.class.getResource("/images/netflixLogo.png")));
		netflixPicture.setBounds(88, 11, 676, 173);
		add(netflixPicture);
		
	}
	
	// https://docs.oracle.com/javase/tutorial/uiswing/components/passwordfield.html
	// I wanted to use this method because otherwise, I'd be using the deprecated getText() method of JPasswordField.
	// And if I used getText(), then JPasswordField would be no different than a regular JTextField, aside from the masked keystrokes.
	private static boolean isPasswordCorrect(char[] input) {
	    boolean isCorrect = true;
	    
	    char[] correctPassword = { 'M', 'o', 'v', 'i', 'e', 'T', 'i', 'm', 'e', '2', '2', '!' };		// My invented password.

	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }

	    //Zero out the password.
	    Arrays.fill(correctPassword,'0');

	    return isCorrect;
	}
	
}
