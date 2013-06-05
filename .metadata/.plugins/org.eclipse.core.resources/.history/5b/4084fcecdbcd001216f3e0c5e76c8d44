package code.busters.sumobots;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

public class TextFieldNew extends TextField {
	
	Graphics g;
	GUIContext gc;
	private String tooltip;
	static Color tip = new Color(0x88000000);
	float tx, ty, tw, th;
	String[] body;
	int tipw, tiph;
	int ltipw = 0;
	
	public TextFieldNew(GUIContext cn, Font font, int x, int y,
			int width, int height, String tool) {
		super(cn, font, x, y, width, height);
		tooltip = tool;
		tx = x;
		ty = y;
		tw = width;
		th = height;
		gc = cn;
	}
		
	public float MouseX() {
		return container.getInput().getMouseX(); 
	}
	
	public float MouseY() {
		return container.getInput().getMouseY(); 
	}
	
	public float XOffset() {
		float xoffset = 0;
		if (MouseX() + 12 + (tipw * 7 + 4) > MainSim.WinX) {
			xoffset = (MouseX() + 12 + (tipw * 7 + 4)) - MainSim.WinX;
		} else {
			xoffset = 0;
		}
		return xoffset;
	}
	
	public float YOffset() {
		float yoffset = 0;
		if (MouseY() + 12 + (tiph * 10 + 6) > MainSim.WinY) {
			yoffset = (MouseY() + 12 + (tiph * 10 + 6)) - MainSim.WinY;
		} else {
			yoffset = 0;
		}
		return yoffset;
	}
			
	public void ToolTipRender(Graphics g) {
		if ((MouseX() > tx) & (MouseX() < tx + tw) & (MouseY() > ty) & (MouseY() < ty + th)) {
			if (this.isAcceptingInput() == true) {
				String source = tooltip;
				String delims = "[//]+";
				String[] tokens = source.split(delims);
				ltipw = tokens[0].length();
				
				for (int i = 0; i < tokens.length; i++) {
					if (tokens[i].length() > ltipw) {
						tipw = tokens[i].length();
					}
					if (tokens[i].length() > tipw) {
						tipw = tokens[i].length();
					}
				}
				
				tiph = tokens.length;
				
				g.setColor(tip);
				g.fillRect(MouseX() + 12 - XOffset(), MouseY() + 12 - YOffset(), tipw * 7 + 4, tiph * 10 + 6);
				
				g.setColor(Color.white);
				g.setFont(GUI.defaultTTF);
				for (int i = 0; i < tokens.length; i++) {
				
					if (i==0) {
						g.drawString(tokens[0], MouseX() + 8 + 6 - XOffset(), MouseY() + 12 + 2 - YOffset());
						g.drawString(tokens[0], MouseX() + 8 + 6 + 1 - XOffset(), MouseY() + 12 + 2 - YOffset());
					} else {
						g.drawString(tokens[i], MouseX() + 8 + 6 - XOffset(), MouseY() + 12 + 2 + (i*10) - YOffset());
					}
					
				}
			}
		}
	}
}
	
