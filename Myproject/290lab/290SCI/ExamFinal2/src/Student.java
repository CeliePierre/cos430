
package student;
import java.util.Comparator;
import java.util.Date;
/**
 *
 * @author kalalajoel
 */

    

public class Student extends Person {
    private String skillLevel; // Assuming skill level is a String

    public Student(String firstName, String lastName, Date birthDate, String uniqueId, String skillLevel) {
        super(firstName, lastName, birthDate, uniqueId);
        this.skillLevel = skillLevel;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

  
    public int compareTo(Student other) {
        return getBirthDate().compareTo(other.getBirthDate());
    }

    public static class CmpSkill implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.skillLevel.compareTo(s2.skillLevel);
        }
    }
}