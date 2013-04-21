/*
 * A class for buttons using Slick2d.  Instances of this class will allow the user to interact with the program with
 * ease.  In other words if this class dies so does Sumo Bot Simulator 2013.
 */

package code.busters.sumobots;

import org.newdawn.slick.*;

import java.awt.Font;

import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class Button extends AbstractComponent {
	
	private float buttonX;				// Button's x position
	private float buttonY;				// Button's y position
	private int buttonW;				// Button's width
	private int buttonH;				// Button's height
	private boolean active = false;		// Button's status
	private Color buttonColour;			// Button's colour (because we are Canadian)
	private Color buttonTextColour;		// Button's text colour
	private String buttonTextOld;		
	
	private Font defaultFont = new Font("Lucida Console", Font.PLAIN, 12);
	TrueTypeFont defaultTTF = new TrueTypeFont(defaultFont, true);
	
	// Default constructor
	public Button(GUIContext container) {
		super(container);
	}

	// Better constructor that defines all the properties of the button
	public Button(GUIContext gc, float x, float y, int width, int height, Color colour, Color textColour, String text) throws SlickException {
		super(gc);
		buttonX = x;
		buttonY = y;
		buttonW = width;
		buttonH = height;
		buttonColour = colour;
		buttonTextColour = textColour;
		buttonTextOld = text;
		buttonW = (int)(buttonTextOld.length() * 7 + 6);		
	}
	
	public int getX() {
		return (int)(buttonX);
	}
	
	public int getY() {
		return (int)(buttonY);
	}

	public int getHeight() {
		return (int)(buttonH);
	}

	public int getWidth() {
		return (int)(buttonW);
	}
	
	// Draws the button 
	public void render(GUIContext gc, Graphics g) throws SlickException {
		g.setColor(buttonColour);
		g.fillRect(buttonX, buttonY, buttonW, buttonH);
		g.setColor(buttonTextColour);
		g.setFont(defaultTTF);
		g.drawString(buttonTextOld, buttonX + 3, buttonY + 3);
		g.setColor(Color.transparent);
		g.fillRect(buttonX, buttonY, buttonW, buttonH);
	}

	// Sets the x and y location of the button measured from the top left corner
	public void setLocation(int x, int y) {
		buttonX = (float)x;
		buttonY = (float)y;
		
	}
	
	// Method that checks if the mouse is over the button area
	public boolean mouseOverArea(GUIContext gc){
		Input input = gc.getInput();	// Gets the input from gc
		int mx = input.getMouseX();		// Gets the x position of the mouse
		int my = input.getMouseY();		// Gets the y position of the mouse
		
		if (mx > buttonX && mx < (buttonX + buttonW) && my > buttonY && my < (buttonY + buttonH)){	// When the mouse 
			return true;																			// is inside the button area
		}else{																						// button area
			return false;
		}
	}
		
	// Method that changes the colour of the button
	public void changeColour(Color colour){
		buttonColour = colour;
	}
	
	// Redundant method that checks if the button is activated
	public boolean isActive(){
		return active;
	}
	
	// Checks if the button is active
	public void setActivity(boolean state){
		active = state;
	}
	
	// Method that says when the button is clicked
	// Note:  when you use this method you should also probably set the button to active.  
	public boolean buttonClicked(GUIContext gc){
		Input input = gc.getInput();	// Gets the input from gc
		int mx = input.getMouseX();		// Gets the x position of the mouse
		int my = input.getMouseY();		// Gets the y position of the mouse
		
		// Makes sure the mouse is in the confines of the button 
		if (mx > buttonX && mx < (buttonX + buttonW) && my > buttonY && my < (buttonY + buttonH)){		
			// If the mouse is clicked while it is in the button area then the button is clicked or Bob's my uncle.
			// 		Hint: Bob is not my uncle.
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) || input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
				return true;
			}else
				return false;
		}else
			return false;
		}
}
