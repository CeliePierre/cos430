/**
 * Revision History
 *******************************************************************
 * 02.01.2021  Updated the lab<br>
 * 2014  cleaned up and JavaDoc added<br>
 * 2005  original version written<br>
 */
package vetclinicdriver;

/**
 * Course: CSCI 160<br>
 * Project: AgilityCompetition (Version 3 Solution)<br>
 * File: Dog.java<br>
 * Uses: Date class<br>
 * Extends: nothing<br>
 * Implements: Comparable<br>
 * Presumption: All dogs have these attributes<br>
 * A class to represent a Dog <br>
 * @author aapplin
 */
public abstract class Dog implements Comparable {

    protected final Date birthDate;
    protected final String breed;
    protected final double weight;


    /**
     * Parameterized Constructor for the Dog class.
     *
     * @param dob the dog's date of birth (Date)
     * @param breed the dog's breed
     * @param weight the dog's weight
     */
    public Dog(Date dob, String breed, double weight) {
        this.birthDate = dob;
        this.breed = breed;
        this.weight = weight;
    }
    // abstract methods FORCE the subclasses to implement them
    public abstract Date getBirthDate();
    public abstract String getBreed();
    public abstract double getWeight();
    @Override
    public abstract String toString();
    @Override
    public abstract int compareTo(Object that);     
}
