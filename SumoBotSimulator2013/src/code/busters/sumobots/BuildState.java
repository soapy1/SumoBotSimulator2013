package code.busters.sumobots;

/*
 * Class that defines the build state for the application
 */

//http://stackoverflow.com/questions/403256/how-do-i-read-a-resource-file-from-a-java-jar-file

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import code.busters.sumobots.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class BuildState extends BasicGameState {
	
	Color Background = new Color(0xfff4f4f4);
	
	// Window dimensions
	public static int WinX = 800;				
	public static int WinY = 600;
	public static boolean WinF = false;
	
	public static boolean initFlag;
	public static boolean simFlag;
	public static boolean buildFlag;
		
	// General variables for the robot
	static double RWeight =  0;											
	static Wheel roboWheel;
	static Motor roboMotorOne;
	static PowerSupply roboPS;
	static Wire roboWire;
	
	public int getID() {
		return GameStates.Build.ordinal();
	}
	
	@Override
	// Dummy init so that we can have a splash screen
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		System.out.println("Build state");
    }	

	// Real init
	public static void initState(GameContainer gc, StateBasedGame sg) throws SlickException, NullPointerException {
		
		initFlag = false;
		simFlag = false;
		buildFlag = true;
		
		// Creates the general variables for the robot
		roboWheel = new Wheel();
    	roboMotorOne = new Motor();
    	roboPS = new PowerSupply();
    	roboWire = new Wire();
		
    	// Sets display mode so that it looks nice
		gc.setShowFPS(false);
		gc.setVSync(true);
		
		// Initiates the GUI class so that we can show stuff on the screen (all the stuff is in the GUI class)
    	GUI.InitGUI(gc, sg);
    
    	// Open the robot.xml file for the first time using an input file stream
    	try {
			XMLReadWrite.readIS(ResourceLoader.is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	
    	// Initializes the text boxes for the GUI
    	GUI.setTextBox();
    }	
	
	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
    	// Create/fill the background
		g.setColor(Background);
    	g.fillRect(0, 0, 1366, 768);
    	
    	// Draws the appropriate stuffs for the GUI
    	GUI.RenderGUI(gc, sg, g, "MainWindow");
    	GUI.RenderGUI(gc, sg, g, "InfoPane");
    	GUI.RenderGUI(gc, sg, g, "Tabs");
    	GUI.RenderInfo(g);
    	GUI.RenderGUI(gc, sg, g, "MenuBar");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sg, int dt) throws SlickException {
		// Updates the GUI using the GUI class and UpdateGUI method
		GUI.UpdateGUI(gc, sg, dt);
		if (initFlag == true) {
    		init(gc, sg);
    		initFlag = false;
    	}
	}
}
