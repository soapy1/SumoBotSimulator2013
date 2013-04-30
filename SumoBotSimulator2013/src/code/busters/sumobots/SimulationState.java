package code.busters.sumobots;

import code.busters.sumobots.GameStates;
import code.busters.sumobots.MainSim;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


//TODO: go back from simulation state

public class SimulationState extends BasicGameState {
	
	private StateBasedGame game;
	
	Color Background = new Color(0xfff4f4f4);
	
	public static int WinX = 800;
	public static int WinY = 600;
	public static boolean WinF = false;
	
	public int getID() {
		// TODO Auto-generated method stub
		return GameStates.Simulation.ordinal();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		game = sg;
		gc.setShowFPS(false);
		gc.setVSync(true);
		BuildState.simFlag = true;
		BuildState.buildFlag = false;
		
		GUI.InitGUISim(gc, sg);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
		g.setColor(Background);
    	g.fillRect(0, 0, 1366, 768);
    	GUI.RenderGUI(gc, sg, g, "InfoPane");
    	GUI.RenderGUI(gc, sg, g, "Tabs");
    	GUI.UpdateInfo(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sg, int dt) throws SlickException {
		if (GUI.fancyMech.buttonClicked(gc) == true){
			sg.enterState(GameStates.Build.ordinal());
		}else if (GUI.fancyElec.buttonClicked(gc) == true){
			sg.enterState(GameStates.Build.ordinal());
		}

	}
}
