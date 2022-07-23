 import java.io.*;

public class RocketCalc implements Runnable {

	private double mass;
	private double burnTime;// total burn time in seconds
	private double totalFuelConsumption;// Total fuel consumed
	private double thrust;// in N'(s)
	private double altitude;
	private double velocity;
	private double aGravity;// Acceleration due to gravity
	private double calcsPerSecond;
	
	private RocketPanel panel;

	public RocketCalc(double aCalcsPerSecond,  double aMass,double aAltitude, double aBurnTime, double aTotalFuelConsumption,double atotalThrust, RocketPanel aPanel) {
         		
		mass = aMass;
		calcsPerSecond = aCalcsPerSecond;
		burnTime = aBurnTime;
		totalFuelConsumption = aTotalFuelConsumption;
		thrust = atotalThrust ;
		panel = aPanel ;
		altitude = aAltitude;
		velocity = 0;
		aGravity = 9.8;

	}// Constructor

	public void altitudeCalc() {

		
		double forceGravity = 0 / aGravity;
		double forceNet = 0;
		double acceleration = 0;
		double timeInterval = 1 / calcsPerSecond; 
		double fuelConsumption = (totalFuelConsumption * timeInterval) / burnTime; // fuel consumption per time interval

		double earthMass = Math.pow(10, 24) * 5.97;
		double gravityConstant = 0.0000000000667;//Math.pow(10, -11) * 6.67
		double radiusEarth = 6.38 * Math.pow(10, 6);
		double totalDistance = Math.pow((radiusEarth + this.getAltitude()), 2); // also is the total radius (r^2)
		
		double forceDrag = 0; 
		double airDensity = 1.225; // (kg/m^3)
		//double deltaAirDensity = 120;  
		//double newValue = 0;
		double crossSectionDiameter = 3.7; // (m)
		double crossSectionRadius = crossSectionDiameter/2;
		double radiusSquared = Math.pow(crossSectionRadius, 2);
		double crossSectionArea = Math.PI * radiusSquared;
		double dragCoefficient = 0.25; 
		double slopeOfAirDensity = (-0.0000765625 * altitude) + airDensity;
		//double velocity = 0;
		//double velocitySquared = Math.pow(velocity, 2);
		
		/*
		 * for loop to start at time = 0 and then increment by the time interval our
		 * example time interval is set at .1 seconds and goes to 162 seconds in .1
		 * second intervals
		 */
	
		//(0, 1.225) (16000, 0)
		//^ calculating the slope of air density 
		
		try {
	         System.setOut(new PrintStream(new FileOutputStream("RocketCalc.txt"))); 
			 }
			 catch (IOException e){
				 System.out.println("error creating file");
			 }
		
		
		
		for (double time = 0; time < burnTime; time += timeInterval) {
			
			
			this.setMass(this.getMass() - fuelConsumption);
			forceNet = (thrust - forceGravity) - forceDrag;
			forceGravity = (gravityConstant * this.getMass() * earthMass)/(totalDistance); // divided by r^2
			acceleration = forceNet / this.getMass();
			this.setVelocity(this.getVelocity() + acceleration * timeInterval);
			//for(double altitude = 0; altitude < 16000; altitude ++) {
			
				//equation 
				//forceDrag = newValue
				//newValue += deltaAirdensity
				//newValue = forceDrag
				
			
			//}
			this.setAltitude(this.getAltitude() + this.getVelocity() * timeInterval);		
			for(int x = 0; x < 16000; x++) {
				forceDrag = 0.5 * slopeOfAirDensity * crossSectionArea * dragCoefficient * this.getVelocity() *this.getVelocity();
				airDensity += slopeOfAirDensity;
				
				//newValue = forceDrag;
			}
			//System.out.println((int) this.getAltitude() + this.getVelocity() + time);
			panel.updateRocket((int) this.getAltitude(), this.getVelocity(), time);
			System.out.println("" + this.getAltitude() + "," + this.getVelocity() + "," + time + "," + this.getMass());
			

		}//for loop
		
		
		
	}//altitudeCalc

	/*
	 * This is the runs when you implement runnable and call the thread.start() with
	 * the falcon9 thread.
	 */
	public void run() {
		altitudeCalc();
	}

	// ***********************************************   Getters and Setters    *********************************************************
	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getBurnTime() {
		return burnTime;
	}

	public void setBurnTime(double burnTime) {
		this.burnTime = burnTime;
	}

	public double getTotalFuelConsumption() {
		return totalFuelConsumption;
	}

	public void setTotalFuelConsumption(double fuelConsumption) {
		this.totalFuelConsumption = fuelConsumption;
	}

	public double getThrust() {
		return thrust;
	}

	public void setThrust(double thrust) {
		this.thrust = thrust;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getCalcsPerSecond() {
		return calcsPerSecond;
	}

	public void setCalcsPerSecond(double calcsPerSecond) {
		this.calcsPerSecond = calcsPerSecond;
	}

}// RocketCalc class
