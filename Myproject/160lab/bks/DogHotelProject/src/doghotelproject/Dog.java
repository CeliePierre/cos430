/**
 *                   Revision History
 * ***********************************************************************
 * 2019 Rewrote the compareTo() method to use a single return.<br>
 * 2015 Written for the agility competition project to show how a class<br>
 *      can be written for a special purpose.
 */

package doghotelproject;

/**
 * A class to represent a Dog.
 * Course: CSCI 160<br>
 * Class: Dog <br>
 * Uses: Date class<br>
 * Extends: nothing<br>
 * Implements: Comparable<br>
 * Presumption: All dogs have these attributes <br>
 * @author aapplin
 */
public abstract class Dog implements Comparable{
    
    /**
     * a date object representing the date the dog was born
     */
    protected final Date birthDate;

    /**
     * a String object representing the dog's breed
     */
    protected final String breed;

    /**
     * a double representing the dog's weight
     */
    protected final double weight;        
    /**
     * Parameterized Constructor for the Dog class.
     * @param birthDate the dog's date of birth (Date)
     * @param breed the dog's breed
     * @param weight the dog's weight
     */
    public Dog(Date birthDate, String breed, double weight){
        this.birthDate = birthDate;
        this.breed = breed;
        this.weight = weight;         
    }
    // abstract methods force the subclasses to implement these

    /**
     * @return the value of property birthDate
     */
    public abstract Date getBirthDate();

    /**
     * @return the value of property breed
     */
    public abstract String getBreed();

    /**
     * @return the value of property weight
     */
    public abstract double getWeight();

    /**
     * @return a String representing the properties of the object
     */
    @Override
    public abstract String toString();

    /**
     * @param that the object we are comparing this object to
     * @return an integer less than 0, 0 or greater than 0
     */
    @Override
    public abstract int compareTo(Object that);   
}

