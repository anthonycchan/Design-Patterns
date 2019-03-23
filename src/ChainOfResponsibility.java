abstract class EmailHandler {
	public static int SPAM = 1;
	public static int FAN = 2;
	
	EmailHandler m_nextHandler;
	
	public void setNextHandler(EmailHandler nextHandler) {
		m_nextHandler = nextHandler;
	}
	
	public void handleEmail(int emailType) {
		if( isApplicable(emailType) )
			printMessage();
		else
			m_nextHandler.handleEmail(emailType);
	}
	
	protected abstract boolean isApplicable(int emailType);
	protected abstract void printMessage();
}

class SpamHandler extends EmailHandler {
	protected boolean isApplicable(int emailType) {
		if(emailType == SPAM)
			return true;	
		return false;
	}
	protected void printMessage() {
		System.out.println("Spam email");
	}
}

class FanHandler extends EmailHandler {
	protected boolean isApplicable(int emailType) {
		if(emailType == FAN)
			return true;	
		return false;
	}
	protected void printMessage() {
		System.out.println("Fan email");
	}
}

public class ChainOfResponsibility {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 EmailHandler emailHandler = getChainOfHandlers();
		 emailHandler.handleEmail(EmailHandler.SPAM);
		 emailHandler.handleEmail(EmailHandler.FAN);
	}

	static EmailHandler getChainOfHandlers() {
		EmailHandler emailHandler = new SpamHandler();
		EmailHandler nextEmailHandler = new FanHandler();
		emailHandler.setNextHandler(nextEmailHandler);
		return emailHandler;
	}
}
