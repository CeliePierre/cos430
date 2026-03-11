/*
 *              Revision History
 * ***************************************************************
 * 3/19/2019  revised for console play.
 * 9/25/2016  Created for a GUI Cryptography game  AA
 */

package cryptoquotes;


import java.util.ArrayList;

/**
 * Holds an ArrayList of quotes
 * @date 9/25/2016
 * @author aapplin
 */
public class Database {
    private ArrayList<Quote> quotes; 


    /**
     * Default constructor simply creates the ArrayList
     */
    public Database() {
        quotes = new ArrayList<>();
    }
    
    /**
     * AddQuote receives a Quote object and if it is not null, it is 
     * added to the ArrayList
     * @param q a Quote object to be added.
     */
    public void addQuote(Quote q){
        if (q != null){
            quotes.add(q);
        }
    }

    /**
     * Retrieves an unused quote and runs it through the encryption routine
     * and returns it to the calling module.
     * @param i the index of a Quote object
     * @return an encrypted version of the quote or null if i is invalid
     */
    public Cryptogram getCryptogram(int i){
        if (i < quotes.size()  && !quotes.get(i).isUsed()){
            quotes.get(i).setUsed();
            return new Cryptogram(quotes.get(i));
        } else {
            return null;
        }
    }

    /**
     * accessor for the length of the ArrayList.
     * @return the length of the ArrayList
     */
    public int getSize(){
        return quotes.size();
    }
}
