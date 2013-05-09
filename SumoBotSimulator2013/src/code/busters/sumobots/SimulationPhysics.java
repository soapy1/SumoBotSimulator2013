/*
 * Class that controls all the physics we need
 */

package code.busters.sumobots;

public class SimulationPhysics {
	
	private static boolean moving = false;
	
	public static double getForceApp(){
		return BuildState.roboMotorOne.getTorque()/(BuildState.roboMotorOne.getShaft()/(2*10E2));	
	}
	
	public static boolean getMoving(){
		return moving;
	}
	
	public static double getForceFriction(){
		double f;
		if (moving == true){
			f = BuildState.RWeight*BuildState.roboWheel.getMu();
		}else{
			f = 0;
		}
		return f;
	}
	
	public static double getNetForce(){
		return getForceApp()-getForceFriction();
	}
	
	public static double getWeight(){
		return BuildState.RWeight*9.8;
	}
	
	public static double getAccel(){
		return (getNetForce()/BuildState.RWeight);
	}
	
	public static double getSpeed(){
		double s = Math.sqrt(getForceApp()*(BuildState.roboWheel.getDiam()/2)/BuildState.RWeight);
		return s;
	}
	
	public static void setMoving(boolean move){
		moving = move; 
	}
	
	
}