/*
 *              Revision History
 * ***************************************************************
 *  2021 - rewrote the compareTo()
 *  2018 - original verion aa
 */
package vetclinicdriver;

/**
 * A class to model a dog at a shelter.  
 * @author aapplin
 */
public class ShelterDog extends Dog{
     private final String name;

    public ShelterDog(Date dob, String breed, 
            double weight, String name) {
        super(dob, breed, weight);
        this.name = name;
    }
    /**
     * Accessor for date of birth
     * @return the Date object representing date of birth
     */
    @Override
    public Date getBirthDate() {
        return birthDate;
    }
    /**
     * Accessor for breed
     * @return a string object that is the breed
     */
    @Override
    public String getBreed() {
        return breed;
    }

    /**
     * Accessor for weight
     * @return the value of weight 
     */
    @Override
    public double getWeight() {
        return weight;
    }
    /**
     * Accessor for the name attribute
     * @return the value of name
     */
    public String getName(){
        return name;
    }
    /**
     * toString allows an object to be directly printed by returning a String
     * that can be printed to a file.  
     * @return a formatted string representing the values of the attributes
     * for a Pet object.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        String eol = System.lineSeparator(); // end of line for this OS
        str.append(String.format("%4d %4d %4d %-25s", 
                birthDate.getDay(), birthDate.getMonth(), 
                birthDate.getYear(), breed));
        str.append(eol);
        str.append(String.format("%7.2f  %-15s", weight, name));
        return str.toString();
    }
    /**
     * compareTo (abstract method of the Comparable Interface) is implemented 
     * to impose a natural ordering on a group of objects.
     * compareTo is used by the Collections.sort routine to allow us to sort
     * the competitors belonging to some Java collection.
     * @param that is the Dog object we are comparing this one to
     * @return a negative integer, zero, or a positive integer if this object 
     * comes before, is equal to, or comes after the specified object.
     */
    @Override
    public int compareTo(Object that) {
        // some constants for clarity
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1; 
        int comparison = EQUAL;
        // shouldn't be any null objects, but if there are
        // put them at the end
        if (that == null)
            comparison = AFTER; 
        //this optimization is usually worthwhile, and can
        //always be added - if the addresses are the same... they are equal
        else if (this == that) 
            comparison = EQUAL;
        
        //  we will sort by dog's name 
        else
            comparison = this.getName().compareTo(((ShelterDog)that).getName());
        return comparison;
    } 
     
}
