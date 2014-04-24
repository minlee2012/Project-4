package project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class WelcomeWindow extends JApplet implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	//Width of the window
	private final static int width = 600;
	//Height of the window
	private final static int height = 300;
	
	//Welcome message
	private final String WELCOME_MESSAGE = "Welcome to the World Map";
	
	//Initial JPanel that will be presented on the applet
	private JPanel window;
	//JLabel that displays the map image
	private JLabel mapLabel;
	//MapPanel object that is the main JPanel that will be presented on the applet
	private MapPanel mapPanel;
	//Button that starts the program
	private JButton startButton;
	//Variable for the content pane
	private Container content = getContentPane();
	//ImageIcon that holds the main map image
	private ImageIcon map;
	//ImageIcon that is the reference for the desired size of the window
	private ImageIcon referenceMap;
	//Textfield for user name
	private TextField username; 
	
	public void init(){
		
		window = new JPanel();
		window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));
		
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		startButton.setSize(200, 50);
		
		username = new TextField(20);
		
		JLabel mapLabel = new JLabel();
		
		ImageIcon map = new ImageIcon("mapImage2.png"); 
		ImageIcon referenceMap = new ImageIcon("lifeExpectancyEdit.png"); 
		
		mapLabel.setIcon(map);
		
		JLabel prompt = new JLabel("Please enter your name:");
		
		setSize(referenceMap.getIconWidth() + 200, referenceMap.getIconHeight() + 200);
		window.add(mapLabel);
		window.add(startButton);
		window.add(prompt);
		window.add(username);
		mapLabel.setAlignmentX(CENTER_ALIGNMENT);
		mapLabel.setAlignmentY(CENTER_ALIGNMENT);
		window.setVisible(true);
		content.add(window);
		
	}//init
	
	private void goToMapPanel(){
		
		mapPanel = new MapPanel(null);
		mapPanel.setVisible(true);
		window.setVisible(false);
		this.setContentPane(mapPanel);
		
	}//goToMapPanel

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		goToMapPanel();
	}//actionPerformed
	
}//class WelcomeWindow