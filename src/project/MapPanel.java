package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MapPanel extends JPanel implements ActionListener, MouseListener{

	public enum Continent{
		WORLD, NORTH_AMERICA, SOUTH_AMERICA, EUROPE, AFRICA, ASIA;
	}
	
	public enum MapMode{
		ECONOMIC, HEALTH; //TODO: rename later as appropriate to content?
	}
	
	/* --||-- BEGIN VARIABLES --||-- */
	
	//to make Eclipse shut up about the warning
	private static final long serialVersionUID = 1l;
	
	private DataManager worldData;
	
	//state variables
	private String currentView;//which continent are we looking at?	
	private String currentCountry;//what country are we looking at right now?
	private MapMode currentMapMode;//what mode is the map in?
	private boolean quizRunning;//is the user in a quiz right now?
	private StudentData currentStudent;//who is the user and what have they seen?
	private boolean inPreTest;//are they taking the pretest
	
	//buttons for the countries
	private HashMap<String, AppButton> buttons;//a hash of all the buttons for the countries
	
	//images for the Maps
	//and so on for each continent
	
	//buttons for the UI
	//NB: these must be AppButtons or actionPerformed will break!
	private AppButton mapModeChangeButton;
	private AppButton quizButton;
	private AppButton backButton;//go back to world view from continentView
	
	/* --||-- END VARIABLES --||-- */
	
	/**
	 * Creates a new MapPanel based on data from the given DataManager.
	 * 
	 * @param newWorldData the DataManager to load data from
	 */

	public MapPanel(DataManager newWorldData, StudentData newStudentData, project.MapMode mapType){
		//handle passed-in data
		worldData = newWorldData;
		currentStudent = newStudentData;
		
		//set up basic state
		inPreTest = true;//we start by trapping the user in the pre-test
		quizRunning = true;
		//TODO: get and set the rest of the data on the subject of the pre-test from currentStudent
		
		//delegate to helper function for UI setup
		setUp(mapType);
	}
	
	/**
	 * Does Swing set up for a MapPanel.
	 * Helper function for the constructor.
	 * <p>
	 * (Assumes that worldData has been set previously.)
	 */
	private void setUp(MapMode type){
		//TODO: set text on all buttons correctly, including setting up the quiz button for being in-quiz
		
		quizButton = new AppButton();
		quizButton.setText("End Quiz");
		
		setLayout(new BorderLayout());
		//set default values for what we're looking at
		currentView = "World";
		currentCountry = "none";
		currentMapMode = MapMode.ECONOMIC;
		
		setBackground(Color.black);
		ImageIcon map = new ImageIcon("lifeExpectancyEdit.png"); 
		setSize(map.getIconWidth(), map.getIconHeight());
		
		JLabel mapLabel = new JLabel();
		JPanel infoBox = new JPanel();
		infoBox.setPreferredSize(new Dimension(100, map.getIconHeight()));
		infoBox.setLocation(200,200);
		infoBox.setBackground(Color.RED);
		infoBox.setOpaque(true);

		mapLabel.setIcon(map);

		add(mapLabel, BorderLayout.WEST);	
		add(infoBox, BorderLayout.EAST);
		
		if(type == MapMode.ECONOMIC){
			JLabel gdpPerCapita = new JLabel();
			JLabel gdpRealGrowthRate = new JLabel();
			JLabel agriculturePercentageOfGdp = new JLabel();
			JLabel economicFreedomScore= new JLabel();
			JLabel lowestTenIncome = new JLabel();
			JLabel highestTenIncome = new JLabel();
			JLabel majorIndustries = new JLabel();
			JLabel unemploymentRate = new JLabel();
			JLabel majorEconomicIssue = new JLabel();
			JLabel makeADifferenceEconomic = new JLabel();
			
			CountryData workingCountry = worldData.getDataForCountry(currentCountry);
			gdpPerCapita.setText(workingCountry.getGpdPerCapita());
			gdpRealGrowthRate.setText(workingCountry.getGdpRealGrowthRate());
			agriculturePercentageOfGdp.setText(workingCountry.getagriculturePercentageOfGdp());
			economicFreedomScore.setText(workingCountry.getEconomicFreedomScore());
			lowestTenIncome.setText(workingCountry.getLowestTenIncome());
			highestTenIncome.setText(workingCountry.getHighestTenIncome());
			majorIndustries.setText(workingCountry.getMajorIndustries());
			unemploymentRate.setText(workingCountry.getUnemploymentRate());
			majorEconomicIssue.setText(workingCountry.getMajorEconomicIssue());
			makeADifferenceEconomic.setText(workingCountry.getMakeADifferenceEconomic());
			
		}//if Economic mode
		
	}
	
	/**
	 * Fill the InfoBox with the given data on a country.
	 * 
	 * @param newCountry the data to display about the given country
	 */
	public void updateInfoBox(CountryData newCountry){
		//TODO: implement this once layout is done
		
		//clear the infobox
		
		//extract data from the CountryData and format it appropriately
		//then add it
		
		
	}
	
	/**
	 * Respond to misc. events generated by the UI.
	 * 
	 * @param e the ActionEvent that represents the event that occurred
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("Event " + e.toString() + " did the thing!");
		
		//if the button clicked was the button for a country
		if(buttons.containsKey(((AppButton) e.getSource()).getId())){//hopefully this cast worksÉmake all buttons AppButtons to ensure that
			String countryClicked = ((AppButton) e.getSource()).getId();
			
			//change currentCountry appropriately
			currentCountry = countryClicked;
			
			//update the StudentData
			currentStudent.addCountrySeen(currentCountry, currentMapMode);
			
			//update the info box
			updateInfoBox(worldData.getDataForCountry(countryClicked));
		} else if(e.getSource().equals(backButton)){//back button
			if(!currentView.equals("World")){//we only need to change things if we're not in world view
				//go back to World view
				currentView = "World";
				
				//update the display layer appropriately
				//TODO: implement once the layout is known/how to work with it
				//something like
				if(currentMapMode == MapMode.ECONOMIC){
					//load economic world map
				} else if(currentMapMode == MapMode.HEALTH){
					//load health world map
				}
			}
		} else if(e.getSource().equals(quizButton)){//start/stop quiz
			if(quizRunning){//if they're in a quiz
				if(inPreTest){//don't let people bail on the pre-test
					JOptionPane.showMessageDialog(this, "You must finish the pre-test first!", "Cannot leave pre-test", JOptionPane.WARNING_MESSAGE);
				} else {
					//show a message to the user
					JOptionPane.showMessageDialog(this, "Thanks for playing!", "Quiz ended", JOptionPane.INFORMATION_MESSAGE);
					
					//flip the bool
					quizRunning = false;
					
					//change the label on the button
					quizButton.setText("Start Quiz");
				}
			} else if(!quizRunning){//they're not in a quiz, so let's start one!
				//flip the bool
				quizRunning = true;
				
				//change the label on the button
				quizButton.setText("End Quiz");
				
				//start the quiz
				runQuiz();
			}
		}
	}
	
	/**
	 * Presents the user with a series of the questions on the
	 * currently selected continent based on the current map mode
	 * (economic or health)
	 */
	private void runQuiz(){
		//if(currentView.equals("World")){//you can't start a quiz from the world view
		//	JOptionPane.showMessageDialog(this, "You must select a continent to take a quiz!", "Error", JOptionPane.ERROR_MESSAGE);
		//} else {
			//Vector<String> subjectCountries = worldData.getDataForContinent(currentView).getCountryList();
			
		//get the list of subject countries by bouncing currentView off of StudentData.getCountriesSeen
		
			//TODO: do the actual quiz here
			//TODO: programmatic question generation; elsewhere
			//present question
			//check answer
			//profit/repeat!
		//}
	}

	/**
	 * Respond to mouse clicks.
	 * 
	 * @param e a MouseEvent that represents a mouse click
	 */
	@Override
	public void mouseClicked(MouseEvent e){
		//only check for clicks on continents if we're looking at the whole world
		if(currentView.equals("World")){
			//get the locations of the click
			int mouseX = e.getX();
			int mouseY = e.getY();
			
			//check it against the bounding box of each country
			String continentNames[] = worldData.getContinentList();
			for(int i = 0; i < continentNames.length; i++){
				if(!continentNames[i].equals("World")){//don't check against the world's bounding box
					//get data on the continent
					ContinentData continentData = worldData.getDataForContinent(continentNames[i]);
					
					if(continentData.isPointInBounds(mouseX, mouseY)){//if we're inside this continent
						//note that we've changed continent
						currentView = continentNames[i];
						
						//update StudentData
						currentStudent.addContinentSeen(currentView, currentMapMode);
						
						//TODO: change layout appropriately here
						
						//stop checking by terminating the for loop
						break;
					}
				}
			}
		}
	}

	/**
	 * Respond to the mouse entering this component.
	 * 
	 * @param e a MouseEvent that represents a movement of the mouse
	 */
	@Override
	public void mouseEntered(MouseEvent e){
		//Auto-generated method stub
		//Does nothing; required by interface
	}

	/**
	 * Respond to the mouse exiting this component.
	 * 
	 * @param e a MouseEvent that represents a movement of the mouse
	 */
	@Override
	public void mouseExited(MouseEvent e){
		//Auto-generated method stub
		//Does nothing; required by interface
	}

	/**
	 * Respond to mouse presses.
	 * 
	 * @param e a MouseEvent that represents a mouse press
	 */
	@Override
	public void mousePressed(MouseEvent e){
		//Auto-generated method stub
		//Does nothing; required by interface		
	}

	/**
	 * Respond to mouse releases.
	 * 
	 * @param e a MouseEvent that represents a mouse release
	 */
	@Override
	public void mouseReleased(MouseEvent e){
		//Auto-generated method stub
		//Does nothing; required by interface
	}
	
}//class MapPanel
