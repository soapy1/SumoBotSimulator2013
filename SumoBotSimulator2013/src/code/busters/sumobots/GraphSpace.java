package code.busters.sumobots;

/*
 * Class for creating and manipulating graphs
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class GraphSpace extends AbstractComponent {

	int gx, gy, gw, gh, equt;	// Variables for the position of the graph
	
	double gxv, gyv;
	
	boolean gbo;	// For tracking the position of the origin
	int dt = 0;
	
	double xscale = 1;		// x scale (pre-defined) 
	double yscale;			// y scale (user-defined)
	
	double xoffs = 0;
	double yoffs = 0;
	
	String graphID;		// Type of graph
	
	String graphType;	// Other thing for type of graph (it is needed for something different... just stop questioning out judgement)
	String readOut;	
	
	DecimalFormat df = new DecimalFormat("0.########");

	Color Background = new Color(0xfff4f4f4);		// Background colour for the graph
	
	ArrayList<Point> line = new ArrayList<Point>();		// The points in the graph
														// We are going to draw the graph like a polygone
	
	// Constructor... it just is
	public GraphSpace(GUIContext gc, int x, int y, int width, int height, boolean bottomOrigin, int equtype, String gr, double ys) {
		super(gc);
		gx = x;
		gy = y;
		gw = width;
		gh = height;
		gbo = bottomOrigin;
		equt = equtype;
		graphID = gr;
		yscale = ys;
		line.add(new Point(x, y+height));
	}
	
	// Returns the x position of the origin
	public int getOriginX() {
		return gx;
	}
	
	// Returns the y position of the origin
	public int getOriginY() {
		if (gbo == true) {
			return gy + gh;
		} else {
			return gy + (gh/2);
		}
		
	}
	
	@Override
	// Returns the height
	public int getHeight() {
		return gh;
	}

	@Override
	// Returns the width
	public int getWidth() {
		return gw;
	}

	@Override
	// Returns the x position
	public int getX() {
		return gx;
	}

	@Override
	// Returns the y position
	public int getY() {
		return gy;
	}
	
	// Does some calculations and displays the y position for the graph
	public double equate(double i, double xoffs, double yoffs, double xscale, double yscale, int type) {
		double x = 0, y = 0, yout = 0;
		x = (xscale * i) - xoffs;
		
		// Determines the type of graph based on the variable type
		switch (type) {
			
			// static-y graph
			default:
				if (gbo == true) {
				y = Math.random() * ((yscale*yscale) * Math.sin(Math.random())); 
				} else {
				y = (Math.random() * ((yscale*yscale) * Math.sin(Math.random()))) - (gh/yscale/2); 	
				}
			break;
			
			// Displacement time graph
			case 1:
				graphType = "dt";
				readOut = df.format((SimulationState.x - 200) / 6) + "m";
				y = SimulationState.x / MainSim.WinX * 15 * 2.4;
			break;
				
			// Velocity time graph
			case 2:
				graphType = "vt";
				readOut = df.format(SimulationPhysics.getSpeed(dt))  + "m/s";
				y = SimulationPhysics.getSpeed(dt) / MainSim.WinX * 15 * 2.4;
			break;
			
			// Acceleration time graph
			case 3:
				graphType = "at";
				readOut = df.format(SimulationPhysics.getAccel(dt)) + "m/s²";
				y = SimulationPhysics.getAccel(dt) / MainSim.WinX * 15 * 2.4;
			break;
		}
		
		yout = (yscale * -y) - yoffs;	// Calculates the y value
		return yout;					// Returns the y value
	}
	
	// Calculates the points in the graph given the x value (time)
	public void function(int t){
		// For velocity graph
		if (graphID.equalsIgnoreCase("vt")){
			line.add(new Point((float)(t*xscale + getOriginX()), (float) (getOriginY()-(SimulationPhysics.getSpeed(t)*yscale))));
		}
		// For displacement graph
		else if (graphID.equalsIgnoreCase("dt")){
			line.add(new Point((float)(t*xscale + getOriginX()), (float) (getOriginY()-(SimulationPhysics.getDisplacement(t)*yscale))));
		}
		// For acceleration graph
		else if (graphID.equalsIgnoreCase("at")){
			line.add(new Point((float)(t*xscale + getOriginX()), (float) (getOriginY()-(SimulationPhysics.getAccel(t)*yscale))));
		}
	}
	
	// Draws the graphs
	public void update() throws SlickException{
		dt += 1;	// keeps track of time
		
		function(dt);										// Calls the function method using time
		equate(1, xoffs, yoffs, xscale, yscale, equt);		// Calls the equate method
															//  	We probably should have put those two methods together but yolo
	}
	
	@Override
	public void render(GUIContext gc, Graphics g) throws SlickException {
		
		// Draws the Cartesian plane
		g.setColor(Color.white);
		g.fillRect(gx, gy, gw, gh);
		g.setColor(Color.black);
		g.drawLine(gx, gy, gx, gy + getHeight());
		g.drawLine(getOriginX(), getOriginY(), getOriginX() + gw - 1, getOriginY());
		
		Point ol, ne;	// Defines two points so that a line can be drawn between the and the user sees a curve
			
		g.setColor(Background);
		g.fillRect(gx, gy + gh + 5, gw, 15);
		g.setColor(Color.black);
		g.drawString(graphType + ": " + readOut, gx, gy + gh + 6);	// Prints info about the graph (the y position)
			
		g.setColor(Color.red);			// Colour of the curve
		
		// Draws the curve
		// Since we take two points separated by time t and draw a line in between them we are really just drawing a 
		//		series of tangent lines and not actually a curve.  It still looks nice though.
		for (int i = 0; i<line.size()-1; i ++){
			ol = line.get(i);			// Gets the value of the "old" point
			ne = line.get(i+1);			// Gets the value of the "new" point
			if (ne.getX()<getOriginX()+getWidth() && ne.getY()>getY() && ne.getY()<getY()+getHeight()){
				g.drawLine(ol.getX(), ol.getY(), ne.getX(), ne.getY());		// Draws a line between the old and new points
			}
		}
	}

	@Override
	// The x and y position of the graph
	public void setLocation(int x, int y) {
		gx = x;
		gy = y;		
	}
}
