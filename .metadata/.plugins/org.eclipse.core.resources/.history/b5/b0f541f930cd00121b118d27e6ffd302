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
	
	// Default constructor
	public PowerSupply(){
		voltage = 0;
		current = 0;
	}
	
	// Better constructor
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
	
	// Sets the voltage if the input is less than 12
	public void setVoltage(double v){
		voltage = v;
	}
	
	// Makes sure the user inputs a valid voltage
	public boolean validVoltage(){
		if (voltage <= 24){
			return true;
		}else{
			return false;
		}	
	}
	
	public void setCurrent(double c){
		current = c;
	}

}
