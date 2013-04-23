/*
 * A class related to the electrical portion of the robot that defines the power supply used by the robot;
 * One instance of this class will be created since the robot will only have one power supply.  If the user intends
 * too set up their power supply in series or parallel, they can account for the difference by calculating the overall
 * voltage and current of their power supply.
 */

//TODO: set the restrictions

package code.busters.sumobots;

public class PowerSupply {
	
	private double voltage, current;
	
	public PowerSupply(){
		voltage = 0;
		current = 0;
	}
	
	public PowerSupply(double v, double c){
		voltage = v;
		current = c;
	}
	
	public double getVoltage(){
		return voltage;
	}
	
	public double getCurrent(){
		return current;
	}
	
	public void setVoltage(double v){
		if (v > 12){
			System.err.println("invalid voltage for the power supply");
		}else{
			voltage = v;	
		}
	}
	
	public void setCurrent(double c){
		current = c;
	}

}
