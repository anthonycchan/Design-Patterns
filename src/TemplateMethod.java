abstract class CaffeineBeverage {
	
	void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		if( customerWantsCondiments() ) {
			addCondiments();
		}
	}
	
	abstract void brew();
	abstract void addCondiments();
	
	void boilWater() {
		System.out.println("Boiling water");
	}
	
	void pourInCup() {
		System.out.println("Pouring in cup");
	}
	
	// This is a hook method
	boolean customerWantsCondiments() {
		return true;
	}
}

class Coffee extends CaffeineBeverage {
	
	Coffee(boolean addCondiments) {
		m_addCondiments = addCondiments;
	}
	
	@Override
	void brew() {
		// TODO Auto-generated method stub
		System.out.println("Brewing coffee");
	}

	@Override
	void addCondiments() {
		// TODO Auto-generated method stub
		System.out.println("Adding sugar and milk");
	}
	
	boolean customerWantsCondiments() {
		return m_addCondiments;
	}
	
	boolean m_addCondiments;
}

class Tea extends CaffeineBeverage {

	Tea(boolean addCondiments) {
		m_addCondiments = addCondiments;
	}
	
	@Override
	void brew() {
		// TODO Auto-generated method stub
		System.out.println("Brewing tea");
	}

	@Override
	void addCondiments() {
		// TODO Auto-generated method stub
		System.out.println("Adding honey and lemon");
	}
	
	boolean customerWantsCondiments() {
		return m_addCondiments;
	}
	
	boolean m_addCondiments;
}

public class TemplateMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CaffeineBeverage coffee = new Coffee(false);
		coffee.prepareRecipe();
		
		System.out.println("");
		
		CaffeineBeverage tea = new Tea(true);
		tea.prepareRecipe();
	}
}
