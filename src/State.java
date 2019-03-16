class CokeDispenserState {
	void insertMoney(int amount) {
		System.out.println("Insert money error");
	}
	void selectCoke() {
		System.out.println("Select coke error");
	}
	void dispense() {
		System.out.println("Dispense coke error");
	}
	void refillCoke(int numCoke) {
		System.out.println("Refill coke error");
	}
}

class NoMoney extends CokeDispenserState {
	CokeDispenser m_cokeDispenser;
	NoMoney(CokeDispenser cokeDispenser) {
		m_cokeDispenser = cokeDispenser;
	}
	
	void insertMoney(int amount) {
		m_cokeDispenser.addMoney(amount);
		
		int total = m_cokeDispenser.getTotalMoney();
		int price = m_cokeDispenser.getCokePrice();
		if ( total >= price ) {
			m_cokeDispenser.setChange(total - price);
			
			m_cokeDispenser.setState(m_cokeDispenser.getHasMoneyState());
		}
		else
			System.out.println("not enough money");
	}
}

class HasMoney extends CokeDispenserState {
	CokeDispenser m_cokeDispenser;
	HasMoney(CokeDispenser cokeDispenser) {
		m_cokeDispenser = cokeDispenser;
	}
	
	void selectCoke() {
		m_cokeDispenser.setState(m_cokeDispenser.getSoldState());
	}
}

class Sold extends CokeDispenserState {
	CokeDispenser m_cokeDispenser;
	Sold(CokeDispenser cokeDispenser) {
		m_cokeDispenser = cokeDispenser;
	}
	
	void dispense() {
		m_cokeDispenser.dispenseCoke();
		m_cokeDispenser.returnChange();
		
		if(m_cokeDispenser.getCokeCount() > 0) 
			m_cokeDispenser.setState(m_cokeDispenser.getNoMoneyState());
		else
			m_cokeDispenser.setState(m_cokeDispenser.getSoldOutState());
	}
}

class SoldOut extends CokeDispenserState {
	CokeDispenser m_cokeDispenser;
	SoldOut(CokeDispenser cokeDispenser) {
		m_cokeDispenser = cokeDispenser;
	}
	
	void refillCoke(int numCoke) {
		m_cokeDispenser.setCokeCount(numCoke);
		m_cokeDispenser.setState(m_cokeDispenser.getNoMoneyState());
	}
}

class CokeDispenser {
	private CokeDispenserState noMoneyState;
	private CokeDispenserState hasMoneyState;
	private CokeDispenserState soldState;
	private CokeDispenserState soldOutState;
	
	private CokeDispenserState m_state;
	int m_totalMoney;
	
	int m_cokeCount;
	
	public CokeDispenser() {
		// TODO Auto-generated method stub
		noMoneyState = new NoMoney(this);
		hasMoneyState = new HasMoney(this);
		soldState = new Sold(this);
		soldOutState = new SoldOut(this);
		
		m_state = soldOutState;
		
		m_state.refillCoke(3);
		
		m_totalMoney = 0;
	}
	
	public CokeDispenserState getNoMoneyState() { return noMoneyState; }
	public CokeDispenserState getHasMoneyState() { return hasMoneyState; }
	public CokeDispenserState getSoldState() { return soldState; }
	public CokeDispenserState getSoldOutState() { return soldOutState; }
	
	void insertMoney(int amount) {
		m_state.insertMoney(amount);
	}
	void selectCoke() {
		m_state.selectCoke();
		m_state.dispense();
	}
	void dispenseCoke() {
		System.out.println("Here's a coke. Your balance is: " + m_totalMoney);
		m_cokeCount--;
	}
	
	void setState(CokeDispenserState state) {
		m_state = state;
	}
	
	void addMoney(int amount) {
		m_totalMoney += amount;
	}
	
	public int getTotalMoney() { return m_totalMoney; }
	public int getCokePrice() { return 1; }
	public int getCokeCount() { return m_cokeCount; }
	public void setCokeCount(int cokeCount) { m_cokeCount = cokeCount; }
	public void setChange(int change) {  
		m_totalMoney = change;
	}
	public int returnChange() { 
		return m_totalMoney;
	}
}

public class State {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CokeDispenser dispenser = new CokeDispenser();
		dispenser.insertMoney(2);
		dispenser.selectCoke();
		dispenser.insertMoney(3);
		dispenser.selectCoke();
	}

}
