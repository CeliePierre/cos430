/*
 *              Revision History
 * ***************************************************************
 * 2019 Rewrote the compareTo() method to use a single return.<br>
 * 2015 Written to demonstrate inheritance
 */
package doghotelproject;

/**
 * A class to represent a Pet as an extension of Dog.
 * Course: CSCI 160<br>
 * Class: Pet <br>
 * Uses: nothing<br>
 * Extends: Dog<br>
 * Implements: Comparable<br>
 * Presumption: All Pets have these attributes <br>
 * @author aapplin
 */
public class Pet extends Dog {

    /**
     *  The Pet's name
     */
    protected final String name;

    /**
     * The Pet's owner's name
     */
    protected final String owner;

    /**
     * Parameterized Constructor for Pet
     *
     * @param dob the dog's date of birth (Date)
     * @param breed the dog's breed
     * @param weight the dog's weight
     * @param name the pet's name
     * @param owner the pet's owner's name
     */
    public Pet(Date dob, String breed, double weight,
            String name, String owner) {
        super(dob, breed, weight);  // call Dog's constructor  
        this.name = name;
        this.owner = owner;
    }

    /**
     * Accessor for date of birth
     *
     * @return the Date object representing date of birth
     */
    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Accessor for breed
     *
     * @return a string object that is the breed
     */
    @Override
    public String getBreed() {
        return breed;
    }

    /**
     * Accessor for weight
     *
     * @return the value of weight
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * Accessor for the name attribute
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for the owner attribute
     *
     * @return the value of owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * toString allows an object to be directly printed by returning a String
     * that can be printed to the console or to a file.
     *
     * @return a formatted string representing the values of the attributes for
     * a Pet object.
     */
    @Override
    public String toString() {
        return String.format("%-15s%-25s%7.2f  %-15s%-12s",
                birthDate.toString(), breed, weight, name, owner);
    }

    /**
     * compareTo (abstract method of the Comparable Interface) is implemented to
     * impose a natural ordering on a group of objects. compareTo is used by the
     * Collections.sort routine to allow us to sort the competitors belonging to
     * some Java collection.
     *
     * @param that is the Dog object we are comparing this one to
     * @return a negative integer, zero, or a positive integer if this object
     * comes before, is equal to, or comes after the specified object.
     */
    @Override
    public int compareTo(Object that) {
        int comparison;
        if (that == null) {
            comparison = 1;
        } else if (this == that) {
            comparison = 0;
        } else {
            comparison = this.owner.compareTo(((Pet) that).getOwner());
            if (comparison == 0) {
                comparison = this.name.compareTo(((Pet) that).getName());
            }
        }
        return comparison;
    }

    /**
     * Unit Test for the Pet class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // tests Pet class only. to run, right-click 
        // and choose Run File
        Date date = new Date(7, 26, 2006);
        // demonstrating polymorphism.  a subclass is an instance of 
        // it's superclass. 
        Dog dog = new Pet(date, "Toy Poodle", 10.2, "Eudora", "Anne");
        System.out.println(dog);
    }
}

