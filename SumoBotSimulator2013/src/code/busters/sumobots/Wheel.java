/*
 * A class related to the mechanical portion of the robot, specifically the the wheels of the robot.
 * Multiple instances of this class can be made in order to have different wheels.  Currently, only one instance will
 * be produced because there is only on set of identical wheels.
 */

package code.busters.sumobots;

public class Wheel {

	private double diam, mu;
	
	// constructor 
	public Wheel (){
		diam = 0;
		mu = 0;
	}
	
	// Better constructor
	public Wheel(double diameter, double coefficientFriction){
		diam = diameter;
		mu = coefficientFriction;
	}
	
	// Sets the diameter of the wheel
	public void setDiam(double d){
		diam = d;
	}
	
	// Sets the coefficient of friction
	public void setMu(double m){
		mu = m;
	}
	
	// Makes sure the the coefficient of friction is between 0 and 1
	public boolean validMu(){
		if (mu <= 1 && mu >= 0){
			return true;
		}else{
			return false;
		}	
	}
	
	// Returns the diameter of the wheel 
	public double getDiam(){
		return diam;
	}
	
	// Returns the coefficient of friction
	public double getMu(){
		return mu;
	}
}
