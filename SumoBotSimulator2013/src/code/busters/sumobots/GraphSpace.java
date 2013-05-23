package code.busters.sumobots;

//http://www.java-forums.org/java-awt/9319-how-draw-polygon-java.html
//http://docs.oracle.com/javase/1.5.0/docs/api/java/awt/Polygon.html
//http://www.java-gaming.org/index.php/topic,20107.
//http://www.smartwerkz.com/svn/public/projects/Slick/trunk/src/org/newdawn/slick/tests/PolygonTest.java

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class GraphSpace extends AbstractComponent {

	int gx, gy, gw, gh, equt;
	
	double gxv, gyv;
	
	boolean gbo;
	int dt = 0;
	
	double xscale = 4;
	double yscale;
	
	double xoffs = 0;
	double yoffs = 0;
	
	String graphID;		// Type of graph
	
	String graphType;
	String readOut;
	
	DecimalFormat df = new DecimalFormat("0.########");

	Color Background = new Color(0xfff4f4f4);
	
	//Polygon line = new Polygon();
	ArrayList<Point> line = new ArrayList<Point>();
	
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
				readOut = df.format(SimulationPhysics.getSpeed(dt))  + "m/s";
				y = SimulationPhysics.getSpeed(dt) / MainSim.WinX * 15 * 2.4;
			break;
			
			case 3:
				graphType = "at";
				readOut = df.format(SimulationPhysics.getAccel(dt)) + "m/s²";
				y = SimulationPhysics.getAccel(dt) / MainSim.WinX * 15 * 2.4;
			break;
		}
		
		yout = (yscale * -y) - yoffs;
		return yout;
	}
	
	
	public void function(int t){
		if (graphID.equalsIgnoreCase("vt")){
			line.add(new Point((float)(t*xscale + getOriginX()), (float) (getOriginY()-(SimulationPhysics.getSpeed(t)*yscale))));
		}else if (graphID.equalsIgnoreCase("dt")){
			line.add(new Point((float)(t*xscale + getOriginX()), (float) (getOriginY()-(SimulationPhysics.getDisplacement(t)*yscale))));
		}else if (graphID.equalsIgnoreCase("at")){
			line.add(new Point((float)(t*xscale + getOriginX()), (float) (getOriginY()-(SimulationPhysics.getAccel(t)*yscale))));
		}
	}
	
	public void update() throws SlickException{
		dt += 1;
		
		function(dt);
		equate(1, xoffs, yoffs, xscale, yscale, equt);
	}
	
	@Override
	public void render(GUIContext gc, Graphics g) throws SlickException {
		
		// Draws the Cartesian plane
		g.setColor(Color.white);
		g.fillRect(gx, gy, gw, gh);
		g.setColor(Color.black);
		g.drawLine(gx, gy, gx, gy + getHeight());
		g.drawLine(getOriginX(), getOriginY(), getOriginX() + gw - 1, getOriginY());
		
		Point ol, ne;
			
		g.setColor(Background);
		g.fillRect(gx, gy + gh + 5, gw, 15);
		g.setColor(Color.black);
		g.drawString(graphType + ": " + readOut, gx, gy + gh + 6);
			
		for (int i = 0; i<line.size()-1; i ++){
			ol = line.get(i);
			ne = line.get(i+1);
			if (ne.getX()<getOriginX()+getWidth() && ne.getY()>getY() && ne.getY()<getY()+getHeight()){
				g.drawLine(ol.getX(), ol.getY(), ne.getX(), ne.getY());
			}
		}
			
			/*
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
			
			//g.draw(line);
			g.setColor(Background);
			g.fillRect(gx, gy + gh + 5, gw, 15);
			g.setColor(Color.black);
			g.drawString(graphType + ": " + readOut, gx, gy + gh + 6);
			*/
	//	}
	}

	@Override
	public void setLocation(int x, int y) {
		gx = x;
		gy = y;		
	}
	
	
	
}
