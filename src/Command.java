import java.util.Vector;

interface CommandInterface {
	public void execute();
}

class Light {
	public void On() {
		System.out.println("Turn On the lights!");
	}
	
	public void Off() {
		System.out.println("Lights off");
	}
}

class LightsOnCommand implements CommandInterface {
	Light m_light;
	LightsOnCommand(Light light) {
		m_light = light;
	}
	public void execute() {
		m_light.On();
	}
}

class LightsOffCommand implements CommandInterface {
	Light m_light;
	LightsOffCommand(Light light) {
		m_light = light;
	}
	public void execute() {
		m_light.Off();
	}
}

class Remote {
	CommandInterface[] m_onCommands;
	CommandInterface[] m_offCommands;
	
	Remote() {
		m_onCommands = new CommandInterface[2];
		m_offCommands = new CommandInterface[2];
	}
	
	void setCommand(int slot, CommandInterface onCommand, CommandInterface offCommand) {
		m_onCommands[slot] = onCommand;
		m_offCommands[slot] = offCommand;
	}
	
	void executeOnCommand(int slot) {
		m_onCommands[slot].execute();
	}
	
	void executeOffCommand(int slot) {
		m_offCommands[slot].execute();
	}
}

public class Command {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Light lightBulb = new Light();
		LightsOnCommand lightsOn = new LightsOnCommand(lightBulb);
		LightsOffCommand lightsOff = new LightsOffCommand(lightBulb);
		
		Remote remote = new Remote();
		
		remote.setCommand(0, lightsOn, lightsOff);
		remote.executeOnCommand(0);
		remote.executeOffCommand(0);
	}
}
