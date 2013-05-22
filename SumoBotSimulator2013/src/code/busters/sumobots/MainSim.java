package code.busters.sumobots;

/*
 * The main driver class for the program.  Anything that goes happens in the program happens here. 
 * 
 * DUE: June 3
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.awt.SplashScreen;

public class MainSim extends StateBasedGame {
		
		public static int WinX = 800;
		public static int WinY = 600;
		public static String version = "0.0.2.2";
		
        public MainSim() {
        	super("Sumo Bot Simulator 2013");
        }
        
        public static void main(String args[]) {
        	 try {
        		
        		 AppGameContainer container = new AppGameContainer(new MainSim());
    			 container.setDisplayMode(WinX,WinY,false);
        		 container.start();
        		 
                } catch (SlickException e) {
                        e.printStackTrace();
                }
        }
        
        public void initStatesList(GameContainer gc) throws SlickException {
        	this.addState(new ResourceLoader());
        	this.addState(new BuildState());
        	this.addState(new SimulationState());
        	this.addState(new SettingsState());
       } 
        
}