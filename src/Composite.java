import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

abstract class ShipComponent {
	public void add(ShipComponent component) {
		throw new UnsupportedOperationException();
	}
	
	public void remove(ShipComponent component) {
		throw new UnsupportedOperationException();
	}
	
	public ShipComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}
	
	public String getName() {
		throw new UnsupportedOperationException();
	}

	public int getArmor() {
		throw new UnsupportedOperationException();	
	}
	
	public void print() {
		throw new UnsupportedOperationException();
	}
	
	public Iterator createIterator() {
		throw new UnsupportedOperationException();
	}
}

class ShipItem extends ShipComponent {
	String m_name;
	int m_armor;
	
	ShipItem(String name, int armor) {
		m_name = name;
		m_armor = armor;
	}
	
	public String getName() {
		return m_name;
	}
	
	public int getArmor() {
		return m_armor;
	}
	
	public void print() {
		System.out.println(getName() + " : " + getArmor());
	}
	
	public Iterator createIterator() {
		return null;
	}
}

class ShipGroup extends ShipComponent {
	String m_name;
	ArrayList<ShipComponent> shipComponents = new ArrayList<ShipComponent>();
	Iterator iterator = null;
			
	ShipGroup(String name) {
		m_name = name;
	}
	
	public void add(ShipComponent component) {
		shipComponents.add(component);
	}
	
	public void remove(ShipComponent component) {
		shipComponents.remove(component);
	}
	
	public ShipComponent getChild(int i) {
		return (ShipComponent) shipComponents.get(i);
	}
	
	public String getName() {
		return m_name;
	}
	
	public void print() {
		System.out.println(getName());
		Iterator iter = shipComponents.iterator();
		while(iter.hasNext()) {
			ShipComponent shipComp = (ShipComponent)iter.next();
			shipComp.print();
		}
	}
	
	public Iterator createIterator() {
		if(iterator == null)
			iterator = new CompositeIterator(shipComponents.iterator());
		return iterator;
	}
}

public class Composite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		// Initialization
		//
		ShipComponent navy = new ShipGroup("Navies");
		ShipComponent germanNavy = new ShipGroup("German Navy");
		navy.add(germanNavy);
		ShipComponent battleShips = new ShipGroup("BattleShips");
		germanNavy.add(battleShips);
		
		battleShips.add(new ShipItem("Bismark", 100));
		battleShips.add(new ShipItem("Tripitz", 100));
		
		
		ShipComponent USNavy = new ShipGroup("US Navy");
		navy.add(USNavy);
		
		battleShips = new ShipGroup("BattleShips");
		USNavy.add(battleShips);
		
		battleShips.add(new ShipItem("Texas", 100));
		battleShips.add(new ShipItem("Arizona", 100));
		
		
		//
		// Iteration
		//
		Composite composition = new Composite();
		
		System.out.println("InternalIteration:");
		composition.internalIteration(navy);
		
		System.out.println("\nExternalIteration:");
		composition.compositeIteration(navy);
	}
	
	public void internalIteration(ShipComponent navy) {
		navy.print();
	}
	
	public void compositeIteration(ShipComponent navy) {
		Iterator iter = navy.createIterator();  // Gets the iterator for the collection inside Navy.. Does not include Navy itself.
		System.out.println(navy.getName());
		while(iter.hasNext()) {
			ShipComponent shipComp = (ShipComponent)iter.next();
			System.out.print(shipComp.getName() );
			if(shipComp instanceof ShipItem)
				System.out.print(" : " + shipComp.getArmor());
			System.out.println();
		}
	}
}

// Iterator for the tree
class CompositeIterator implements Iterator {
	Stack<Iterator> stack = new Stack();

	CompositeIterator(Iterator iter) {
		stack.push(iter);
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(stack.empty())
			return false;
		
		Iterator iter = stack.peek();
		if(!iter.hasNext()) {
			stack.pop();
			return hasNext();
		}
		else {
			return true;
		}
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		if(hasNext()) {
			Iterator iter = (Iterator) stack.peek();
			ShipComponent shipComponent = (ShipComponent) iter.next();
			if(shipComponent instanceof ShipGroup)
				stack.push(shipComponent.createIterator());
			
			return shipComponent;
		}
		else {
			return null;
		}
	}
}