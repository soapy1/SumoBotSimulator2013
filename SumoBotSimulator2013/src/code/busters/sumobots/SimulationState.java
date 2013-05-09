package code.busters.sumobots;

import code.busters.sumobots.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SimulationState extends BasicGameState {
	
	private StateBasedGame game;
	
	Color Background = new Color(0xfff4f4f4);
	
	public static int WinX = 800;			// Size of window
	public static int WinY = 600;
	public static boolean WinF = false;
	public static Image robot;				// Image for animation
	public Button go;						// Button to start animation
	
	private static final int DELAY = 500;	//| Makes sure robot is moved in such a way that you can
	private static int timeElapsed = 0;			//|		see it move.
	
	static float x = 200;		// The x and y position of the robot
	static float y = 400;		// 	on the screen
	
	public int getID() {
		return GameStates.Simulation.ordinal();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		game = sg;
		gc.setShowFPS(false);
		gc.setVSync(true);
		BuildState.simFlag = true;
		BuildState.buildFlag = false;
		robot = new Image("res/robotImg.png");	
		go = new Button(gc, sg, 740, 540, 40, 18, Color.darkGray, Color.white, "START ");
		
		GUI.InitGUISim(gc, sg);		// Initializes the GUI for simulation
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
		g.setColor(Background);						//|
    	g.fillRect(0, 0, 1366, 768);				//|
    	GUI.RenderGUI(gc, sg, g, "InfoPane");		//|
    	GUI.RenderGUI(gc, sg, g, "Tabs");			//| Draws the necessary GUI parts
    	GUI.RenderInfo(g);							//|
    	go.render(gc, g);							//|
    	
    	robot.draw(x, y, (float)0.55);		// Draws robot at specified x and y position	
    	
    	g.setColor(Color.black);
    	// TODO: make an ruler image to allow user to see the displacement of the robot
    	g.fillRect(200, (float)(400+robot.getHeight()*0.5), 600, 4);	//
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sg, int dt) throws SlickException {
		timeElapsed += dt;
		
		if (GUI.fancyMech.buttonClicked(gc) == true){
			sg.enterState(GameStates.Build.ordinal());
		}else if (GUI.fancyElec.buttonClicked(gc) == true){
			sg.enterState(GameStates.Build.ordinal());
		}else if (GUI.fancySim.buttonClicked(gc) == true){
			sg.enterState(GameStates.Build.ordinal());
		}
		// TODO: update automatically so that you gets a smooth animation
		if (go.buttonClicked(gc) == true){
			if (timeElapsed >= DELAY){
				timeElapsed = 0;
				goSim(dt);	
			}
		}
	}

	public static void goSim(int dt){ 
		if (x < WinX- (robot.getWidth()*0.5)){
			x += SimulationPhysics.getSpeed();
		}
	}
}
