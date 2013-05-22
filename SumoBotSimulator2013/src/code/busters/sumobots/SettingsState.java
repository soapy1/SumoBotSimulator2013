package code.busters.sumobots;

import java.awt.Font;

import code.busters.sumobots.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SettingsState extends BasicGameState {
	
	private StateBasedGame game;
	
	Color Background = new Color(0xfff4f4f4);
	
	public static int WinX = 800;			// Size of window
	public static int WinY = 600;
	
	private static Font defaultFont = new Font("Lucida Console", Font.PLAIN, 12);
	static TrueTypeFont defaultTTF = new TrueTypeFont(defaultFont, true);
		
	static TextFieldNew txtWinW;
	static TextFieldNew txtWinH;
	
	public int getID() {
		return GameStates.Settings.ordinal();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		//GUI.InitGUI(gc, sg);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
    	g.setColor(Background);
    	g.fillRect(0, 0, 1366, 768);  
    	//GUI.RenderGUI(gc, sg, g, "Settings");
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sg, int dt) throws SlickException {							
		GUI.UpdateGUI(gc, sg, dt);
	}	
}
