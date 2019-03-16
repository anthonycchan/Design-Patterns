interface IStudentRecord {
	public void setName(String name);
	public String getName();
	public void setScore(double score);
	public double getScore();
}

class StudentRecordImpl implements IStudentRecord {
	private String m_name;
	private double m_score;
	
	@Override
	public void setName(String name) {
		m_name = name;
	}
	@Override
	public String getName() {
		return m_name;
	}
	@Override
	public void setScore(double score) {
		m_score = score;
	}
	@Override
	public double getScore() {
		return m_score;
	}
}

class StudentProxy implements IStudentRecord {
	IStudentRecord m_studentRecord;
	
	StudentProxy(IStudentRecord studentRecord) {
		m_studentRecord = studentRecord;
	}
	
	@Override
	public void setName(String name) {
		m_studentRecord.setName(name);
	}
	@Override
	public String getName() {
		return m_studentRecord.getName();
	}
	@Override
	public void setScore(double score) {
		System.out.println("Student cannot set score!");
	}
	@Override
	public double getScore() {
		return m_studentRecord.getScore();
	}
}

class TeacherProxy implements IStudentRecord {
	IStudentRecord m_studentRecord;
	
	TeacherProxy(IStudentRecord studentRecord) {
		m_studentRecord = studentRecord;
	}
	
	@Override
	public void setName(String name) {
		System.out.println("Only a student can set a name!");
	}
	@Override
	public String getName() {
		return m_studentRecord.getName();
	}
	@Override
	public void setScore(double score) {
		m_studentRecord.setScore(score);
	}
	@Override
	public double getScore() {
		return m_studentRecord.getScore();
	}
}

public class Proxy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IStudentRecord studentRecord = new StudentRecordImpl();
		
		IStudentRecord studentProxy = new StudentProxy(studentRecord);
		studentProxy.setName("Anthony");
		
		IStudentRecord teacherProxy = new TeacherProxy(studentRecord);
		teacherProxy.setScore(100.0);
		System.out.println( teacherProxy.getName() + " : " + teacherProxy.getScore() );
		
		studentProxy.setScore(101.0);
		System.out.println( studentProxy.getName() + " : " + studentProxy.getScore() );
	}
}
