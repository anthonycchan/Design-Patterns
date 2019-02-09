import java.util.ArrayList;

//
// Provider classes
//
interface Subject {
	public void registerObserver(Observer ob);
	public void removeObserver(Observer ob);
	public void notifyObservers();
}

class StockInformationProvider implements Subject{
	private ArrayList<Observer> m_observers;
	private double m_price;
	
	public StockInformationProvider() {
		m_observers = new ArrayList<Observer>();
	}
	
	public void registerObserver(Observer ob) {
		m_observers.add(ob);
	}
	
	public void removeObserver(Observer ob) {
		int index =m_observers.indexOf(ob);
		if(index >= 0)
			m_observers.remove(index);
	}
	
	public void notifyObservers() {
		for(Observer obs : m_observers) {
			obs.update(m_price);
		}
	}
	
	public void setPrice(double newPrice){
		m_price = newPrice;
		notifyObservers();
	}
}

//
// Subscriber classes
//
interface Observer {
	public void update(double price);
}

class SimpleAverageSubscriber implements Observer{
	private double m_price;
	
	public SimpleAverageSubscriber(Subject stockInfoProvider) {
		stockInfoProvider.registerObserver(this);
	}
	public void update(double price) {
		m_price = price;
	}
	public double getSimpleAverage() {
		return m_price;
	}
}

class MovingAverageSubscriber implements Observer {
	private double m_price;
	
	public MovingAverageSubscriber(Subject stockInfoProvider) {
		stockInfoProvider.registerObserver(this);
	}
	public void update(double price) {
		m_price = price;
	}
	public double getMovingAverage() {
		return (m_price + m_price);
	}
}

public class ObserverStrategy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StockInformationProvider stockInfoProvider = new StockInformationProvider();
		SimpleAverageSubscriber simpleAverage = new SimpleAverageSubscriber(stockInfoProvider);
		MovingAverageSubscriber movingAverage = new MovingAverageSubscriber(stockInfoProvider);
		
		stockInfoProvider.setPrice(1.0);
		System.out.println(simpleAverage.getSimpleAverage() + " : " + movingAverage.getMovingAverage());
		stockInfoProvider.setPrice(2.0);
		System.out.println(simpleAverage.getSimpleAverage() + " : " + movingAverage.getMovingAverage());
	}

}
