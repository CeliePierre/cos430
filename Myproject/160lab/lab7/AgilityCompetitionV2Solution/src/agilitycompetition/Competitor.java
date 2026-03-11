/**
 * Revision History
 *******************************************************************
 * 02.01.2021  Updated the lab<br>
 * 2014  cleaned up and JavaDoc added<br>
 * 2005  original version written<br>
 */
package agilitycompetition;

/**
 * Course:  CSCI 160<br>
 * Project: AgilityCompetition (Version 2 Solution)<br>
 * File:    Contest.java <br>
 * Uses:    Date, ElapsedTime<br>
 * Extends: Pet<br>
 * Overrides: Comparable &lt;Dog&gt; <br>
 * A class to model a competitor with an elapsed time on the course.<br>
 * @author aapplin
 */
public class Competitor extends Pet {

    private final ElapsedTime time;

    /**
     * Parameterized Constructor for Competitor
     *
     * @param birthDate the dog's date of birth (Date)
     * @param breed the dog's breed
     * @param weight the dog's weight
     * @param name the pet's name
     * @param owner the pet's owner's name
     * @param time the time that the competitor ran the course in
     */
    public Competitor(Date birthDate, String breed, double weight,
            String name, String owner, ElapsedTime time) {
        // call Pet's constructor  
        super(birthDate, breed, weight, name, owner); 
        this.time = time;
    }

    /**
     * Accessor for the course time
     *
     * @return the value of course time
     */
    public ElapsedTime getTime() {
        return time;
    }

/**
 * toString allows an object to be directly printed by returning a 
 * String that can be printed to the console or to a file. 
 * This one is  written in the input file format.
 *
 * @return a formatted string representing the values of the attributes 
 * for a dog object.
 */
 @Override
 public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(String.format("%s %d %d %d %d", 
            super.toString(), time.getHours(),
            time.getMinutes(), time.getSeconds(),
            time.getMilliseconds()));
    return str.toString();
}

    /**
     * compareTo (abstract method of the Comparable Interface) is implemented to
     * impose a natural ordering on a group of objects. compareTo is used by the
     * Collections.sort routine to allow us to sort the competitors belonging to
     * some Java collection.
     * 
     * This one overrides the one in Pet
     *
     * @param that is the Dog object we are comparing this one to
     * @return a negative integer, zero, or a positive integer if this object
     * comes before, is equal to, or comes after the specified object.
     */
    @Override
    public int compareTo(Object that) {
        // some constants for clarity
        int comparison = 0;
        // shouldn't be any null objects, but if there are
        // put them at the end
        if (that == null) {
            comparison = 1;
            //this optimization is  worthwhile
            //if the addresses are the same... they are equal
        } else if (this == that) {
            comparison = 0;
        } else {
            // we sort by time. There is a compareTo() 
            // in the ElapsedTime class that sorts on the 4 fields.
            comparison = this.time.compareTo(((Competitor)that).getTime());
        }
        return comparison;
    }

    /**
     * Unit Test for the Competitor class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // tests Dog class only. to run, right-click 
        // and choose Run File
        Date date1 = new Date(7, 26, 2006);
        Date date2 = new Date(10, 17, 2017);

        Competitor dog1 = new Competitor(date1, "Toy Poodle", 10.2, "Eudora",
                "Anne", new ElapsedTime(0, 2, 35, 40));
        Competitor dog2 = new Competitor(date2, "Bulldog", 20.4, "Bubba",
                "Jesse", new ElapsedTime(0, 5, 24, 50));
        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println("testing compareTo() ");
        System.out.println("dog1.compareTo(dog2) " + dog1.compareTo(dog2));
        System.out.println("dog1.compareTo(dog1) " + dog1.compareTo(dog1));
        System.out.println("dog2.compareTo(dog1) " + dog2.compareTo(dog1));
    }
}
