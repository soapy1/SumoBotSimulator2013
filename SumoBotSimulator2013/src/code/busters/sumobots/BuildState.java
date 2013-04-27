package code.busters.sumobots;

/*
 * Class that defines the build state for the application
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import code.busters.sumobots.GameStates;
import code.busters.sumobots.MainSim;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class BuildState extends BasicGameState {
	
	Color Background = new Color(0xfff4f4f4);
	
	public static int WinX = 800;
	public static int WinY = 600;
	public static boolean WinF = false;
	
	public static boolean initFlag;
	public static boolean simFlag;
	public static boolean buildFlag;
		
	static double RWeight =  0;											// General variables for the robot
	static Wheel roboWheel;
	static Motor roboMotorOne;
	static PowerSupply roboPS;
	static Wire roboWire;
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return GameStates.Build.ordinal();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		
		initFlag = false;
		simFlag = false;
		buildFlag = true;
		
		roboWheel = new Wheel();
    	roboMotorOne = new Motor();
    	roboPS = new PowerSupply();
    	roboWire = new Wire();
		
		gc.setShowFPS(false);
		gc.setVSync(true);
		
    	GUI.InitGUI(gc, sg);
    	File robot = new File("res/robot.xml");
    	try {
			XMLReadWrite.read(robot);
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
		}
    }	

	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
    	g.setColor(Background);
    	g.fillRect(0, 0, 1366, 768);
    	GUI.RenderGUI(gc, sg, g, "MainWindow");
    	GUI.RenderGUI(gc, sg, g, "InfoPane");
    	GUI.UpdateInfo(g);
    	GUI.RenderGUI(gc, sg, g, "MenuBar");
    	//GUI.RenderGUI(gc, g, "comp");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sg, int dt) throws SlickException {
		GUI.UpdateGUI(gc, sg, dt);
		if (initFlag == true) {
    		init(gc, sg);
    		initFlag = false;
    	}
	}
}
