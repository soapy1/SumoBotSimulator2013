package code.busters.sumobots;

/*
 * The main driver class for the program.  Anything that goes happens in the program happens here. 
 * 2378 lines of code including white space and comments 
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainSim extends StateBasedGame {
		
		public static int WinX = 800;				// The width of the window
		public static int WinY = 600;				// The length of the window
		public static String version = "1.0";		// The version number... woot for 1.0
		
        public MainSim() {
        	super("Sumo Bot Simulator 2013");
        }
        
        public static void main(String args[]) {
        	 try {
        		 AppGameContainer container = new AppGameContainer(new MainSim());
    			 container.setDisplayMode(WinX,WinY,false);
        		 container.start();								
        		 
                } catch (SlickException e) {			// So it does not goof
                        e.printStackTrace();
                }
        }
        
        // Initiates all the states.  The only state where the "real" init is called is the ResourceLoader class.
        //	All the real init methods will be call in the ResourceLoader class
        public void initStatesList(GameContainer gc) throws SlickException {
        	this.addState(new ResourceLoader());
        	this.addState(new BuildState());
        	this.addState(new SimulationState());
        	this.addState(new SettingsState());
       } 
        
}