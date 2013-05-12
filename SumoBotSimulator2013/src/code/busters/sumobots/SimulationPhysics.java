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
		return BuildState.roboMotorOne.getTorque()/(BuildState.roboWheel.getDiam()/(1*10E2));	
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
	
	public static double getAccel(){
		return (getForceApp()/BuildState.RWeight);
	}
	
	public static double getSpeed(){
		double s = BuildState.roboMotorOne.getSpeed()*(Math.PI*BuildState.roboWheel.getDiam()/((1*10E2)*60));
		return s;
	}
	
	public static double getDisplacement(int time){
		double d = getSpeed()*time;
		return d;
	}
	
	public static void setMoving(boolean move){
		moving = move; 
	}
	
	
}
