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
	
	//private StateBasedGame game;
	
	Color Background = new Color(0xfff4f4f4);
	
	public static int WinX = 800;			// Size of window
	public static int WinY = 600;
	public static boolean WinF = false;
	public static Image robot;				// Image for animation
	public static Image wood;
	public static Image ice;
	public static Image concrete;
	
	public static Button go;				// Button to start animation
	public static Button goTrans;			// 	Transparent button over the go buttons
	
	private static final int DELAY = 100;	//| Makes sure robot is moved in such a way that you can
	private int timeElapsed = 0;			//|		see it move.
	
	private int t = 0;					// To keep track of time.
	
	public static float x = 200;		// The x and y position of the robot
	public static float y = 400;		// 	on the screen
	
	static GraphSpace vtGraph, atGraph, dtGraph;	// The three graphs that we will be showing
	
	public int getID() {
		return GameStates.Simulation.ordinal();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		System.out.println("Simulation state");
	}
	
	public static void initState(GameContainer gc, StateBasedGame sg) throws SlickException{
		gc.setShowFPS(false);
		gc.setVSync(true);
		BuildState.simFlag = true;
		BuildState.buildFlag = false;
		gc.setMinimumLogicUpdateInterval(50);
		
		robot = new Image("res/robotImg.png");
		wood = new Image("res/woodFloor.png");
		ice = new Image("res/iceFloor.png");
		concrete = new Image("res/concreteFloor.png");
		
		go = new Button(gc, sg, 720, 540, 40, 18, Color.darkGray, Color.white, "SIMULATION");
		goTrans = new Button(gc, sg, 720, 540, 40, 18, Color.transparent, Color.transparent, "          ");
		
		gc.setMinimumLogicUpdateInterval(10);
		GUI.InitGUISim(gc, sg);		// Initializes the GUI for simulation
		
		dtGraph = new GraphSpace(gc, MainSim.WinX - 150-48, 16, 150+40, 150+40, true, 1, "dt", 1);
		vtGraph = new GraphSpace(gc, dtGraph.getX() - 150-48, 16, 150+40, 150+40, false, 2, "vt", 6);
		atGraph = new GraphSpace(gc, vtGraph.getX() - 150-48, 16, 150+40, 150+40, true, 3, "at", 30);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
		g.setColor(Background);						
    	g.fillRect(0, 0, 1366, 768);				
    
    	// Draws a background based on the coefficient of friction	
    	if (BuildState.roboWheel.getMu() < 0.2){
    		ice.draw(200, 425);
    	}else if (BuildState.roboWheel.getMu() > 0.8){
    		concrete.draw(200, 425);
    	}else{
    		wood.draw(200, 425);
    	}
    	
    	robot.draw(x, y, (float)0.55);			// Draws robot at specified x and y position	
    	
    	GUI.RenderGUI(gc, sg, g, "InfoPane");		//|
    	GUI.RenderGUI(gc, sg, g, "Tabs");			//| Draws the necessary GUI parts
    	GUI.RenderInfo(g);							//|
    	
    	go.render(gc, g);					// Button to start simulation (Image)					
    	goTrans.render(gc, g);				// Button to start simulation (Does stuff)			
    	
    	g.setColor(Color.black);
    	
    	// TODO: make an ruler image to allow user to see the displacement of the robot	
    	
    	dtGraph.render(gc, g);		//|
    	vtGraph.render(gc, g);		//| Renders the graphs
    	atGraph.render(gc, g);		//|
    	
	}
	
	// Resets the simulation state to the default values
	public void reset(GameContainer gc){
		x = 200;
		y = 400;
		dtGraph = new GraphSpace(gc, MainSim.WinX - 150-48, 16, 150+40, 150+40, true, 1, "dt", 4);
		vtGraph = new GraphSpace(gc, dtGraph.getX() - 150-48, 16, 150+40, 150+40, false, 2, "vt", 4);
		atGraph = new GraphSpace(gc, vtGraph.getX() - 150-48, 16, 150+40, 150+40, true, 3, "at", 10);
	}
	
	public void update(GameContainer gc, StateBasedGame sg, int dt) throws SlickException {							
		timeElapsed += dt;	

		if (GUI.fancyMech.buttonClicked(gc) == true){
			reset(gc);
			sg.enterState(GameStates.Build.ordinal());
		}else if (GUI.fancyElec.buttonClicked(gc) == true){
			reset(gc);
			sg.enterState(GameStates.Build.ordinal());
		}else if (GUI.fancySim.buttonClicked(gc) == true){
			reset(gc);
			sg.enterState(GameStates.Build.ordinal());
		}else if (goTrans.buttonClicked(gc) == true){
			goTrans.setActivity(!goTrans.isActive());
			t = 0;
		}
		
		if (goTrans.isActive() == true){
			dtGraph.update();
			vtGraph.update();
			atGraph.update();
			t += 1;
			if (timeElapsed >= DELAY){
				timeElapsed = 0;
				goSim(dt, t);	
			}
		}
	}

	// Calculates the position of the robot when the simulation is running
	public void goSim(int dt, int t){ 
		if (x < WinX){
			if (SimulationPhysics.getAccelSpeed(t) < SimulationPhysics.getSpeed(t)){
				x += (SimulationPhysics.getAccelSpeed(t)*6);
			}else {
				x += (SimulationPhysics.getSpeed(t)*6);
			}
		}else{
			x = 200-robot.getWidth();
		}
	}
}

