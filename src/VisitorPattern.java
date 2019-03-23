import java.util.Vector;

abstract class Component {
	protected int m_value;
	public void accept(Visitor visitor, Component comp) {}
	public void print() {}
}

class Leaf extends Component{
	Leaf(int value) { m_value = value; }
	@Override
	public void accept(Visitor visitor, Component comp) {
		// TODO Auto-generated method stub
		visitor.visit(this, comp);
	}
	public void print() {
		System.out.println("Leaf: " + m_value);
	}
}

class Composite2 extends Component {
	private Vector<Component> m_children;
	Composite2(int value) {
		m_children = new Vector<Component>();
		m_value = value; 
	}
	@Override
	public void accept(Visitor visitor, Component comp) {
		// TODO Auto-generated method stub
		visitor.visit(this, comp);
	}
	public void add(Component comp) {
		m_children.addElement(comp);
	}
	public void print() {
		System.out.println("Composite: " + m_value);
		for(Component comp : m_children)
			comp.print();
	}
}

interface Visitor {
	public void visit(Leaf leaf, Component comp);
	public void visit(Composite2 composite, Component comp);
	public int getValue();
}

class AddVisitor implements Visitor {
	@Override
	public void visit(Leaf leaf, Component comp) {
		// TODO Auto-generated method stub
		// Cannot add a component to a leaf
	}
	@Override
	public void visit(Composite2 composite, Component comp) {
		// TODO Auto-generated method stub
		composite.add(comp);
	}
	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}
}

class GetValueVisitor implements Visitor {
	int m_value = 1;
	@Override
	public void visit(Leaf leaf, Component comp) {
		// TODO Auto-generated method stub
		m_value = m_value * 10;
	}

	@Override
	public void visit(Composite2 composite, Component comp) {
		// TODO Auto-generated method stub
		m_value = m_value * 20;
	}
	public int getValue() {
		return m_value;
	}
}

public class VisitorPattern {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Component comp1 = new Composite2(1);
		Component leaf1 = new Leaf(2);
		Component leaf2 = new Leaf(3);
		
		Visitor addvisitor = new AddVisitor();
		comp1.accept(addvisitor, leaf1);
		comp1.accept(addvisitor, leaf2);
		
		Visitor getValueVisitor = new GetValueVisitor();
		comp1.accept(getValueVisitor, comp1);
		System.out.println("comp1 value: " + getValueVisitor.getValue() );
		
		leaf1.accept(getValueVisitor, leaf1);
		leaf2.accept(getValueVisitor, leaf2);
		System.out.println("total value: " + getValueVisitor.getValue() );
		
		comp1.print();
	}
}
