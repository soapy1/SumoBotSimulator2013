/*
 * A class related to the mechanical portion of the robot.
 * Multiple instances of this class can be made in order to have different wheels.  Currently, only one instance will
 * be produced because there is only on set of identical wheels.
 */

public class Wheel {

	private double diam, mu;
	
	public Wheel(double diameter, double coefficientFriction){
		diam = diameter;
		mu = coefficientFriction;
	}
	
	
	public void setDiam(double d){
		diam = d;
	}
	
	public void setMu(double m){
		mu = m;
	}
	
	public double getDiam(){
		return diam;
	}
	
	public double getMu(){
		return mu;
	}
}
