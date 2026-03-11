package KW.AXA;

/** 
 * Person is a class that represents a human being.
 * The Person2 class has been modified to include exercise solutions
 * @author Koffman and Wolfgang
 **/
public class Person2 {
    // Data Fields

    /** The given name */
    private String givenName;
    /** The family name */
    private String familyName;
    /** The ID number */
    private String IDNumber;
    /** The birth year */
    private int birthYear = 1900;
    /*<exercise chapter="A" section="7" type="programming" number="2">*/
    /** Mother's Maiden Name */
    private String motherMaidenName;
    /*</exercise>*/
    // Constants
    /** The age at which a person can vote */
    private static final int VOTE_AGE = 18;
    /** The age at which a person is considered a senior citizen */
    private static final int SENIOR_AGE = 65;

    // Constructors
    /** Construct a person with given values
     *  @param first The given name
     *  @param family The family name
     *  @param ID The ID number
     *  @param birth The birth year
     */
    public Person2(String first, String family, String ID, int birth) {
        givenName = first;
        familyName = family;
        IDNumber = ID;
        birthYear = birth;
    }

    /** Construct a person with only an IDNumber specified.
     *  @param ID The ID number
     */
    public Person2(String ID) {
        IDNumber = ID;
    }

    // Modifier Methods
    /** Sets the givenName field.
     *  @param given The given name
     */
    public void setGivenName(String given) {
        givenName = given;
    }

    /** Sets the familyName field.
     *  @param family The family name
     */
    public void setFamilyName(String family) {
        familyName = family;
    }

    /** Sets the birthYear field.
     *  @param birthYear The year of birth
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    /*<exercise chapter="A" section="7" type="programming" number="2">*/
    /**
     * Sets the motherMaidenName fiels.
     * @param motherMaidenName The mother's maiden name
     */
    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }
    /*</exercise>*/

    // Accessor Methods
    /** Gets the person's given name.
     *  @return the given name as a String
     */
    public String getGivenName() {
        return givenName;
    }

    /** Gets the person's family name.
     *  @return the family name as a String
     */
    public String getFamilyName() {
        return familyName;
    }

    /** Gets the person's ID number.
     *  @return the ID number as a String
     */
    public String getIDNumber() {
        return IDNumber;
    }

    /** Gets the person's year of birth.
     *  @return the year of birth as an int value
     */
    public int getBirthYear() {
        return birthYear;
    }

    /*<exercise chapter="A" section="7" type="programming" number="2">*/
    /**
     * Gets the person's mother's maiden name
     * @return motherMaidenName
     */
    public String getMotherMaidenName() {
        return motherMaidenName;
    }
    /*</exercise>*/

    // Other Methods
    /** Calculates a person's age at this year's birthday.
     *  @param year The current year
     *  @return the year minus the birth year
     */
    public int age(int year) {
        return year - birthYear;
    }

    /** Determines whether a person can vote.
     *  @param year The current year
     *  @return true if the person's age is greater than or
     *  equal to the voting age
     */
    public boolean canVote(int year) {
        int theAge = age(year);
        return theAge >= VOTE_AGE;
    }

    /** Determines whether a person is a senior citizen.
     *  @param year the current year
     *  @return true if person's age is greater than or
     *  equal to the age at which a person is
     *  considered to be a senior citizen
     */
    public boolean isSenior(int year) {
        return age(year) >= SENIOR_AGE;
    }

    /*<exercise chapter="A" section="7" type="programming" number="2">*/
    /** Retrieves the information in a Person object.
     *  @return the object state as a string
     */
    @Override
    public String toString() {
        return "Given name: " + givenName + "\n"
                + "Family name: " + familyName + "\n"
                + "ID number: " + IDNumber + "\n"
                + "Mother maiden name: " + motherMaidenName + "\n"
                + "Year of birth: " + birthYear + "\n";
    }

    /** Compares two Person objects for equality.
     *  @param per The second Person object
     *  @return true if the Person objects have same
     *  ID number and motherMaidenName; false if they don't
     */
    public boolean equals(Person2 per) {
        if (per == null) {
            return false;
        } else {
            return IDNumber.equals(per.IDNumber)
                    && ((motherMaidenName != null
                    && motherMaidenName.equals(per.motherMaidenName))
                    || (motherMaidenName == null && per.motherMaidenName == null));
        }
    }
    /*</exercise>*/

    /*<exercise chapter="A" section="7" type="programming" number="3">*/
    /**
     * Compare this Person object to another Person object.  If this Person's
     * ID is less (greater) than the other Person's ID return a negative
     * (positive) value. Otherwise return 0.
     */
    public int compareTo(Person2 other) {
        return IDNumber.compareTo(other.IDNumber);
    }
    /*</exercise>*/

    /*<exercise chapter="A" section="7" type="programming" number="4">*/
    /**
     * Switch the given and family names of the person
     */
    public void switchNames() {
        String temp = givenName;
        givenName = familyName;
        familyName = temp;
    }
    /*</exercise>*/

    /*<exercise chapter="A" section="7" type="programming" number="1">*/
    /**
     * Return the Person's initials as a string. There should be a peroid
     * after each initial.
     * @return the Person's initials as a string.
     */
    public String getInitials() {
        return givenName.charAt(0) + "." + familyName.charAt(0) + ".";
    }
    /*</exercise>*/
}
