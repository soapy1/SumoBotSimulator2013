/*
 * Class that controls all the physics we need
 */

package code.busters.sumobots;

public class SimulationPhysics {
	
	private static boolean moving = false;
	
	public static boolean getMoving(){
		return moving;
	}
	
	// Applied force
	public static double getForceApp(){
		return BuildState.roboMotorOne.getTorque()/(BuildState.roboWheel.getDiam()/(2*10E2));	
	}
	
	// Force of static friction
	public static double getForceStaticFriction(){
		double f;
		f = getWeight()*BuildState.roboWheel.getMu();
		return f;
	}
	
	// Force of gravity down
	public static double getWeight(){
		return BuildState.RWeight*9.8;
	}

	// Returns the maximum acceleration 
	//	Use this value to calculate the displacement of the robot while it is still accelerating and for the info pane
	public static double getMaxAccel(){
		double accel;
		if (BuildState.RWeight == 0){
			accel = 0;
		}else{
			accel = (getForceApp()/BuildState.RWeight);
		}
		return accel;
	}
	
	// Gets the true acceleration of the robot
	public static double getAccel(int t){
		double accel;
		if (getAccelSpeed(t) <  getMaxSpeed()){
			accel = getMaxAccel();
		}else {
			accel = 0;
		}
		return accel;
	}
	
	// Returns the acceleration speed of the robot
	public static double getAccelSpeed(int time){
		double a = getMaxAccel()*time;
		return a;
	}
	
	// The maximum speed of the robot
	// Used mostly for the info pane
	public static double getMaxSpeed(){
		double s = BuildState.roboMotorOne.getSpeed()*(Math.PI*BuildState.roboWheel.getDiam()/((2*10E2)*60));
		return s;
	}
	
	// The true speed of the robot
	// Use this value to determine the displacement when the robot is no longer accelerating
	public static double getSpeed(int t){
		double s;
		if (getAccelSpeed(t) <  getMaxSpeed()){
				s = getAccelSpeed(t);
		} else {
			s = BuildState.roboMotorOne.getSpeed()*(Math.PI*BuildState.roboWheel.getDiam()/((2*10E2)*60));
		}
		return s;
	}
	
	// Gets the displacement of the robot
	public static double getDisplacement(int time){
		double d;
		if (SimulationState.x == 0){		// The x position of the robot in the simulation state
			d = 0;							// Resets the displacement
		}else{
			d = getSpeed(time)*time;		// Gets the displacement
		}
		return d;
	}
	
	// Sets the move variable
	// Only useful if you need to calculate the force of kinetic friction
	public static void setMoving(boolean move){
		moving = move; 
	}
}
