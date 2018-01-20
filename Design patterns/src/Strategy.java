interface Attribute {
	int getStartTime();
	int getEndTime();
}

class LongGroundTimeAttribute implements Attribute{
	int m_pairingStartTime, m_duration, m_dutyEndTime;
		
	LongGroundTimeAttribute(int pairingStartTime, int duration, int dutyEndTime) {
		m_pairingStartTime = pairingStartTime;
		m_duration = duration;
		m_dutyEndTime = dutyEndTime;
	}
	
	public int getStartTime() {
		return m_pairingStartTime;
	}
	
	public int getEndTime() {
		return m_pairingStartTime + m_duration;
	}
}

class EmptyDutyAttribute implements Attribute {
	int m_pairingStartTime, m_duration, m_dutyEndTime;
	
	EmptyDutyAttribute(int pairingStartTime, int duration, int dutyEndTime) {
		m_pairingStartTime = pairingStartTime;
		m_duration = duration;
		m_dutyEndTime = dutyEndTime;
	}
	
	public int getStartTime() {
		return m_pairingStartTime;
	}

	public int getEndTime() {
		return m_dutyEndTime;
	}
}

class Nonfly {
	Attribute m_atr;
	
	void setAttributes(Attribute atr) {
		m_atr = atr;
	}
	
	// Generic getters
	public int getStartTime() {
		return m_atr.getStartTime();
	}
	
	public int getEndTime() {
		return m_atr.getEndTime();
	}
}

public class Strategy {
	public static void main(String args[]) {
		// Create different kinds of nonflies using the same nonfly interface
		Nonfly longGroundNonfly = new Nonfly();
		int pairingStartTime = 1234;
		int duration = 10;
		int dutyEndTime = 4321;
		longGroundNonfly.setAttributes(new LongGroundTimeAttribute(pairingStartTime, duration, dutyEndTime));

		// Generic functionality
		Strategy obj1 = new Strategy();
		obj1.translateAttributes(longGroundNonfly);
		
		
		// Create another kind of nonfly
		Nonfly emptyDutyNonfly = new Nonfly();
		emptyDutyNonfly.setAttributes(new EmptyDutyAttribute(pairingStartTime, duration, dutyEndTime));
		
		
		// Generic functionality
		obj1.translateAttributes(emptyDutyNonfly);
	}
	
	void translateAttributes(Nonfly nonfly) {
		System.out.println(nonfly.getStartTime());
		System.out.println(nonfly.getEndTime());
	}
}

