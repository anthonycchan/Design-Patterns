//
// Handle singleton multithreading issue by synchronizing the getInstance method
//
class Logger {
	private Logger() {
		System.out.println("constructed");
	}

	private static Logger m_instance;
	
	public static synchronized Logger getInstance() {
		if (m_instance == null)
			m_instance = new Logger();
		return m_instance;
	}
}

//
// Handle singleton multithreading issue by eager initialization
//
class Logger2{
	private static Logger2 m_logger  = new Logger2();
	
	private Logger2() { 
	}
	
	public static Logger2 getInstance() {
		return m_logger;
	}
}

//
// Handle singleton multithreading issue by double-checked locking
//
class Logger3{
	private static volatile Logger3 m_logger;
	private Logger3() {}
	
	public static Logger3 getInstance() {
		if ( m_logger == null ) {
			synchronized(Logger3.class) {
				if (m_logger == null)
					m_logger = new Logger3();
			}
		}

		return m_logger;
	}
}

public class Singleton {
	public static void main(String[] args) {
		Logger logger = Logger.getInstance();
		System.out.println(logger);
		
		Logger2 logger2 = Logger2.getInstance();
		System.out.println(logger2);
				
		System.out.println(boolean.class);
	}
}
