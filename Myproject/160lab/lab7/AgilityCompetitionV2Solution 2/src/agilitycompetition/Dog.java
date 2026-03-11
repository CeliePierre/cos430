/**
 * Revision History
 *******************************************************************
 * 09.20.2021 fixed the compareTo()
 * 02.01.2021  Updated the lab
 * 2014  cleaned up and JavaDoc added
 * 2013  rewritten in Java
 * 2005  original version written in C++
 */
package agilitycompetition;

/**
 * Course: CSCI 160
 * File: Dog
 * Uses: Date class
 * Extends: nothing
 * Implements: Comparable
 * Presumption: All dogs have these attributes
 * A class to represent a Dog.
 * Version 01.02
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

   
    public abstract Date getBirthDate() ;
    
    public  abstract String getBreed() ;
    
    public  abstract double getWeight();

    

    @Override
    public abstract int  compareTo(Object that);
        
}