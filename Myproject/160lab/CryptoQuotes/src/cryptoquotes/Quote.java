/*
 *              Revision History
 * ***************************************************************
 * THIS CLASS IS FINISHED DO NOT CHANGE
 * 3/19/2019  revised for console play.
 * 9/25/2016  Created for a GUI Cryptography game  AA
 */
package cryptoquotes;



/**
 * A class to hold a quote, an author, and an author description
 * @date 9/25/2016
 * @author aapplin
 */
public class Quote {

    private String quotation;  //quotation
    private String author;     // author
    private boolean used;      // used in the current game

    /**
     * Parameterized constructor sets used to default and all other properties
     * to the inputs.  The quotation is split into lines of length 70 so they 
     * will not be two wide. 
     *
     * @param quotation - the String input for the quotation
     * @param author - the String input for the author's name
     * @param description - the String input for the description of the author
     */
    public Quote(String quotation, String author) {
        
        this.quotation = quotation;
        this.author = author;
        this.used = false;
    }
    /**
     * presently unused  I want to be able to add to the database someday
     * @param quotation a new quote
     */
    protected void setQuotation(String quotation){
        this.quotation = quotation;
    }
    /**
     * presently unused  I want to be able to add to the database someday
     * @param quotation a new author
     */
    protected void setAuthor(String author){
        this.author = author;
    }
    /**
     * changes used to true so this quote won't be used again today
     * could take it out of the arrayList, but this seemed like a good
     * idea.
     */
    public void setUsed() {
        this.used = true;
    }

    /**
     * Returns the current value of the used property
     *
     * @return boolean value true if this quote has been used today and false
     * otherwise
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Returns the quotation String
     *
     * @return value of the quotation String
     */
    public String getQuotation() {
        return quotation;
    }

    /**
     * Returns the Author's name String
     *
     * @return value of the author String
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns a formatted String that is the quotation \n author, description
     * Is only needed for the Unit Test.
     *
     * @return a string that is the quotation properties nicely formatted.
     */
    @Override
    public String toString() {
        String eol = System.lineSeparator();
        StringBuilder str = new StringBuilder();
        if (quotation.length() > 70) {            
            int subLength = 0; // for counting the length of the output line
            String[] array = quotation.split("[\\s]");
            for (int i = 0; i < array.length; i++) {
                if (subLength >= 70) {
                    str.append(eol);
                    subLength = 0;
                }
                str.append(array[i]).append(" "); // the next word and a space
                subLength += (array[i].length() + 1); // add that length
            }
        }else{
            str.append(quotation);
        }
        return str.toString() + eol + author;
    }

    /**
     * Unit test for Quote
     *
     * @param args - command line args... not used.
     */
    public static void main(String[] args) {
        Quote q = new Quote("We celebrate the past to awaken the future.",
                "John F. Kennedy, 35th US president");

        //System.out.println(q);
    }

}
