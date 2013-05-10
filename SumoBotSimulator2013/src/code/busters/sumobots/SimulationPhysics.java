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
		return BuildState.roboMotorOne.getTorque()/(BuildState.roboMotorOne.getShaft()/(2*10E2));	
	}
	
	// Force of kinetic friction
	public static double getForceFriction(){
		double f;
		//if (moving == true){
		f = getWeight()*BuildState.roboWheel.getMu();
		//}else{
		//	f = 0;
		//}
		return f;
	}
	
	// Net force
	public static double getNetForce(){
		return getForceApp()-getForceFriction();
	}
	
	// Force of gravity down
	public static double getWeight(){
		return BuildState.RWeight*9.8;
	}
	
	public static double getAccel(){
		return (getNetForce()/BuildState.RWeight);
	}
	
	public static double getSpeed(){
		double s = BuildState.roboMotorOne.getSpeed()*(2*Math.PI*BuildState.roboMotorOne.getShaft()/((1*10E2)*60));
		return s;
	}
	
	public static void setMoving(boolean move){
		moving = move; 
	}
	
	
}
