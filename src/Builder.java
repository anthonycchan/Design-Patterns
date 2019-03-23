class Computer {
	String m_hdd;
	String m_processor;
	String m_gpu;
	String m_os;
	
	Computer() {}
	void setHdd(String hdd) {
		m_hdd = hdd;
	}
	void setProcessor(String processor) {
		m_processor = processor;
	}
	void setGPU(String gpu) {
		m_gpu = gpu;
	}
	void setOS(String os) {
		m_os = os;
	}
	void print() {
		System.out.println(m_os + " : " + m_processor + " : " + m_hdd + " : " + m_gpu );
	}
}

abstract class ComputerBuilder {
	Computer m_computer;
	
	ComputerBuilder() {
		m_computer = new Computer();
	}
	ComputerBuilder setHdd(String hdd) {
		m_computer.setHdd(hdd);
		return this;
	}
	ComputerBuilder setProcessor(String processor) {
		m_computer.setProcessor(processor);
		return this;
	}
	ComputerBuilder setGPU(String gpu) {
		m_computer.setGPU(gpu);
		return this;
	}
	Computer build() {
		return m_computer;
	}
	abstract ComputerBuilder install();
}

class PCComputerBuilder extends ComputerBuilder{
	ComputerBuilder install() {
		m_computer.setOS("Windows");
		return this;
	}
}

class MacComputerBuilder extends ComputerBuilder {
	ComputerBuilder install() {
		m_computer.setOS("MACOS");
		return this;
	}
}

public class Builder {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComputerBuilder PCCompBuilder = new PCComputerBuilder();
		Computer PC = PCCompBuilder.setGPU("Nvidia").setHdd("1000GB").setProcessor("Intel i7").install().build();
		PC.print();
		
		ComputerBuilder MACCompBuilder = new MacComputerBuilder();
		Computer MAC = MACCompBuilder.setGPU("Intel").setHdd("500GB").setProcessor("AMD").install().build();
		MAC.print();		
	}
}
