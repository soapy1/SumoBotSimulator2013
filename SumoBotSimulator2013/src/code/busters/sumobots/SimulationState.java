package code.busters.sumobots;

import code.busters.sumobots.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SimulationState extends BasicGameState {
	
	private StateBasedGame game;
	
	Color Background = new Color(0xfff4f4f4);
	
	public static int WinX = 800;			// Size of window
	public static int WinY = 600;
	public static boolean WinF = false;
	public static Image robot;				// Image for animation
	public static Image wood;
	public static Button go;						// Button to start animation
	public static Button goTrans;
	
	private static final int DELAY = 100;	//| Makes sure robot is moved in such a way that you can
	private int timeElapsed = 0;			//|		see it move.
	
	private int t = 0;			// To keep track of time.
	
	public static float x = 200;		// The x and y position of the robot
	public static float y = 400;		// 	on the screen
	
	// Check with Dan
	//
	//
	static GraphSpace vtGraph, atGraph, dtGraph;
	
	public int getID() {
		return GameStates.Simulation.ordinal();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		
		System.out.println("Simulation state");
	}
	
	public static void initState(GameContainer gc, StateBasedGame sg) throws SlickException{
		//game = sg;
		gc.setShowFPS(false);
		gc.setVSync(true);
		BuildState.simFlag = true;
		BuildState.buildFlag = false;
		gc.setMinimumLogicUpdateInterval(50);
		
		robot = new Image("res/robotImg.png");
		wood = new Image("res/woodFloor.png");
		go = new Button(gc, sg, 720, 540, 40, 18, Color.darkGray, Color.white, "SIMULATION");
		goTrans = new Button(gc, sg, 720, 540, 40, 18, Color.transparent, Color.transparent, "          ");
		
		gc.setMinimumLogicUpdateInterval(10);
		GUI.InitGUISim(gc, sg);		// Initializes the GUI for simulation
		
		dtGraph = new GraphSpace(gc, MainSim.WinX - 150 - 16, 16, 150, 150, true, 1);
		vtGraph = new GraphSpace(gc, dtGraph.getX() - 150 - 16, 16, 150, 150, false, 2);
		atGraph = new GraphSpace(gc, vtGraph.getX() - 150 - 16, 16, 150, 150, true, 3);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
		g.setColor(Background);						
    	g.fillRect(0, 0, 1366, 768);				
    
    	wood.draw(200, 425);					// Draws wood background	
    	robot.draw(x, y, (float)0.55);			// Draws robot at specified x and y position	
    	
    	GUI.RenderGUI(gc, sg, g, "InfoPane");		//|
    	GUI.RenderGUI(gc, sg, g, "Tabs");			//| Draws the necessary GUI parts
    	GUI.RenderInfo(g);							//|
    	
    	go.render(gc, g);					// Button to start simulation (Image)					
    	goTrans.render(gc, g);				// Button to start simulation (Does stuff)			
    	
    	g.setColor(Color.black);
    	// TODO: make an ruler image to allow user to see the displacement of the robot	
    	
    	dtGraph.render(gc, g);
    	vtGraph.render(gc, g);
    	atGraph.render(gc, g);
    	
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
		}else if (goTrans.buttonClicked(gc) == true){
			goTrans.setActivity(!goTrans.isActive());
			t = 0;
		}
		
		if (goTrans.isActive() == true){
			t += 1;
			if (timeElapsed >= DELAY){
				timeElapsed = 0;
				goSim(dt, t);	
			}
		}
		
	}

	public void goSim(int dt, int t){ 
		if (x < WinX){
			if (SimulationPhysics.getAccelSpeed(t) < SimulationPhysics.getSpeed()){
				x += (SimulationPhysics.getAccelSpeed(t));
			}else {
				x += (SimulationPhysics.getSpeed());
			}
		}else{
			x = 200-robot.getWidth();
		}
	}
}

