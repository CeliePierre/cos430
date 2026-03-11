/**
 * Course:  CSCI 160
 * Class:   Competitor represents a PetDog that is also competing in an
 *          agility competition. Adds an elapsed time as a property.
 * Uses:    ElapsedTime
 * Extends: PetDog
 * Implements: Comparable
 *       compareTo allows us to sort by elapsedTime from shortest to longest
 */
package agilitycompetition;

import java.util.Comparator;

/**
 *
 * @author aapplin
 */
public class Competitor extends PetDog implements Comparable{
    private ElapsedTime time; 
    /**
     *  Default Constructor
     */
    public Competitor(){
        this(new Date(), "None", 0.0, "None", "None", new ElapsedTime());
    }
    /**
     * Parameterized Constructor
     * @param name  String for the dog's name
     * @param dob   Date for the dog's date of birth
     * @param breed String for the dog's breed
     * @param weight value of the dog's weight
     * @param owner String for the dog's owner
     * @param time ElapsedTime that the dog took on the agility course
     */
    public Competitor(Date dob, String breed, double weight,
             String name, String owner, ElapsedTime time){
        super( dob, breed, weight, name, owner);
        this.time = time;
    }
    public Competitor(int month, int day, int year, String breed, double weight,
             String name, String owner, ElapsedTime time){
        super( new Date(month, day, year), breed, weight, name, owner);
        this.setTime(time);
    }
    /**
     * Mutator for the course time 
     * @param time an ElapsedTime object
     */
    public void setTime(ElapsedTime time) {
        this.time = time;
    }
    
    /**
     * Accessor for the course time
     * @return the value of course time
     */
    public ElapsedTime getTime(){
        return time;
    }

    /**
     * toString allows an object to be directly printed by returning a String
     * that can be printed to the console or to a file.
     * @return a formatted string with all of the values of the attributes.
     */
    public String toString(){
        return String.format("%s  %-12s" ,
                super.toString(), time.toString());
    }

    /**
     * compareTo (abstract method of the Comparable Interface) is implemented 
     * to impose a natural ordering on a group of objects.
     * compareTo is used by the collections.sort routine to allow us to sort
     * the competitors belonging to some Java collection.
     * adapted from http://www.javapractices.com/topic/TopicAction.do?Id=10
     * @param that the competitor we are comparing this one to
     * @return a negative integer, zero, or a positive integer as this object 
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object that) {
        int comparison;
        // shouldn't be any null objects, but if there are
        // put them at the end
        if (that == null)
            comparison = 1; 
        //this optimization is worthwhile
        // if the addresses are the same... objects are equal
        else if (this == that) 
            comparison =  0;
        // otherwise . . . 
        // first we sort by time; there is a compareTo in the Time class
        // casting is necessary.
        else{
            comparison = this.time.compareTo(((Competitor)that).time);        
            if (comparison == 0){
                // if they finished in the same time 
                // then we sort by dog's owner - uses the String compareTo
                comparison = this.getOwner().
                                compareTo(((Competitor)that).getOwner());
                if (comparison == 0){
                    // if they have the same owner also
                    // then by dog's breed - uses the String compareTo
                    comparison = this.getBreed().
                            compareTo(((Competitor)that).getBreed());
                    if (comparison == 0){
                        // if the breed is the same also...
                        // then by dog's name - uses the String compareTo
                        comparison = this.getName().
                                compareTo(((Competitor)that).getName());
                    }
                }
            }
        }
        return comparison;  
    }
    /**
     * Embedded class used to compare two owner names
     * Used for sorting by owner name 
     */
    public static class CmpOwner extends CmpCnt 
            implements Comparator<Competitor>{

        @Override
        public int compare(Competitor c1, Competitor c2) {
            cmpCnt++;
            return c1.getOwner().compareToIgnoreCase(c2.getOwner());
        }
        
    }
    /**
     * Embedded class used to compare two competitor names
     * used for sorting by the competitor names
     */
    public static class CmpName extends CmpCnt
            implements Comparator<Competitor>{
        @Override
        public int compare (Competitor c1, Competitor c2) {
            cmpCnt++;
            return c1.getName().compareToIgnoreCase(c2.getName());
        }
    }
    /**
     * Unit test for Competitor
     * @param args the command line arguments
     */
    public static void main(String [] args){
        // tests Competitor class only. To run, right-click and choose Run File
        Competitor dog1 = new Competitor(); // default constructor
        System.out.println(dog1);
        Date date =  new Date(7, 26, 2006);
        ElapsedTime time = new ElapsedTime(0, 59, 59, 1127);
        Competitor dog2 = new Competitor(date, "Toy Poodle", 10.2, 
                "Eudora", "Anne", time);
        System.out.println(dog2);
        System.out.println("testing compareTo:");
        System.out.println("Dog1 vs Dog2 = " + dog1.compareTo(dog2));
        System.out.println("Dog2 vs Dog1 = " + dog2.compareTo(dog1));

        System.out.println("testing compare:");
        CmpOwner ca = new CmpOwner();
        System.out.println("Dog1 vs Dog2 = = " + ca.compare(dog1, dog2));
        System.out.println("Dog2 vs Dog1 = " + ca.compare(dog2, dog1));

    }
}
