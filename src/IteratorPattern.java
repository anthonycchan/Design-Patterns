import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;
import java.util.Iterator;
import java.util.Set;

class Ship {
	String m_shipName;
	int m_armor;
	
	Ship(String shipName, int armor) {
		m_shipName = shipName;
		m_armor = armor;
	}
	
	String getShipName() { return m_shipName; }
	int getArmor() { return m_armor; }
}

class GermanBattleships implements Battleship {
	Vector<Ship> m_ships = new Vector<Ship>();
	GermanBattleships() {
		addItem("Bismark", 100);
		addItem("Tripitz", 200);
	}
	
	void addItem(String shipName, int armor) {
		Ship ship = new Ship(shipName, armor);
		m_ships.addElement(ship);
	}
	
	public Iterator createIterator() {
		return new GermanBattleshipIterator(m_ships);
	}
	
	public String getName() { 
		return "German";
	}
}

//interface Iterator {
//	boolean hasNext();
//	Object next();
//}


// Use custom iterator definition
class GermanBattleshipIterator implements Iterator {
	Vector<Ship> m_ships;
	int position;
	
	GermanBattleshipIterator(Vector<Ship> ships) {
		m_ships = ships;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if( position >= m_ships.size() || m_ships.get(position) == null) 
			return false;
		else
			return true;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		Ship ship = m_ships.get(position);
		position++;
		return ship;
	}	
	
	public void remove() { 
		m_ships.remove(position-1);
	}
}

// Use java.util built in iterator
class AmericanBattleships implements Battleship{
	Set<Ship> m_ships = new HashSet<Ship>();
	
	AmericanBattleships() {
		addItem("Texas", 300);
		addItem("Arizona", 400);
		addItem("New York", 500);
	}
	
	void addItem(String shipName, int armor) {
		Ship ship = new Ship(shipName, armor);
		m_ships.add(ship);
	}
	
	public Iterator createIterator() {
		return m_ships.iterator();
	}
	
	public String getName() { 
		return "American";
	}
}

interface Battleship {
	public Iterator createIterator();
	public String getName();
}

public class IteratorPattern {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Battleship germanBB = new GermanBattleships();
		Battleship americanBB = new AmericanBattleships();
		
		ArrayList<Battleship> battleships = new ArrayList<Battleship>();
		battleships.add(germanBB);
		battleships.add(americanBB);
		
		IteratorPattern mainObj = new IteratorPattern(battleships);
		mainObj.print();
		mainObj.remove("New York");
		mainObj.remove("Tripitz");
		System.out.println("\nAfter removal");
		mainObj.print();
	}
	
	ArrayList<Battleship> m_battleships;
	
	IteratorPattern(ArrayList<Battleship> battleships) {
		m_battleships = battleships;
	}
	
	void print() {
		Iterator bbIter = m_battleships.iterator();
		while(bbIter.hasNext()) {
			Battleship BBGroup = (Battleship) bbIter.next();
			System.out.println(BBGroup.getName());
			print(BBGroup.createIterator());
		}
	}
	
	private void print(Iterator iter) {
		while(iter.hasNext())
		{
			Ship ship = (Ship) iter.next();
			System.out.println("\t" + ship.getShipName() + " : " + ship.getArmor());
		}
	}
	
	private void remove(String name) {
		Iterator bbIter = m_battleships.iterator();
		while(bbIter.hasNext()) {
			Battleship BBGroup = (Battleship) bbIter.next();
			Iterator<Ship> iter = BBGroup.createIterator();
			while(iter.hasNext()) {
				Ship ship = iter.next();
				if(ship.getShipName() == name) 
					iter.remove();
			}
		}
	}
}
