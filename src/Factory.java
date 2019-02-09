abstract class PizzaStore {
	public Pizza orderPizza(String type) {
		Pizza pizza;
		
		pizza = createPizza(type);
		
		pizza.prepare();
		
		return pizza;
	}
	
	protected abstract Pizza createPizza(String type);
}

class NYPizzaStore extends PizzaStore {
	IngredientFactory m_nyIngredients = new NYIngredients();
	
	protected Pizza createPizza(String type) {
		if(type == "Cheese")
			return new CheesePizza(m_nyIngredients);
		
		return null;
	}
}

//
// Pizza
//
abstract class Pizza {
	Cheese m_cheese;
	
	public abstract void prepare();
	
	public void box() {
		System.out.println("Boxing the pizza!");
	}
}

class CheesePizza extends Pizza{
	IngredientFactory m_ingredientFactory;
	
	CheesePizza(IngredientFactory ingr) {
		m_ingredientFactory = ingr;
	}
	public void prepare() {
		System.out.print("preparing cheese pizza");
		
		m_cheese = m_ingredientFactory.createCheese();
	}
}

//
// Ingredients
//
interface IngredientFactory {
	public abstract Cheese createCheese();
}

class NYIngredients implements IngredientFactory{
	public Cheese createCheese() {
		return new MozarellaCheese();
	}
}

interface Cheese {
}
class MozarellaCheese implements Cheese {
}
class CheddarCheese implements Cheese {
}

//
// Driver
//
public class Factory {
	public static void main(String[] args) {
		PizzaStore nyPizzaStore = new NYPizzaStore();
		Pizza pizza = nyPizzaStore.orderPizza("Cheese");
	}
}

//class SegmentTypeValidator {
//	hasApplicableSegmentType(types, segment) {
//		SegmentTypeChecker checker	
//
//		checker = getSegmentTypeChecker(type)
//
//		checker->isValid(segment);
//	}
//	
//	abstract SegmentTypeChecker getSegmentTypeChecker(type);
//}
//
//class SegmentTypeValidatorFlight implements SegmentTypeValidator {
//	SegmentUtility sutil;
//	
//	SegmentTypeValidatorFlight(flight) {
//		sutil = new FlightSegmentUtility(flight);
//	}
//	
//	SegmentTypeChecker getSegmentTypeChecker(type){
//		SegmentTypeChecker checker
//		if(type == diverted) {
//			checker = new DivertedChecker(sutil);
//		else if (type == continuation)
//			checker = new ContinuationChecker(sutil);
//		}
//		return checker
//	}
//}
//
//class SegmentTypeValidatorOffline implements SegmentTypeValidator {
//	SegmentUtility sutil;
//	
//	SegmentTypeValidatorOffline(offline) {
//		sutil = new OfflineSegmentUtility(offline);
//	}
//	
//	SegmentTypeChecker getSegmentTypeChecker(type){
//		SegmentTypeChecker checker
//		if(type == diverted) {
//			checker = new DivertedChecker(sutil);
//		else if (type == continuation)
//			checker = new ContinuationChecker(sutil);
//		}
//		return checker
//	}
//}
//
//class FlightSegmentUtility implements SegmentUtility {
//	Flight m_flight
//	
//	FlightSegmentUtility(flight)
//	{
//		m_flight = flight
//	}
//	
//	bool isOfState(state) {
//		return m_flight.isState(state)
//	}
//}
//
//class OfflineSegmentUtility implements SegmentUtility{
//	Offline m_offline
//	
//	FlightSegmentUtility(offline)
//	{
//		m_offline = offline
//	}
//	
//	bool isOfState(state) {
//		return m_offline.isState(state)
//	}
//}
//
//
//class SegmentTypeChecker {
//	bool isOfState(segment) = 0;
//}
//
//class DivertedChecker implements SegmentTypeChecker {
//	SegmentUtility m_sutil;
//	
//	DivertedChecker(SegmentUtility sutil) {
//		m_sutil = sutil
//	}
//	
//	bool isValid() {
//		return m_sutil.isOfState(Divert)
//	}
//}
//
//class ContinuationChecker implements SegmentTypeChecker {
//	SegmentUtility m_sutil;
//	
//	ContinuationChecker(SegmentUtility sutil) {
//		m_sutil = sutil
//	}
//	
//	bool isValid() {
//		return m_sutil.isOfState(Continuation)
//	}
//}
//
//main() {
//	SegmentTypeValidator stValidator
//	if(flight)
//		stValidator = new SegmentTypeValidatorFlight(flight)
//	else if(offline)
//		stValidator = new SegmentTypeValidatorOffline(offline)
//	
//	stValidator.hasApplicableSegmentType(diverted, segment)
//	
//}

