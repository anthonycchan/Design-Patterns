// Allows to vary implementation and abstraction

interface TV {
	void on();
	void off();
}

class Sony implements TV{
	public void on() {
		System.out.println("Turn on Sony TV");
	}
	public void off() {
		System.out.println("Turn off Sony TV");
	}
}

class Samsung implements TV {
	public void on() {
		System.out.println("Turn on Samsung TV");
	}
	public void off() {
		System.out.println("Turn off Samsung TV");
	}
}

//
// 1st variation: Type of remote control by inheritance 
// 2nd variation: Behavior of the remote control by composition
//
abstract class RemoteControl {
	TV	m_tv;
	RemoteControl(TV tv) {
		m_tv = tv;
	}
	abstract void turnOn();
	abstract void turnOff();
}

class TVRemote extends RemoteControl{
	TVRemote(TV tv) {
		super(tv);
		// 
	}
	void turnOn() {
		m_tv.on();
	}
	void turnOff() {
		m_tv.off();
	}
}

public class Bridge {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoteControl sonyTvRemote = new TVRemote( new Sony() );
		sonyTvRemote.turnOn();
		sonyTvRemote.turnOff();
	}
}
