/*
 * A class related to the electrical portion of the robot.
 * Multiple instances of this class can be made in order to have several motors.  Currently, only two instance will
 * be produced because there are two motors.
 */

public class Motor {

	private double volt, current, speed, torque, shaft;
	
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
	
	// The torque of the motor is calculated in NM
	public void setTorque(){
		torque = (volt*current*9.554/speed);
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
