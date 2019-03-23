class Mediator {
	private int m_price;
	
	public void setPrice(int price) { m_price = price; }
	public int getPrice() { return m_price; }
}

class StockInformationProvider2 {
	Mediator m_mediator;
	public StockInformationProvider2(Mediator med) {
		m_mediator = med;
	}
	public void setPrice(int price) {
		m_mediator.setPrice(price);
	}
}

class SimpleAverageSubscriber2 {
	Mediator m_mediator;
	public SimpleAverageSubscriber2(Mediator med) {
		m_mediator = med;
	}
	public int getSimpleAverage() {
		return m_mediator.getPrice();
	}
}

class MovingAverageSubscriber2 {
	Mediator m_mediator;
	public MovingAverageSubscriber2(Mediator med) {
		m_mediator = med;
	}
	public int getMovingAverage() {
		return (m_mediator.getPrice() + m_mediator.getPrice());
	}
}

public class MediatorPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mediator med = new Mediator();
		StockInformationProvider2 provider = new StockInformationProvider2(med);
		provider.setPrice(10);
		SimpleAverageSubscriber2 simple = new SimpleAverageSubscriber2(med);
		MovingAverageSubscriber2 moving = new MovingAverageSubscriber2(med);
		
		System.out.println("Simple: " + simple.getSimpleAverage() + " Moving: " + moving.getMovingAverage());
	}

}
