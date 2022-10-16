package netflixfrontendpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

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

import netflixbackendpackage.ShowCollection;
import netflixbackendpackage.ShowInWeek;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class MainPanel extends JPanel{
	
	private int count;
	private JTextField textboxShowWeek;
	private ShowCollection completeShowCollection;
	
	public MainPanel() {
		
		// Backend: Load the data for usage in the private data member.
		completeShowCollection = new ShowCollection();
		completeShowCollection.readFile();
		System.out.println(completeShowCollection);
		
		
		
		// Remember: This here is instantiaing the private data members!
		count = 0;
		setLayout(null);
		
		// Set some panel settings.
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(860, 600));
		
		JLabel labelSuggestRandomShow = new JLabel("Suggest Random Show:");
		labelSuggestRandomShow.setBounds(30, 74, 230, 14);
		add(labelSuggestRandomShow);
		
		JLabel labelRandomShow = new JLabel("Random Show:");
		labelRandomShow.setBounds(30, 129, 230, 14);
		add(labelRandomShow);

		JLabel labelRandomShowDisplay = new JLabel("---");
		labelRandomShowDisplay.setBounds(30, 154, 230, 14);
		add(labelRandomShowDisplay);
		
		
		JButton buttonFeelingLucky = new JButton("Feeling Lucky?");
		buttonFeelingLucky.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				labelRandomShowDisplay.setText(completeShowCollection.suggestRandomShow());
				
			}
		});
		buttonFeelingLucky.setBounds(30, 95, 230, 23);
		add(buttonFeelingLucky);
		
		textboxShowWeek = new JTextField();
		textboxShowWeek.setText("2022-09-04");
		textboxShowWeek.setBounds(30, 235, 230, 20);
		add(textboxShowWeek);
		textboxShowWeek.setColumns(10);
		
		JButton buttonGetShows = new JButton("Get Shows");
		buttonGetShows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textboxShowWeek.getText();
				
			}
		});
		buttonGetShows.setBounds(30, 266, 230, 23);
		add(buttonGetShows);
		
		JLabel labelGetShowWeek = new JLabel("Get Show Week:");
		labelGetShowWeek.setBounds(30, 214, 230, 14);
		add(labelGetShowWeek);
		
		JScrollPane scrollPaneWeeklyRank = new JScrollPane();
		scrollPaneWeeklyRank.setBounds(322, 193, 230, 367);
		add(scrollPaneWeeklyRank);
		
		JTextArea textAreaDisplayWeeklyRankShows = new JTextArea();
		scrollPaneWeeklyRank.setViewportView(textAreaDisplayWeeklyRankShows);

		
		JSlider sliderWeeklyRank = new JSlider();
		sliderWeeklyRank.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				
				
			}
		});
		sliderWeeklyRank.setSnapToTicks(true);
		sliderWeeklyRank.setMinimum(1);
		sliderWeeklyRank.setPaintLabels(true);
		sliderWeeklyRank.setPaintTicks(true);
		sliderWeeklyRank.setMajorTickSpacing(1);
		sliderWeeklyRank.setMaximum(10);
		sliderWeeklyRank.setBounds(322, 95, 230, 45);
		add(sliderWeeklyRank);
		
		JLabel labelSelectShowsByWeeklyRank = new JLabel("Select Shows by Weekly Rank");
		labelSelectShowsByWeeklyRank.setBounds(322, 70, 230, 14);
		add(labelSelectShowsByWeeklyRank);
		
		JLabel labelDisplayShowsOfWeeklyRank = new JLabel("Shows of Weekly Rank:");
		labelDisplayShowsOfWeeklyRank.setBounds(322, 168, 230, 14);
		add(labelDisplayShowsOfWeeklyRank);
		
		JLabel labelCompleteData = new JLabel("Complete Data");
		labelCompleteData.setBounds(581, 168, 230, 14);
		add(labelCompleteData);
		
		JLabel labelWelcomeUser = new JLabel("Welcome User: ");
		labelWelcomeUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelWelcomeUser.setBounds(30, 28, 230, 18);
		add(labelWelcomeUser);
		
		JScrollPane scrollPaneCompleteData = new JScrollPane();
		scrollPaneCompleteData.setBounds(581, 193, 230, 367);
		add(scrollPaneCompleteData);
		
//		String username = JOptionPane.showInputDialog("Please enter your username: ");
//		labelWelcomeUser.setText("Welcome User: " + username);
		
		JTextArea textAreaDisplayCompleteData = new JTextArea();
		scrollPaneCompleteData.setViewportView(textAreaDisplayCompleteData);
		textAreaDisplayCompleteData.setText(completeShowCollection.toString());
		
		
		
	}
}
