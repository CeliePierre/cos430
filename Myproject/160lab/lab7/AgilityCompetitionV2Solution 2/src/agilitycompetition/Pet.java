/**
 * Revision History
 *******************************************************************
 * 02.01.2021  Updated the lab<br>
 * 2014  cleaned up and JavaDoc added<br>
 * 2005  original version written<br>
 */
package agilitycompetition;

/**
 * Course: CSCI 160<br> 
 * Project: AgilityCompetition (Version 2 Solution)<br>
 * File: Pet.java <br>
 * Uses: Date class<br> 
 * Extends: Dog <br>
 * Overrides: Comparable &lt;Dog&gt; <br>
 * A class to model a pet dog which adds a name for the dog and an<br>
 * owner name to the properties of Dog.<br>
 * @author aapplin
 */
public class Pet extends Dog {

    private final String name;
    private final String owner;

    /**
     * Parameterized Constructor for Pet.
     *
     * @param birthDate the dog's date of birth (Date)
     * @param breed the dog's breed
     * @param weight the dog's weight
     * @param name the pet's name
     * @param owner the pet's owner's name
     */
    public Pet(Date birthDate, String breed, double weight,
            String name, String owner) {
        super(birthDate, breed, weight);  // call Dog's constructor  
        this.name = name;
        this.owner = owner;
    }
 /**
     * Accessor for birthdate
     *
     * @return the current value of birthdate
     */
    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     *
     * @return
     */
    @Override
    public String getBreed() {
        return breed;
    }

    /**
     *
     * @return
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
     String eol = System.lineSeparator(); // end of line for this OS
        str.append(String.format("%4d %4d %4d %-25s", 
                birthDate.getDay(), birthDate.getMonth(), 
                birthDate.getYear(), breed));
        str.append(eol);
        str.append(String.format("%7.2f", weight));
    str.append(String.format("%s ",super.toString()));
    str.append(String.format("%-15s%-12s",name, owner));
    return str.toString();
}

    /**
     * compareTo (abstract method of the Comparable Interface) is implemented to
     * impose a natural ordering on a group of objects. compareTo is used by the
     * Collections.sort routine to allow us to sort the competitors belonging to
     * some Java collection.
     * 
     * This one overrides the one in Dog
     *
     * @param that is the Dog object we are comparing this one to
     * @return a negative integer, zero, or a positive integer if this object
     * comes before, is equal to, or comes after the specified object.
     */
    @Override
    public int compareTo(Object that) {
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
            // We will sort by owners's name. Since owner is a String, 
            // this uses the String's compareTo() method
            comparison = this.getOwner().compareTo(((Pet) that).getOwner());
            // if they have the same owner, we will sort by dog's name within 
            // that. So all of the Pets that belong to a single owner will be 
            // together            
            if (comparison == 0) {
                comparison = this.getName().compareTo(((Pet) that).getName());
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
        // tests Pet class only. To run, right-click 
        // and choose Run File
        Date date1 = new Date(7, 26, 2006);
        Date date2 = new Date(10, 17, 2017);
        Pet dog1 = new Pet(date1, "Toy Poodle", 10.2, "Eudora", "Anne");
        Pet dog2 = new Pet(date2, "Bulldog", 20.4, "Buster", "Jesse");
        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println("testing compareTo() ");
        System.out.println("dog1.compareTo(dog2) " + dog1.compareTo(dog2));
        System.out.println("dog1.compareTo(dog1) " + dog1.compareTo(dog1));
        System.out.println("dog2.compareTo(dog1) " + dog2.compareTo(dog1));
    }
}
