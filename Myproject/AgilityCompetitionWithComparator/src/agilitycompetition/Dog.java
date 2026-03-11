/**
 * Course: CSCI 160
 * Class: Dog 
 * Uses: Date class
 * Extends: nothing
 * Implements: nothing
 * Presumption: All dogs have these attributes 
 */
package agilitycompetition;

/**
 *
 * @author aapplin
 */
public class Dog {
    
    private Date birthDate;
    private String breed;
    private double weight;
    
    /**
     * Default Constructor
     */
    public Dog(){
        this(new Date(0,0,0),"None",0.0);
    }

    /**
     *
     * @param dob
     * @param breed
     * @param weight
     */
    public Dog(Date dob, String breed, double weight){
        this.birthDate = dob;
        this.breed = breed;
        this.weight = weight;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    
    /**
     *
     * @return
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
     * that can be printed to the console or to a file.  
     * @return a formatted string representing the values of the attributes
     * for a dog object.
     */
    @Override
    public String toString() {
        return String.format("%-15s%-25s%7.2f",
                birthDate.toString(), breed, weight);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){
        // tests Dog class only to run, right-click and choose Run File
        Dog dog1 = new Dog(); // default constructor
        Date date =  new Date(7, 26, 2006);
        System.out.println(dog1);
        Dog dog2 = new Dog(date, "Toy Poodle", 10.2 );
        System.out.println(dog2);
    }
    
}
