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

public class SimulationState extends BasicGameState {
	
	
	public int getID() {
		// TODO Auto-generated method stub
		return GameStates.Simulation.ordinal();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		BuildState.simFlag = true;
		BuildState.buildFlag = false;
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sg, int dt) throws SlickException {
		Input input = gc.getInput();
		//if (input.isKeyPressed(input.KEY_ESCAPE)) {
		// sg.enterState(GameStates.Build.ordinal(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		//}
	}
}
