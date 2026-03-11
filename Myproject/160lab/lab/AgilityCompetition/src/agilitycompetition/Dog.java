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
 * A class to represent a Dog competing in an Agility Contest.
 * Version 01.02
 * @author aapplin
 */
public class Dog implements Comparable {

    protected final Date birthDate;
     protected final String breed;
    protected final double weight;
    //private final String name;
    //private final String owner;
    //private final ElapsedTime time;

    /**
     * Parameterized Constructor for the Dog class.
     *
     * @param dob the dog's date of birth (Date)
     * @param breed the dog's breed
     * @param weight the dog's weight
     * @param name the dog's name
     * @param owner the dog's owner's name
     * @param time the dog's elapsed time on the course (ElapsedTime)
     */
    public Dog(Date dob, String breed, double weight) {
        this.birthDate = dob;
        this.breed = breed;
        this.weight = weight;
        //this.name = name;
        //this.owner = owner;
        //this.time = time;
    }

    /**
     * Accessor for birthdate
     *
     * @return the current value of birthdate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     *
     * @return
     */
    public String getBreed() {
        return breed;
    }

    /**
     *
     * @return
     */
    public double getWeight() {
        return weight;
    }

    
    
    

    /**
     * toString allows an object to be directly printed by returning a String
     * that can be printed to the console or to a file. This one is 
     * written in the input file format.
     *
     * @return a formatted string representing the values of the attributes for
     * a dog object.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        String eol = System.lineSeparator(); // end of line for this OS
        str.append(String.format("%4d %4d %4d %s-25s %s %7.2f", 
                birthDate.getDay(), birthDate.getMonth(), 
                birthDate.getYear(), breed,eol,weight));
        
        return str.toString();
    }

    /**
     * compareTo (abstract method of the Comparable Interface) is implemented 
     * to impose a natural ordering on a group of objects. compareTo is used 
     * by the Collections.sort routine to allow us to sort the competitors 
     * belonging to some Java collection. Conditions are checked in
     * decreasing importance and as soon as a criteria fits we are 
     * finished looking at the comparison.
     *
     * @param that is the Dog object we are comparing this one to
     * @return a negative integer, zero, or a positive integer if this object
     * comes before, is equal to, or comes after the specified object.
     */
    @Override
    public int compareTo(Object that) {
        int comparison  = 0;        
        // shouldn't be any null objects, but if there are
        // put them after this one
        if (that == null){
            comparison = 1;            
            // if the addresses are the same... they are equal
        } else if (this == that) {
            comparison = 0;
        } else { // compare elapsed times
            comparison = this.birthDate.compareTo(((Dog)that).getBirthDate());
        } 
        return comparison;
    }
        
    

    /**
     * Unit Test for the dog class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // tests Dog class only. to run, right-click 
        // and choose Run File
        Date date1 = new Date(7, 26, 2006);
        Date date2 = new Date(10, 17, 2017);

        Dog dog1 = new Dog(date1, "Toy Poodle", 10.2);
        Dog dog2 = new Dog(date2, "Bulldog", 20.4);
        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println("testing compareTo() ");
        System.out.println("dog1.compareTo(dog2) " + dog1.compareTo(dog2));
        System.out.println("dog1.compareTo(dog1) " + dog1.compareTo(dog1));
        System.out.println("dog2.compareTo(dog1) " + dog2.compareTo(dog1));
    }

}
