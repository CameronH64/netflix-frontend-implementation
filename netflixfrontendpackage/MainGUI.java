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

public class MainGUI extends JPanel{
	
	private JTextField textboxEnterShowWeek;
	private ShowCollection completeShowCollection;
	private JTextField textFieldWeek;
	private JTextField textFieldCategory;
	private JTextField textFieldWeeklyRank;
	private JTextField textFieldShowTitle;
	private JTextField textFieldSeasonTitle;
	private JTextField textFieldWeeklyHoursViewed;
	private JTextField textFieldCumulativeWeeksTop10;
	private JLabel labelWelcomeUser;
	
	public MainGUI() {
		
		// Backend: Load the data for usage in the private data member.
		completeShowCollection = new ShowCollection();
		completeShowCollection.readFile();
		
		setLayout(null);
		
		// Set some panel settings.
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(850, 500));
		
		JLabel labelSuggestRandomShow = new JLabel("Feeling Lucky?");
		labelSuggestRandomShow.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelSuggestRandomShow.setBounds(303, 73, 230, 23);
		add(labelSuggestRandomShow);
		
		JLabel labelRandomShow = new JLabel("Random Show:");
		labelRandomShow.setBounds(303, 155, 230, 14);
		add(labelRandomShow);

		JLabel labelDisplayRandomShow = new JLabel("---");
		labelDisplayRandomShow.setBounds(303, 180, 230, 14);
		add(labelDisplayRandomShow);
		
		
		JButton buttonFeelingLucky = new JButton("Surprise Me!");
		buttonFeelingLucky.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				labelDisplayRandomShow.setText(completeShowCollection.suggestRandomShow());
				
			}
		});
		buttonFeelingLucky.setBounds(303, 121, 230, 23);
		add(buttonFeelingLucky);
		
		textboxEnterShowWeek = new JTextField();
		textboxEnterShowWeek.setText("2021-07-04");
		textboxEnterShowWeek.setBounds(303, 322, 230, 20);
		add(textboxEnterShowWeek);
		textboxEnterShowWeek.setColumns(10);
		
		labelWelcomeUser = new JLabel("Welcome Netflix User!");
		labelWelcomeUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelWelcomeUser.setBounds(30, 23, 492, 34);
		add(labelWelcomeUser);
		
		JLabel labelGetShowWeek = new JLabel("Enter Week:");
		labelGetShowWeek.setBounds(303, 301, 230, 14);
		add(labelGetShowWeek);
		
		JLabel labelCompleteData = new JLabel("Data Viewport:");
		labelCompleteData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelCompleteData.setBounds(581, 70, 230, 28);
		add(labelCompleteData);
				
		JScrollPane scrollPaneCompleteData = new JScrollPane();
		scrollPaneCompleteData.setBounds(581, 119, 230, 287);
		add(scrollPaneCompleteData);
		
		// Was originally going to use an input dialog box to ask for a username, but I instead
		// opted for the idea of a login page using multiple panels.
		String username = JOptionPane.showInputDialog("Please enter your Netflix username: ");
		labelWelcomeUser.setText("Welcome User: " + username);
		
		JTextArea textAreaDisplayCompleteData = new JTextArea();
		scrollPaneCompleteData.setViewportView(textAreaDisplayCompleteData);
		textAreaDisplayCompleteData.setText(completeShowCollection.toString());
		
		JComboBox comboBoxMovies = new JComboBox();
		ArrayList<ShowInWeek> moviesInWeek = completeShowCollection.getShows("2021-07-04");
		String[] stringAllData = new String[moviesInWeek.size()];
		int index = 0;
		for (ShowInWeek show : moviesInWeek) {
			stringAllData[index] = show.getShowTitles();
			index++;
		}
		comboBoxMovies.setModel(new DefaultComboBoxModel(stringAllData)); 		// Constructor is a new array of strings.
		comboBoxMovies.setBounds(303, 417, 230, 22);
		add(comboBoxMovies);
		
		JButton buttonGetShows = new JButton("Get Shows");
		buttonGetShows.setBounds(303, 353, 230, 23);
		add(buttonGetShows);
		
		JLabel labelShows = new JLabel("Shows:");
		labelShows.setBounds(303, 392, 230, 14);
		add(labelShows);		

		// *********************************************************************************************
		// The action listener for the text field box that dynamically populates the dropdown/combo box.
		// *********************************************************************************************
			buttonGetShows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Use the getShows method from the ShowCollection class to get all shows of a specified week.
				ArrayList<ShowInWeek> moviesInWeek = completeShowCollection.getShows(textboxEnterShowWeek.getText());
				
				// Check there are movies in moviesInWeek before dynamically populating the combo box.
				if(moviesInWeek != null && moviesInWeek.size() > 0) {
					
					// Create a String array with the size of the moviesInWeek ArrayList.
					String[] stringAllData = new String[moviesInWeek.size()];
					int index = 0;
					
					// Fill up that stringAllData array.
					for (ShowInWeek show : moviesInWeek) {
						stringAllData[index] = show.getShowTitles();
						index++;
					}
					// Set the data from the String array (made from moviesInWeek) to the combo box.
					comboBoxMovies.setModel(new DefaultComboBoxModel(stringAllData));
					// Note: The constructor argument is the new array of strings from earlier, instead of a raw String array.
					
				}
				
			}
		});
		// *****************************************************************************
		// A hefty action listener for submitting a new show to the Netflix database.
		// *****************************************************************************
		JButton buttonAddShow = new JButton("Submit Addition");
		buttonAddShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// First, create a new ShowInWeek object (this will be added to the ShowCollection object).
				ShowInWeek newShow = new ShowInWeek();
				
				// Get input text from each text field box.
				// Parse necessary fields.
				String week = textFieldWeek.getText();
				String category = textFieldCategory.getText();
				int weeklyRank = Integer.parseInt(textFieldWeeklyRank.getText());
				String showTitle = textFieldShowTitle.getText();
				String seasonTitle = textFieldSeasonTitle.getText();
				int weeklyHoursViewed = Integer.parseInt(textFieldWeeklyHoursViewed.getText());
				int cumulativeWeeksTop10 = Integer.parseInt(textFieldCumulativeWeeksTop10.getText());
				
				// Add the data to the ShowInWeek object.
				newShow.setWeek(week);
				newShow.setCategory(category);
				newShow.setWeeklyRank(weeklyRank);
				newShow.setShowTitles(showTitle);
				newShow.setSeasonTitle(seasonTitle);
				newShow.setWeeklyHoursViewed(weeklyHoursViewed);
				newShow.setCumulativeWeeksTop10(cumulativeWeeksTop10);
				
				// Add the ShowInWeek to the completeShowCollection.
				completeShowCollection.addNewShow(newShow);
				
				// Important! Update the data viewport!
				textAreaDisplayCompleteData.setText(completeShowCollection.toString());
				
			}
		});
		buttonAddShow.setBounds(30, 416, 230, 23);
		add(buttonAddShow);
		
		JLabel labelWeek = new JLabel("Week:");
		labelWeek.setBounds(30, 107, 230, 14);
		add(labelWeek);
		
		JLabel labelAddNewShow = new JLabel("Add New Show:");
		labelAddNewShow.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelAddNewShow.setBounds(30, 73, 230, 23);
		add(labelAddNewShow);
		
		JLabel labelGetShowFromWeek = new JLabel("Weekly Search:");
		labelGetShowFromWeek.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelGetShowFromWeek.setBounds(303, 266, 230, 28);
		add(labelGetShowFromWeek);
		
		JLabel labelCategory = new JLabel("Category:");
		labelCategory.setBounds(30, 152, 230, 14);
		add(labelCategory);
		
		
		// *****************************************************************************
		// Text fields for the addition of new show.
		// *****************************************************************************
		textFieldWeek = new JTextField();
		textFieldWeek.setColumns(10);
		textFieldWeek.setBounds(30, 121, 230, 20);
		add(textFieldWeek);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setColumns(10);
		textFieldCategory.setBounds(30, 166, 230, 20);
		add(textFieldCategory);
		
		JLabel labelWeeklyRank = new JLabel("Weekly Rank:");
		labelWeeklyRank.setBounds(30, 197, 230, 14);
		add(labelWeeklyRank);
		
		textFieldWeeklyRank = new JTextField();
		textFieldWeeklyRank.setColumns(10);
		textFieldWeeklyRank.setBounds(30, 211, 230, 20);
		add(textFieldWeeklyRank);
		
		JLabel labelShowTitle = new JLabel("Show Title:");
		labelShowTitle.setBounds(30, 242, 230, 14);
		add(labelShowTitle);
		
		textFieldShowTitle = new JTextField();
		textFieldShowTitle.setColumns(10);
		textFieldShowTitle.setBounds(30, 256, 230, 20);
		add(textFieldShowTitle);
		
		JLabel labelSeasonTitle = new JLabel("Season Title:");
		labelSeasonTitle.setBounds(30, 282, 230, 14);
		add(labelSeasonTitle);
		
		textFieldSeasonTitle = new JTextField();
		textFieldSeasonTitle.setColumns(10);
		textFieldSeasonTitle.setBounds(30, 296, 230, 20);
		add(textFieldSeasonTitle);
		
		JLabel labelWeeklyHoursViewed = new JLabel("Weekly Hours Viewed:");
		labelWeeklyHoursViewed.setBounds(30, 327, 230, 14);
		add(labelWeeklyHoursViewed);
		
		textFieldWeeklyHoursViewed = new JTextField();
		textFieldWeeklyHoursViewed.setColumns(10);
		textFieldWeeklyHoursViewed.setBounds(30, 341, 230, 20);
		add(textFieldWeeklyHoursViewed);
		
		JLabel labelCumulativeWeeksTop10 = new JLabel("Cumulative Weeks Top 10:");
		labelCumulativeWeeksTop10.setBounds(30, 372, 230, 14);
		add(labelCumulativeWeeksTop10);
		
		textFieldCumulativeWeeksTop10 = new JTextField();
		textFieldCumulativeWeeksTop10.setColumns(10);
		textFieldCumulativeWeeksTop10.setBounds(30, 386, 230, 20);
		add(textFieldCumulativeWeeksTop10);
		
		// *****************************************************************************
		// A simple action listener for the save data button for the data viewport.
		// *****************************************************************************
		JButton buttonSaveData = new JButton("Save");
		buttonSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				completeShowCollection.writeFile();
				
			}
		});
		buttonSaveData.setBounds(581, 419, 230, 23);
		add(buttonSaveData);

	}
	
}
