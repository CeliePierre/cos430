package KW.CH02;

/**
 * The DirectoryEntry objects will contain name-and-number pairs.
 * The name in immutablble; that is, it cannot be changed.
 * @author Koffman & Wolfgang
 */
public class DirectoryEntry {

    // Data Fields
    /**The name of the individual represented in the entry. */
    private String name;
    /** The phone number for this individual. */
    private String number;

    // Constructor
    /**
     * Creates a new DirectoryEntry with the specified name and number
     * @param name The name of this individual
     * @number The phone number for this indvidual
     */
    public DirectoryEntry(String name,
            String number) {
        this.name = name;
        this.number = number;
    }

    //Methods
    /**
     * Retrieves the name.
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the number.
     * @return The number.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number to the specified value
     * @param number The new value for the number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}
