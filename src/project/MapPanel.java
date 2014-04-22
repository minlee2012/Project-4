package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;//I think this is the right class to use; change later?
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MapPanel extends JPanel implements ActionListener, MouseListener{

	/*public enum Continent{
		WORLD, NORTH_AMERICA, SOUTH_AMERICA, EUROPE, AFRICA, ASIA;
	}*/
	
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
	
	//buttons for the countries
	private HashMap<String, AppButton> buttons;//a hash of all the buttons for the countries
	
	//images for the Maps
	private BufferedImage worldMapEconomic;
	private BufferedImage worldMapHealth;
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
	public MapPanel(DataManager newWorldData){
		//TODO: do Swing set up here as necessary
		//ie width, height setting, etc
		
		//assign DataManager
		worldData = newWorldData;
		
		//delegate to helper function for rest of setup
		init();
	}
	
	/**
	 * Sets up a MapPanel.
	 * Helper function for the constructor.
	 * <p>
	 * (Assumes that worldData has been set previously.)
	 */
	private void init(){
		//set default values for what we're looking at
		currentView = "World";
		currentCountry = "none";
		currentMapMode = MapMode.ECONOMIC;
		
		//load data from the DataManager
		String countryNames[] = worldData.getCountryList();
		
		//make a button for each country
		CountryData countryData;
		AppButton newButton;
		for(int i = 0; i < countryNames.length; i++){
			//get the country's data
			countryData = worldData.getDataForCountry(countryNames[i]);
			
			//make the button and set appropriate values on it
			newButton = new AppButton();
			newButton.setId(countryNames[i]);
			
			//add the button to the hash
			buttons.put(countryNames[i], newButton);
			
			//add the button to the layoutÉsomehow?
			//TODO: make this work with layouts
			//TODO: get x and y from the CountryData and pass them directly to the layout function thing
		}
		
		//TODO: load all the map images here
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
				//show a message to the user
				JOptionPane.showMessageDialog(this, "Thanks for playing!", "Quiz ended", JOptionPane.INFORMATION_MESSAGE);
				
				//flip the bool
				quizRunning = false;
				
				//change the label on the button
				quizButton.setText("Start Quiz");
			} else if(!quizRunning && currentView != "World"){//they're in a continent but not in a quiz, so let's start one!
				//flip the bool
				quizRunning = true;
				
				//change the label on the button
				quizButton.setText("End Quiz");
				
				//start the quiz
				runQuiz();
			} else if(currentView.equals("World")){//you can't start a quiz from the world view
				JOptionPane.showMessageDialog(this, "You must select a continent to take a quiz!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Presents the user with a series of the questions on the
	 * currently selected continent based on the current map mode
	 * (economic or health)
	 */
	private void runQuiz(){
		if(currentView.equals("World")){//you can't start a quiz from the world view
			JOptionPane.showMessageDialog(this, "You must select a continent to take a quiz!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			Vector<String> subjectCountries = worldData.getDataForContinent(currentView).getCountryList();
			
			//TODO: do the actual quiz here
			//present question
			//check answer
			//profit/repeat!
		}
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
					ContinentData continentData = worldData.getDataForContinent(continentNames[i]);
					
					if(continentData.isPointInBounds(mouseX, mouseY)){//if we're inside this continent
						//note that we've changed continent
						currentView = continentNames[i];
						
						//TODO: change layout appropriately here
						
						//stop checking
						break;
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e){
		//Auto-generated method stub
		//Do nothing; required by interface
	}

	@Override
	public void mouseExited(MouseEvent e){
		//Auto-generated method stub
		//Do nothing; required by interface
	}

	@Override
	public void mousePressed(MouseEvent e){
		//Auto-generated method stub
		//Do nothing; required by interface		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//Auto-generated method stub
		//Do nothing; required by interface
	}
	
}//class MapPanel
