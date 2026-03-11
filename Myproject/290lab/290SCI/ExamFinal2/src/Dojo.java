
package student;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
/**
 *
 * @author kalalajoel
 */
public class Dojo {
       private final TreeMap<Date, Student> students; // Sorted by age, and marked as final
    private ArrayList<Instructor> instructors;

    public Dojo() {
        students = new TreeMap<>();
        instructors = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.put(student.getBirthDate(), student);
    }

    public void removeStudent(Student student) {
        students.remove(student.getBirthDate());
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public void removeInstructor(Instructor instructor) {
        instructors.remove(instructor);
    }

    public static void main(String[] args) {
        // Create a Dojo instance
        Dojo dojo = new Dojo();

        // Create some Students
        Student student1 = new Student("John", "Doe", new Date(), "S001", "Beginner");
        Student student2 = new Student("Jane", "Doe", new Date(), "S002", "Intermediate");

        // Create an Instructor
        Instructor instructor = new Instructor("Alice", "Smith", new Date(), "I001", 30.0);

        // Add students and instructor to the dojo
        dojo.addStudent(student1);
        dojo.addStudent(student2);
        dojo.addInstructor(instructor);

        // Demonstrate other functionalities...
    }
}
    