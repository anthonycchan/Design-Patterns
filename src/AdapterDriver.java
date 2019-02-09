interface ConnectionTime {
	int getConnectionTime();
}

class OldConnectionTime implements ConnectionTime {
	@Override
	public int getConnectionTime() {
		return 10;
	}
}

// New connection time engine to replace the old one
class NewConnectionTime {
	public double getConnectionTime() {
		return 20.0;
	}
}

class NewConnectionTimeAdapter implements ConnectionTime{
	NewConnectionTime m_nct;
	
	NewConnectionTimeAdapter(NewConnectionTime nct) {
		m_nct = nct;
	}
	
	@Override
	public int getConnectionTime() {
		return (int)m_nct.getConnectionTime();
	}
}

public class AdapterDriver {
	public static void main(String[] args) { 
		
		// The driver code to get the connection time does not change
		AdapterDriver obj = new AdapterDriver();
		System.out.println(obj.getConnectionTime());
	}
	
	int getConnectionTime() {
		boolean useOldConnectionTime = false;
		ConnectionTime ct;
		
		if ( useOldConnectionTime ) 
			ct = new OldConnectionTime();
		else
			ct = new NewConnectionTimeAdapter(new NewConnectionTime());
		
		return ct.getConnectionTime();
	}
}
