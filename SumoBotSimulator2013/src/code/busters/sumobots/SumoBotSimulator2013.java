/*
 * Driver class for the application.  Basically, if something needs to be done, it will related with this class.
 */


// to put in the info thingy (about wire area and gauge):
// http://www.engineeringtoolbox.com/awg-wire-gauge-d_731.html
package code.busters.sumobots;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.*;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class SumoBotSimulator2013 extends BasicGame{
	
	Color Background = new Color(0xfff4f4f4);

	public static int WinX = 800;
	public static int WinY = 600;
	public static boolean WinF = false;
	
	public static boolean initFlag = false;
		
	static double RWeight =  0;											// General variables for the robot
	static Wheel roboWheel;
	static Motor roboMotorOne;
	static PowerSupply roboPS;
	static Wire roboWire;
	
    public SumoBotSimulator2013(){
        super("Sumo Bot Simulator 2013");
    }
  
    @Override
    public void init(GameContainer gc) throws SlickException{	
    	gc.setShowFPS(false);
    	
    	roboWheel = new Wheel();
    	roboMotorOne = new Motor();
    	roboPS = new PowerSupply();
    	roboWire = new Wire();
    	
    	
    	GUI.InitGUI(gc);
    	File robot = new File("files/robot.xml");
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
    public void update(GameContainer gc, int delta) throws SlickException{
    	if (initFlag == true) {
    		init(gc);
    		initFlag = false;
    	}
    	GUI.UpdateGUI(gc, delta);
    }
  
    public void render(GameContainer gc, Graphics g) throws SlickException{	    	
    	g.setColor(Background);
    	g.fillRect(0, 0, 1366, 768);
    	GUI.RenderGUI(gc, g, "MainWindow");
    	GUI.RenderGUI(gc, g, "InfoPane");
    	GUI.RenderGUI(gc, g, "MenuBar");
    	
    }
  
    public static void main(String[] args)throws SlickException{
    	 AppGameContainer app = new AppGameContainer(new SumoBotSimulator2013());
         app.setDisplayMode(WinX, WinY, WinF);
         app.start();
    }
}
