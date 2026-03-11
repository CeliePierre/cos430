
package student;

import java.util.Date;

/**
 *
 * @author kalalajoel
 */

public class Person {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String uniqueId;

    public Person(String firstName, String lastName, Date birthDate, String uniqueId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.uniqueId = uniqueId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}