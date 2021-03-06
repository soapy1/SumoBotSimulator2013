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
	// Dummy init so that we can have a splash screen thing
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		System.out.println("Simulation state");
	}
	
	// Real init
	public static void initState(GameContainer gc, StateBasedGame sg) throws SlickException{
		// Sets the display
		gc.setShowFPS(false);
		gc.setVSync(true);
		// Initiates the proper stuffs
		BuildState.simFlag = true;
		BuildState.buildFlag = false;
		// Makess sure it updates so that we can have a nice animation
		gc.setMinimumLogicUpdateInterval(10);
		
		// Images for the simulation
		robot = new Image("res/robotImg.png");				// A robot image				
		wood = new Image("res/woodFloor.png");				// A wood surface
		ice = new Image("res/iceFloor.png");				// An ice surface
		concrete = new Image("res/concreteFloor.png");		// A concrete surface
		
		// A button with transparent button because the bottom button was not really working properly ;) 
		go = new Button(gc, sg, 720, 540, 40, 18, Color.darkGray, Color.white, "SIMULATION");		
		goTrans = new Button(gc, sg, 720, 540, 40, 18, Color.transparent, Color.transparent, "          ");
		
		// Creates the graphs
		dtGraph = new GraphSpace(gc, MainSim.WinX - 150-48, 16, 150+40, 150+40, true, 1, "dt", 1);		// displacement time graph
		vtGraph = new GraphSpace(gc, dtGraph.getX() - 150-48, 16, 150+40, 150+40, false, 2, "vt", 6);	// velocity time graph
		atGraph = new GraphSpace(gc, vtGraph.getX() - 150-48, 16, 150+40, 150+40, true, 3, "at", 30);	// acceleration time graph
		
		GUI.InitGUISim(gc, sg);		// Initializes the GUI for simulation
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
		// Sets the background colour of the GUI
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
    	
    	dtGraph.render(gc, g);		//|
    	vtGraph.render(gc, g);		//| Renders the graphs
    	atGraph.render(gc, g);		//|
    	
	}
	
	// Resets the simulation state to the default values
	public void reset(GameContainer gc){
		x = 200;		//| Default position for the robot
		y = 400;		//|
		
		dtGraph = new GraphSpace(gc, MainSim.WinX - 150-48, 16, 150+40, 150+40, true, 1, "dt", 4);		//|
		vtGraph = new GraphSpace(gc, dtGraph.getX() - 150-48, 16, 150+40, 150+40, false, 2, "vt", 4);	//| Default graphs (null)
		atGraph = new GraphSpace(gc, vtGraph.getX() - 150-48, 16, 150+40, 150+40, true, 3, "at", 10);	//|
	}
	
	public void update(GameContainer gc, StateBasedGame sg, int dt) throws SlickException {							
		timeElapsed += dt;		// keeps track of time	

		// Controls for the tab buttons 
		if (GUI.fancyMech.buttonClicked(gc) == true){			// Mechanical tab clicked
			reset(gc);											// Sets default values
			sg.enterState(GameStates.Build.ordinal());			// Changes to build state
		}else if (GUI.fancyElec.buttonClicked(gc) == true){		// Electrical tab clicked
			reset(gc);
			sg.enterState(GameStates.Build.ordinal());
		}else if (GUI.fancySim.buttonClicked(gc) == true){		// Simulation tab clicked
			reset(gc);
			sg.enterState(GameStates.Build.ordinal());
		}else if (goTrans.buttonClicked(gc) == true){			// run dat simulation button
			goTrans.setActivity(!goTrans.isActive());
			t = 0;
		}
		
		// Updates the position of the robot if the run dat simulation button is active
		if (goTrans.isActive() == true){
			dtGraph.update();	//|
			vtGraph.update();	//| Updates the graphs
			atGraph.update();	//|
			
			t += 1;				// Updates the time
			
			// Updates occur at the end of every cycle (marked by the variable t) otherwise the animation would 
			// 		really bad and stuff
			if (timeElapsed >= DELAY){
				timeElapsed = 0;
				goSim(dt, t);			// Actually updates the position of the robot 
			}
		}
	}

	// Calculates the position of the robot when the simulation is running
	public void goSim(int dt, int t){ 
		if (x < WinX){			// For as long as the robot is in the window
																						// It's a physics
			if (SimulationPhysics.getAccelSpeed(t) < SimulationPhysics.getSpeed(t)){	// As long as the robot is still accelerating
				x += (SimulationPhysics.getAccelSpeed(t)*6);
			}else {
				x += (SimulationPhysics.getSpeed(t)*6);									// When it reached a constant speed
			}
		}else{
			x = 200-robot.getWidth();	// Puts the robot back in the window
		}
	}
}

