
package student;
import java.util.Date;
/**
 *
 * @author kalalajoel
 */

public class Instructor extends Person {
    private double payRate;

    public Instructor(String firstName, String lastName, Date birthDate, String uniqueId, double payRate) {
        super(firstName, lastName, birthDate, uniqueId);
        this.payRate = payRate;
    }

    // Accessor for payRate
    public double getPayRate() {
        return payRate;
    }

    // Mutator for payRate
    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    // toString method to display instructor's information
    @Override
public String toString() {
    return String.format(
        "Instructor[FirstName: %s, LastName: %s, BirthDate: %s, UniqueID: %s, PayRate: %.2f]",
        getFirstName(), getLastName(), getBirthDate(), getUniqueId(), payRate
    );

}
}