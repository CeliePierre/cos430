
package student;

/**
 *
 * @author kalalajoel
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchBySkill {
    public ArrayList<Student> search(String skillLevel, ArrayList<Student> students) {
        ArrayList<Student> matchedStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getSkillLevel().equals(skillLevel)) {
                matchedStudents.add(student);
            }
        }
        return matchedStudents;
    }
}