abstract class Vehicle {
	public abstract double getCost();
}

// 
// Concrete classes
//
class Economy extends Vehicle {
	public double getCost() {
		return 20.0;
	}
}

class Midsize extends Vehicle {
	public double getCost() {
		return 25.0;
	}
}

//
// Decorator classes
//
abstract class FeatureDecorator extends Vehicle{
	public abstract double getCost();
}
class GPS extends FeatureDecorator {
	Vehicle m_vh;
	GPS(Vehicle vh){
		m_vh = vh;
	}
	
	public double getCost() {
		return 15.0 + m_vh.getCost();
	}
	
}
class Insurance extends FeatureDecorator {
	Vehicle m_vh;
	Insurance(Vehicle vh) {
		m_vh = vh;
	}
	public double getCost() {
		return 30.0 + m_vh.getCost();
	}
}

//
// Driver
//
public class Decorator {

	public static void main(String[] args) {
		Economy ecoCar1 = new Economy();
		GPS gps = new GPS(ecoCar1);
		Insurance insurance = new Insurance(gps);
		
		System.out.print(insurance.getCost());
	}
}
