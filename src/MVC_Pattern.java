import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//
// Model
//
interface StudentGrade {
	public String getComment();
	public double getGrade();
	public void setGrade(double grade);
	public void registerGradeChangeObserver(GradeObserver obs);
	public void notifyGradeChangeObservers();
}

class FullTimeStudentGrade implements StudentGrade {
	private double m_grade;
	Set<GradeObserver> m_gradeObservers = new HashSet<GradeObserver>();
	
	public String getComment() {
		if(m_grade == 100)
			return "Excellent!";
		if(m_grade >= 95 )
			return "Very good";
		else
			return "Good";
	}
	public double getGrade() { return m_grade; }
	public void setGrade(double grade) {
		m_grade = grade; 
		notifyGradeChangeObservers();
	}
	public void registerGradeChangeObserver(GradeObserver obs) {
		m_gradeObservers.add(obs); 
	}
	public void notifyGradeChangeObservers() {
		Iterator<GradeObserver> iterator = m_gradeObservers.iterator();
		while(iterator.hasNext()) {
			GradeObserver obs = iterator.next();
			obs.updateGrade(m_grade);
		}
	}
}

// 
// View
//
interface GradeObserver {
	public void updateGrade(double grade);
}

class ReportView implements GradeObserver{
	StudentGrade m_studentGradeModel;
	double m_grade;
	Controller m_controller;
	public ReportView(StudentGrade studentGradeModel, Controller controller) {
		m_studentGradeModel = studentGradeModel;
		m_studentGradeModel.registerGradeChangeObserver((GradeObserver) this);
		m_controller = controller;
	}
	public void printGrade() {
		System.out.println("Grade: " + m_studentGradeModel.getGrade() + " comment: " + m_studentGradeModel.getComment());
	}
	public void setGrade(double grade) {
		m_controller.setGrade(grade);
	}
	public void plusOnePoint() {
		m_controller.incrementGrade();
	}
	public void minusOnePoint() {
		m_controller.decrementGrade();
	}
	public void updateGrade(double grade) {
		m_grade = grade;
		System.out.println("Grade was updated to " + m_grade);
	}
}

//
// Controller
//
interface Controller {
	public void setGrade(double grade);
	public void incrementGrade();
	public void decrementGrade();
}

class StudentGradeController implements Controller{
	StudentGrade m_model;
	ReportView m_view;
	
	StudentGradeController(StudentGrade model) {
		m_model = model;
		m_view = new ReportView(model, this);
	}
	public String getComment() { 
		return m_model.getComment();
	}
	public double getGrade() { 
		return m_model.getGrade(); 
	}
	public void setGrade(double grade) {
		m_model.setGrade(grade);
	}
	public void incrementGrade() {
		m_model.setGrade(m_model.getGrade()+1);
	}
	public void decrementGrade() {
		m_model.setGrade(m_model.getGrade()-1);
	}
	
	public void testFuctionality() {
		m_view.setGrade(100);
		m_view.minusOnePoint();
		m_view.printGrade();
		m_view.plusOnePoint();
		m_view.printGrade();
	}
}

public class MVC_Pattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentGrade fullTimeStudentGrade = new FullTimeStudentGrade();
		StudentGradeController controller = new StudentGradeController(fullTimeStudentGrade);
		
		controller.testFuctionality();
	}

}
