package code.busters.sumobots;

import java.text.DecimalFormat;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class GraphSpace extends AbstractComponent {

	int gx, gy, gw, gh, equt;
	
	double gxv, gyv;
	
	boolean gbo;
	
	String graphType;
	String readOut;
	
	DecimalFormat df = new DecimalFormat("0.########");

	Color Background = new Color(0xfff4f4f4);
		
	public GraphSpace(GUIContext gc, int x, int y, int width, int height, boolean bottomOrigin, int equtype) {
		super(gc);
		gx = x;
		gy = y;
		gw = width;
		gh = height;
		gbo = bottomOrigin;
		equt = equtype;
	}
	
	public int getOriginX() {
		return gx;
	}
	
	public int getOriginY() {
		if (gbo == true) {
			return gy + gh;
		} else {
			return gy + (gh/2);
		}
		
	}
	
	@Override
	public int getHeight() {
		return gh;
	}

	@Override
	public int getWidth() {
		return gw;
	}

	@Override
	public int getX() {
		return gx;
	}

	@Override
	public int getY() {
		return gy;
	}
	

	public double equate(double i, double xoffs, double yoffs, double xscale, double yscale, int type) {
		double x = 0, y = 0, yout = 0;
		x = (xscale * i) - xoffs;
		switch (type) {
			
			default:
				if (gbo == true) {
				y = Math.random() * ((yscale*yscale) * Math.sin(Math.random())); 
				} else {
				y = (Math.random() * ((yscale*yscale) * Math.sin(Math.random()))) - (gh/yscale/2); 	
				}
			break;
			
			case 1:
				graphType = "dt";
				readOut = df.format((SimulationState.x - 200) / 6) + "m";
				y = SimulationState.x / MainSim.WinX * 15 * 2.4;
			break;
				
			case 2:
				graphType = "vt";
				readOut = df.format(SimulationPhysics.getSpeed())  + "m/s";
				y = SimulationPhysics.getSpeed() / MainSim.WinX * 15 * 2.4;
			break;
			
			case 3:
				graphType = "at";
				readOut = df.format(SimulationPhysics.getMaxAccel()) + "m/s²";
				y = SimulationPhysics.getMaxAccel() / MainSim.WinX * 15 * 2.4;
			break;
			
		}
		
		yout = (yscale * -y) - yoffs;
		return yout;
	}
	
	@Override
	public void render(GUIContext gc, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(gx, gy, gw, gh);
		g.setColor(Color.black);
		g.drawLine(gx, gy, gx, gy + getHeight());
		g.drawLine(getOriginX(), getOriginY(), getOriginX() + gw - 1, getOriginY());
		
		
		double xscale = 4;
		double yscale = 4;
		
		double xoffs = 0;
		double yoffs = 0;
				
		for (int i = 0; i <= gw; i++) {
			double lx = i;
			double ly = equate(lx, xoffs, yoffs, xscale, yscale, equt);
			double x =  i + 1;
			double y = equate(x, xoffs, yoffs, xscale, yscale, equt);
			
			float dlx = (float)(getOriginX() + lx);
			float dly = (float)(getOriginY() + ly);
			float dx = (float)(getOriginX() + x);
			float dy = (float)(getOriginY() + y);
						
			if (dlx < getOriginX() || dx > getOriginX() + gw) {	
				
			} else if (dly > gy + gh || dy < gy) {
								
			} else {
				g.setColor(Color.red);
				g.drawLine(dlx, dly, dx, dy);
			}
			
			g.setColor(Background);
			g.fillRect(gx, gy + gh + 5, gw, 15);
			g.setColor(Color.black);
			g.drawString(graphType + ": " + readOut, gx, gy + gh + 6);
		}
	}

	@Override
	public void setLocation(int x, int y) {
		gx = x;
		gy = y;		
	}
	
	
	
}
