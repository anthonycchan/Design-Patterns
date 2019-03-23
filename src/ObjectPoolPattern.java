import java.util.Stack;

class Object {
	private int m_intData;
	private boolean m_boolData;
	private String m_stringData;
	public void clear() {
		m_intData = -1;
		m_boolData = false;
		m_stringData = "";
	}
	public void setInt(int intData) { m_intData = intData; }
	public void setBool(boolean boolData) { m_boolData = boolData; }
	public void setString(String stringData) { m_stringData = stringData; }
	public int getInt() { return m_intData; }
	public boolean getBool() { return m_boolData; }
	public String getString() { return m_stringData; }
}

class ObjectPool {
	private Stack<Object> m_pool;
	private static ObjectPool m_obj = null;
	
	private ObjectPool() {
		m_pool = new Stack<Object>();
	}
	public static ObjectPool getInstance() {
		if(m_obj == null) {
			m_obj = new ObjectPool();
		}
		return m_obj;
	}
	
	public Object getResource() {
		if(!m_pool.isEmpty()) {
			return m_pool.pop();
		}
		else {
			return new Object();
		}
	}
	
	public void returnResource(Object obj) {
		obj.clear();
		m_pool.push(obj);
	}
	
	public int getPoolSize() { return m_pool.size(); }
}
public class ObjectPoolPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectPool ObjPool = ObjectPool.getInstance();
		Object obj1 = ObjPool.getResource();
		obj1.setInt(1);
		System.out.println(obj1.getInt() + " pool size: " + ObjPool.getPoolSize());
		ObjPool.returnResource(obj1);  // pool size =1
		System.out.println(obj1.getInt() + " pool size: " + ObjPool.getPoolSize());
		
		Object obj2 = ObjPool.getResource(); // pool size = 0
		obj1.setInt(1);
		System.out.println(obj1.getInt() + " pool size: " + ObjPool.getPoolSize());
		
		Object obj3 = ObjPool.getResource();  // pool size = 0
		ObjPool.returnResource(obj2); // pool size = 1
		ObjPool.returnResource(obj3);  // pool size = 2
		System.out.println(obj1.getInt() + " pool size: " + ObjPool.getPoolSize());
	}
}
