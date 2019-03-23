import java.util.ArrayList;
import java.util.List;

interface Student {
	Student clone();
	String getName();
}

class FullTime implements Student {
	public Student clone() {
		return new FullTime();
	}
	public String getName() {
		return "Fulltime";
	}
}

class PartTime implements Student {
	public Student clone() {
		return new PartTime();
	}
	public String getName() {
		return "Parttime";
	}
}

class PrototypeModule {
	 private static List<Student> studentTypes = new ArrayList<>();
	 public static void addStudentType(Student student) {
		 studentTypes.add(student);
	 }
	 public static Student createStudent(String name) {
		 for(Student student : studentTypes) {
			 if( student.getName().equals(name) )
				 return student.clone();
		 }
		 System.out.println("Student type does not exists");
		 return null;
	 }
}

public class PrototypePattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrototypeModule.addStudentType(new FullTime());
		PrototypeModule.addStudentType(new PartTime());
		
		Student newStudent1 = PrototypeModule.createStudent("Parttime");
		System.out.println(newStudent1.getName());
		
		Student newStudent2 = PrototypeModule.createStudent("Fulltime");
		System.out.println(newStudent2.getName());
	}

}
