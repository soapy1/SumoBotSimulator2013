package code.busters.sumobots;

/*
 * The main driver class for the program.  Anything that goes happens in the program happens here. 
 */

//http://www.javacodegeeks.com/2012/12/escaping-the-jvm-heap-for-memory-intensive-applications.html
//http://cnx.org/content/m45733/latest/?collection=col11489/latest
//http://cnx.org/content/m45737/latest/?collection=col11489/latest


import code.busters.sumobots.Splash;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainSim extends StateBasedGame {
		
		public static int WinX = 800;
		public static int WinY = 600;
		
        public MainSim() {
        	super("Sumo Bot Simulator 2013");
        }
        
        public void initStatesList(GameContainer container) {
        	addState(new BuildState());
        	addState(new SimulationState());
        }
        
        public static void main(String[] argv) {
        		try {
        			try {
        				Splash.drawSplash();
        			} catch (NullPointerException e) {
        			}
        			AppGameContainer container = new AppGameContainer(new MainSim());
               		container.setDisplayMode(WinX,WinY,false);
                    container.start();

                } catch (SlickException e) {
                        e.printStackTrace();
                }
        }
        
}