package code.busters.sumobots;

public class Wire {

	private double len, ohm, resistivity, area;
	private String circuitType;
	
	// Default constructor
	public Wire(){
		len = 0;
		ohm = 0;
		resistivity = 0;
		area = 0;
		circuitType = "";
	}
	
	// Better constructor
	public Wire (double l, double o, double r, double a, String c){
		len = l;
		ohm = o;
		resistivity = r;
		area = a;
		circuitType = c;
	}
	
	// Returns the length
	public double getLen(){
		return len;
	}
	
	// Returns the resistance
	public double getOhm(){
		return ohm;
	}
	
	// Returns the resistivity
	public double getResistivity(){
		return resistivity;
	}
	
	// Returns the area
	public double getArea(){
		return area;
	}
	
	// Returns the circuit type as a string (either series or parallel)
	public String getCircuitType(){
		return circuitType;
	}
	
	// Determines the circuit type
	public boolean isParallel(){
		if (circuitType.equalsIgnoreCase("parallel")){
			return true;
		}else{
			return false;
		}
	}	
	
	// Sets the length of the wire
	public void setLen(double d){
		len = d;
	}
	
	// Sets the resistance of the wire
	public void setOhm(double o){
		ohm = o;
	}
	
	// Sets the resistivity based on math
	public void setOhm(){
		ohm = (resistivity)*(len/area);
	}
	
	// Sets the resistivity
	public void setResistivity(double r){
		resistivity = r;
	}
	
	// Sets the cross sectional area
	public void setArea(double a){
		area = a;
	}
	
	// Sets the circuit type as either parallel or series
	public void setCircuitType(String s){
		if (s.equalsIgnoreCase("parallel") || s.equalsIgnoreCase("series")){
			circuitType = s;
		}else{
			System.err.println("you can not set the circuit type like that");
		}
	}

}
