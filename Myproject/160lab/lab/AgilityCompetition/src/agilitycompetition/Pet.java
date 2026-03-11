package agilitycompetition;

/**
 *
 * @author kalalajoel
 */
public class Pet extends Dog {

    private final String name;
    private final String owner;

    public Pet(Date dob, String breed, double weight,
            String name, String owner) {
        super(dob, breed, weight);
        this.owner = owner;
        this.name = name;
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
     * Unit Test for the dog class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // tests Dog class only. to run, right-click 
        // and choose Run File
        Date date1 = new Date(7, 26, 2006);
        Date date2 = new Date(10, 17, 2017);

        Pet dog1 = new Pet(date1, "Toy Poodle", 10.2, "Eudora",
                "Anne");
        Pet dog2 = new Pet(date2, "Bulldog", 20.4, "Bubba",
                "Jesse");
        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println("testing compareTo() ");
        System.out.println("dog1.compareTo(dog2) " + dog1.compareTo(dog2));
        System.out.println("dog1.compareTo(dog1) " + dog1.compareTo(dog1));
        System.out.println("dog2.compareTo(dog1) " + dog2.compareTo(dog1));
    }

}
