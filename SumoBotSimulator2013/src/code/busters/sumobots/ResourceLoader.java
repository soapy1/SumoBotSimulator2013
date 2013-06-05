package code.busters.sumobots;

import java.io.InputStream;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ResourceLoader extends BasicGameState{

	public static boolean loaded = false;
	static public InputStream is;

	Image splash;				// Splash screen image	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		gc.setShowFPS(false);
		splash = new Image("res/sumobotsimsplash.png");		// Loads image for splash screen
		
		is = getClass().getClassLoader().getResourceAsStream("code/busters/sumobots/resources/robot.xml");	// Gets the robot.xml file 
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics g)	throws SlickException {
		// Draws the splash screen 
		splash.draw((MainSim.WinX/2)-(splash.getWidth()/2), 
				(MainSim.WinY/2)-(splash.getHeight()/2));		// Draws the splash screen image
		g.drawString("LOADING...", 14, 16);						// Tells the user it is loading so they don't get angry					
		g.drawString("VERSION: " + MainSim.version, 0, 0);		// Tells the version
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sg, int dt) throws SlickException {
		if (loaded == true){								// When all the resources are loaded it starts BuildState
			sg.enterState(GameStates.Build.ordinal());
		}else{
			BuildState.initState(gc, sg);					// Loads the resources
			SimulationState.initState(gc, sg);
			loaded = true;
		}		
	}

	@Override
	public int getID() {
		return GameStates.Load.ordinal();
	}
}
