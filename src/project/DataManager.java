package project;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DataManager {
	private HashMap<String, CountryData> countryData;
	private HashMap<String, ContinentData> continentData;
	private boolean dataLoaded;
	private String fileLocation;

	/**
	 * Constructor takes a String that is the filepath
	 * @param newFileLocation
	 */
	DataManager(String newFileLocation){
		countryData = new HashMap<String, CountryData>();
		continentData = new HashMap<String, ContinentData>();
		dataLoaded=false;
<<<<<<< HEAD
		setFileLocation(newFileLocation);
	}//constructor
	
	/**
	 * sets the filepath
	 * @param newFileLocation
	 */
	public void setFileLocation(String newFileLocation){
		fileLocation = newFileLocation;
	}//setFileLocation

	public void parseData(){
=======
		fileLocation = newFileLocation;
		parseData();
	}
	


	
	private void parseData(){
>>>>>>> Mike
		try{
			String filename = fileLocation;
			int numContinents = 6;
			int continentCounter = 1;

			FileInputStream fileInputStream = new FileInputStream(filename);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					dataInputStream));
			String currentLine;
			while ((currentLine = bufferedReader.readLine())!=null){

				if (continentCounter <= numContinents){
					ContinentData currentContinent = new ContinentData();
					currentContinent = new ContinentData();
					currentContinent.setCountryName(currentLine);
					currentContinent.setAll(bufferedReader);
					currentContinent.setLeftBound(Integer.parseInt(bufferedReader.readLine()));
					currentContinent.setRightBound(Integer.parseInt(bufferedReader.readLine()));
					currentContinent.setTopBound(Integer.parseInt(bufferedReader.readLine()));
					currentContinent.setBottomBound(Integer.parseInt(bufferedReader.readLine()));

					currentLine = bufferedReader.readLine();
					while ((currentLine = bufferedReader.readLine()).length() > 0){
						currentContinent.addToCountryList(currentLine);
					}//while
					
					continentData.put(currentContinent.getCountryName(), currentContinent);
					continentCounter++;	
				}//if
				else{
					CountryData currentCountry = new CountryData();
					currentCountry = new CountryData();
					
					currentCountry.setCountryName(currentLine);
					currentCountry.setAll(bufferedReader);
					currentCountry.setButtonXPosition(Integer.parseInt(bufferedReader.readLine()));
					currentCountry.setButtonYPosition(Integer.parseInt(bufferedReader.readLine()));
					countryData.put(currentCountry.getCountryName(), currentCountry);
					
					bufferedReader.readLine();
				}//else
			}//while
			
			bufferedReader.close();
			dataLoaded = true;
<<<<<<< HEAD
			
		}//try
		catch(Exception e){
			e.printStackTrace();
		}//catch
	}//parseData
=======
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}



>>>>>>> Mike


	/**
	 * Returns a list of all the countries in the countryData hash.
	 * 
	 * @return a list of all the countries in the countryData hash
	 */
	public String[] getCountryList(){
		return (String[]) countryData.keySet().toArray(new String[countryData.keySet().size()]);
	}//getCountryList
	
	/**
	 * Returns a list of all the continents in the countryData hash.
	 * 
	 * @return a list of all the continents in the countryData hash
	 */
	public String[] getContinentList(){
<<<<<<< HEAD
		System.out.print("In get continent list: " + continentData.keySet().size());
		return (String[]) continentData.keySet().toArray(new String[continentData.keySet().size()]);
	}//getContinentList
=======
		return (String[]) continentData.keySet().toArray(new String[continentData.keySet().size()]);
	}
>>>>>>> Mike
	
	public CountryData getDataForCountry(String countryName){

		return countryData.get(countryName);
<<<<<<< HEAD
	}//getDataForCountry

=======
	}
	
>>>>>>> Mike

	public ContinentData getDataForContinent(String continentName){
		//TODO: actually implement this function!
		return new ContinentData();
	}
	
	public String randomlyChooseVariableForSuperlativeQuestion(){
		ArrayList<String> econVariableList = new ArrayList<String>();
		econVariableList.add("gpdPerCapita");
		econVariableList.add("gdpRealGrowthRate");
		econVariableList.add("gdpRealGrowthRate");
		econVariableList.add("agriculturePercentageOfGDP");
		econVariableList.add("economicFreedomScore");
		econVariableList.add("majorIndustries");
		econVariableList.add("unemploymentRate");
		
<<<<<<< HEAD
		return continentData.get(continentName);
	}//getDataForContinent

}//class DataManager
	
=======
		
		Random generator = new Random();
		int minimum = 0;
		int maximum = econVariableList.size()-1;
		int range = maximum - minimum + 1;
		int indexToChooseVariableToAskAbout =  generator.nextInt(range) + minimum;
		return econVariableList.get(indexToChooseVariableToAskAbout);
	}
		
	
	/*public String[] generateEconSuperlativeQuestion(String continentName, String dataVariable){
		//continentName= getCurrentView();
			switch (dataVariable){	
				case "gpdPerCapita":
					return new String[] {"Which is the poorest country in " + continentName + " ?",
							//gdpPerCapitaSortedAfrica.get(0).getCountryName()};
					}
					
		}
			
		}
		
	}
	
	
	public static void main(String args[]){

		DataManager dm = new DataManager("/Users/michaelmcaneny/Desktop/CountryData.txt");
		ContinentData cd = new ContinentData();

		String[] continents = new String[6];
		continents = dm.getCountryList();
		for (int i = 0; i<continents.length;i++){
			System.out.println(continents[i]);
		}
	}
	*/

}

>>>>>>> Mike
