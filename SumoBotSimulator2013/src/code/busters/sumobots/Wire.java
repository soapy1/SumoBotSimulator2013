package code.busters.sumobots;

public class Wire {

	private double len, ohm, resistivity, area;
	private String circuitType;
	
	public Wire(){
		len = 0;
		ohm = 0;
		resistivity = 0;
		area = 0;
		circuitType = "";
	}
	
	public Wire (double l, double o, double r, double a, String c){
		len = l;
		ohm = o;
		resistivity = r;
		area = a;
		circuitType = c;
	}
	
	public double getLen(){
		return len;
	}
	
	public double getOhm(){
		return ohm;
	}
	
	public String getOhmString(){
		return Double.toString(ohm);
	}
	
	public double getResistivity(){
		return resistivity;
	}
	
	public double getArea(){
		return area;
	}
	
	public String getCircuitType(){
		return circuitType;
	}
	
	public boolean isParallel(){
		if (circuitType.equalsIgnoreCase("parallel")){
			return true;
		}else{
			return false;
		}
	}	
	
	public void setLen(double d){
		len = d;
	}
	
	public void setOhm(double o){
		ohm = o;
	}
	
	public void setOhm(){
		ohm = (resistivity)*(len/area);
	}
	
	public void setResistivity(double r){
		resistivity = r;
	}
	
	public void setArea(double a){
		area = a;
	}
	
	public void setCircuitType(String s){
		if (s.equalsIgnoreCase("parallel") || s.equalsIgnoreCase("series")){
			circuitType = s;
		}else{
			System.err.println("you can not set the circuit type like that");
		}
	}

}
