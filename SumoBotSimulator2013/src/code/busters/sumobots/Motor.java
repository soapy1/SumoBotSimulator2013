/*
 * A class related to the electrical portion of the robot that defines the type of the motor in the robot.
 * Multiple instances of this class can be made in order to have several motors.  Currently, only two instance will
 * be produced because there are two motors.
 */

package code.busters.sumobots;

public class Motor {

	private double volt, current, speed, torque, shaft;
	
	// Default constructor
	public Motor(){
		volt = 0;
		current = 0;
		speed = 0;
		torque = 0;
		shaft = 0;
	}
	
	// Better constructor
	public Motor(double v, double c, double s, double t, double ds){
		volt = v;
		current = c;
		speed = s;
		torque = t;
		shaft = ds;
	}
	
	// User inputs voltage
	public void setVoltage(double v){
		volt = v;
	}
	
	//TODO: check with mr.song
	// Makes sure the user inputs a valid voltage
	public boolean validVoltage(){
		if (volt <= 24){
			return true;
		}else{
			return false;
		}	
	}
	
	// User inputs current
	public void setCurrent(double c){
		current = c;
	}
	
	// User inputs the diameter of drive shaft
	public void setShaft(double s){
		shaft = s;
	}
	
	// User input the rpm of the motor
	public void setSpeed(double s){
		speed = s;
	}
	
	// The torque (other)
	public void setTorque(double t){
		torque = t;
	}
	
	// The torque of the motor is calculated in NM
	// This method is made to adjust the torque when all the other variables are inputed
	public void setTorque(){
		//NOTE: assuming there is the same motor and both motors have enough voltage supplied to them
		if (BuildState.roboWire.isParallel() == true) {
			torque = (volt*(current/2)*9.554/speed);
		}else if (BuildState.roboWire.isParallel() == false){
			if ((volt*2) < BuildState.roboPS.getVoltage()){ 
				torque = ((volt/2)*(current)*9.554/speed);
			}else {
				//GUI.printMessage("Friendly Reminder: this robot will fail");
			}
		}
	
	}
	
	public double getVoltage(){
		return volt;
	}
	
	public double getCurrent(){
		return current;
	}
	
	public double getShaft(){
		return shaft;
	}
	
	public double getSpeed(){
		return speed;
	}
	
	public double getTorque(){
		return torque;
	}
	
}
