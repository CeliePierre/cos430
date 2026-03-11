/**
 * Course:  CSCI 160
 * Class:   PetDog 
 * Uses:    Date class
 * Extends: Dog class
 * Implements: nothing
 */
package agilitycompetition;


/**
 *
 * @author aapplin
 */
public class PetDog extends Dog{
    private String name;
    private String owner;
    
    /**
     *
     */
    public PetDog(){
        this(new Date(0,0,0), "None", 0.0, "None", "None");
    }    
    /**
     * Parameterized Constructor
     * @param name input string that is the pet's name
     * @param dob input Date for the dog's date of birth
     * @param breed input string for breed of dog
     * @param weight input for the dog's weight
     * @param owner input for the pet's owner
     */
    public PetDog(Date dob, String breed, double weight, 
                   String name, String owner){
        super(dob, breed, weight);
        this.name = name;
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    /**
     * Accessor for the name attribute
     * @return the value of name
     */
    public String getName(){
        return name;
    }
    /**
     * Accessor for the owner attribute
     * @return the value of owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * toString allows an object to be directly printed by returning a String
     * that can be printed to the console or to a file.  The String is 
     * formatted
     * @return a formatted string that contains the values of all of the 
     * attributes of the object.
     */
    @Override
    public String toString() {
        return String.format("%s  %-15s%-15s",
                super.toString(), name, owner);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){
        // tests PetDog class only to run, right-click and choose Run File
        PetDog dog1 = new PetDog(); // default constructor
        Date date =  new Date(7, 26, 2006);
        System.out.println(dog1);
        PetDog dog2 = new PetDog( date, "Toy Poodle", 10.2,
                "Eudora", "Anne");
        System.out.println(dog2);
    }
    
}
