/**
 *                        Revision History
 *  ==========================================================================
 *  2022 rewritten in Java for the GUI lab as an experiment! Anne Applin 
 *  2003 Originally written at Ithaca College for comp171 by Sharon Stansfield. 
 */

package musicsurveyconsole;

/**
 *
 * @author Anne
 */
public class Student {
    private String firstName;
    private String lastName;
    private String idNumber;
    private boolean voted;

    /**
     * Parameterized Constructor
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param idNumber the student's id number
     */
    public Student(String firstName, String lastName, String idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        voted = false;
    }

    /**
     * changes voted from false to true
     */
    public void setVoted(){
        voted = true;
    }

    /**
     * accessor for the property firstName
     * @return the student's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * accessor for the property idNumber
     * @return the student's id number
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * accessor for the property voted
     * @return the current boolean value of the property
     */
    public boolean hasVoted() {
        return voted;
    }
           
}
