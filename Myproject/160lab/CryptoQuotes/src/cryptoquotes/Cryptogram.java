/*
 *              Revision History
 * ***************************************************************
 * 3/19/2019  revised for console play.
 * 9/25/2016  Created for a GUI Cryptography game  AA
 */
package cryptoquotes;

/**
 * A class to hold a Cryptogram game object. The properties are: A Quote object
 * for the originalQuote A Quote object for the encrypted Quote A Quote object
 * for the partially decrypted quote. A 2-D array (cryptKey) that is a randomly
 * generated encryption key such that the each letter will be assigned a random
 * letter of the alphabet for this particular game object. It will provide the
 * ability to decrypt the game object as well. A boolean (solved) that reports
 * when the encrypted quote has been fully decrypted.
 */


import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @date 9/25/2016
 * @author aapplin
 */
public class Cryptogram{

    private Quote originalQuote;  // the original quote
    private Quote encryptedQuote; // the encrypted quote
    private Quote decryptedQuote; // partially decrypted quote
    private char[][] cryptKey;
    private boolean solved;
    private int[] counts;  // frequency of the letters

    private TreeSet <Character> punctuation = new TreeSet<>();
    /**
     * Constructor for a Cryptogram.  It receives a Quote object and 
     * initializes all properties using that Quote object
     * @param originalQuote the Quote object to be encrypted for game play
     */
    public Cryptogram(Quote originalQuote) {
        this.originalQuote = originalQuote;
        counts = new int[26];
        // row 0 has letters in order, row 1 will be the substitutions
        cryptKey = new char[2][26]; 
        encrypt();// initializes cryptKey, encryptedQuote, and decryptedQuote
        solved = false;
        char[] temp = {'.','!','\'','\"', '?','(',')',',',' '};
        for (int i = 0; i < temp.length; i++)
            punctuation.add(temp[i]);
    }

    /**
     * Creates a randomly generated encryption key and uses it to create the
     * encrypted string leaving spaces and punctuation as they are.
     * Be careful as you type the code!
     */
    public void encrypt() {
        
    }

    /**
     * generateKey creates a two-D array where the first row is just the
     * alphabet, but the second row is a randomly generated corresponding letter
     * that will be used to do the encryption. 
     * Be careful as you type the code!
     */
    private void generateKey() {
        
    }

    /**
     * our cryptKey is simply an array of characters where d is the encrypted
     * character substituted for e
     *
     * @param e - possible encrypted letter
     * @param d - matching originalQuote letter
     * @return true if they match and false otherwise.
     */
    public boolean guessMatches(char e, char d) {
        return cryptKey[1][(d - 'a')] == e;
    }

    /**
     * process guess has to deal with upper case / lower case substitutions
     *
     * @param e  the letter in the encrypted cryptogram
     * @param d  the possible real letter substitution
     * @return true if a substitution is found and false otherwise
     */
    public boolean processGuess(char e, char d) {
        boolean changed = false;
        e = Character.toLowerCase(e);
        d = Character.toLowerCase(d);
        if (guessMatches(e, d)) { // we have a match
            //go through the quote looking for occurances of the d
            // and replace the cooresponding letter in the decryptedQuote with 
            // the d - checking for upper case and lower case
            char[] dQuote = decryptedQuote.getQuotation().toCharArray();
            String str = originalQuote.getQuotation();
            int len = originalQuote.getQuotation().length();
            char uCase = (char) Character.toUpperCase(d);
            for (int i = 0; i < len; i++) {
                if (str.charAt(i) == d) {
                    dQuote[i] = d;
                } else if (str.charAt(i) == uCase) {
                    dQuote[i] = uCase;
                }
            }
            
            // do the same thing with the author
            char[] dAuthor = decryptedQuote.getAuthor().toCharArray();
            str = originalQuote.getAuthor();
            len = originalQuote.getAuthor().length();
            for (int i = 0; i < len; i++) {
                if (str.charAt(i) == d) {
                    dAuthor[i] = d;
                } else if (str.charAt(i) == uCase) {
                    dAuthor[i] = uCase;
                }
            }
            // turn them all back into strings
            decryptedQuote.setQuotation(new String(dQuote));
            decryptedQuote.setAuthor(new String(dAuthor));
            changed = true;
            counts[e - 'a'] = 0; // none of this letter left
            solved = originalQuote.getQuotation().
                    equals(decryptedQuote.getQuotation())
                    && originalQuote.getAuthor().
                            equals(decryptedQuote.getAuthor());
        }

        return changed;
    }

    /**
     * Checks to see if the puzzle has been solved.
     * @return the value of solved
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * accessor for the property encryptedQuote.
     * @return the encryptedQuote
     */
    public Quote getEncryptedQuote() {
        return encryptedQuote;
    }
    /**
     * Accessor for the property
     * @return the original quote
     */
    public Quote getOriginalQuote() {
        return originalQuote;
    }
    /**
     * Accessor the property
     * @return the decrypted quote
     */
    public Quote getDecryptedQuote() {
        return decryptedQuote;
    }
    /**
     * Used mostly for debugging... could be changed to private.
     * @return the generated cryptoKey
     */
    public char[][] getCryptKey() {
        return cryptKey;
    }
    /**
     * accessor for the frequencies array.
     * @return  the counts array
     */
    public int[] getCounts(){
        return counts;
    }
    /**
     * Returns the first substitution in the key that isn't 0 in the counts
     * @return a string with a substitution
     */
    public String getHint(){
        int i = 0;
        while (i < counts.length && counts[i] == 0)
            i++;
        return String.format("Try '%s' is a '%s'.", 
                cryptKey[1][i], cryptKey[0][i]);
    }
    /**
     * A method to display the character frequencies
     * @return a string that is the frequencies of the encrypted letters
     */
    public String getCountsString(){
        StringBuilder str = new StringBuilder();
        str.append("The frequencies :").append(System.lineSeparator());
        str.append("  ");
        for (int i = 0; i < cryptKey[0].length; i++) {
            char cur = cryptKey[0][i];
            str.append(String.format("%-3s", cur));
        }
        str.append(System.lineSeparator()); 
        for (int i = 0; i < counts.length; i++) {
            str.append(String.format("%3d", counts[i]));
        }
        return str.toString();
        
    }
    /**
     * returns the quote as decrypted so far in the game.
     * @return a mixture of the encrypted quote and the decrypted quote.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        String dQuote = decryptedQuote.getQuotation();
        String dAuthor = decryptedQuote.getAuthor();
        String eQuote = encryptedQuote.getQuotation();
        String eAuthor = encryptedQuote.getAuthor();
        int[] lineBreaks = new int[4];  //if it's bigger than that ...
        int charCount = 0;
        int index = 0;
        for (int i = 70; i < dQuote.length(); i += 70) {
            lineBreaks[index] = dQuote.indexOf(" ", i);
            index++;
        }
        index = 0;
        for (int i = 0; i < dQuote.length(); i++) {
            if (i == lineBreaks[index]) {
                str.append(System.lineSeparator());
                index++;
            }
            if (dQuote.charAt(i) != '_'
                    && !punctuation.contains(dQuote.charAt(i))) {
                str.append(String.format("%s", ConsoleColors.RED_BOLD 
                        + dQuote.charAt(i)) + ConsoleColors.RESET);
            } else {
                str.append(eQuote.charAt(i));
            }
            charCount++;
        }
        str.append(System.lineSeparator());
        // now do the author
        for (int i = 0; i < dAuthor.length(); i++) {
            if (dAuthor.charAt(i) != '_'
                    && !punctuation.contains(dAuthor.charAt(i))) {
                str.append(String.format("%s", ConsoleColors.RED_BOLD 
                        + dAuthor.charAt(i)) + ConsoleColors.RESET);
            } else {
                str.append(eAuthor.charAt(i));
            }
        }
        return str.toString();
    }



    /**
     * Unit Test for Cryptogram
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Quote q = new Quote("\"We celebrate the past to awaken the future.\"",
                "John F. Kennedy, 35th US president");
        Cryptogram c = new Cryptogram(q);
        System.out.println("The encryption key:");
        for (int i = 0; i < c.cryptKey[0].length; i++) {
            char cur = c.cryptKey[0][i];
            System.out.printf("%-3s", cur);
        }
        System.out.println();
        for (int i = 0; i < c.cryptKey[0].length; i++) {
            char cur = c.cryptKey[1][i];
            System.out.printf("%-3s", cur);
        }
        System.out.println();
        System.out.println("Original Quote");
        System.out.println(c.originalQuote);
        System.out.println("Encrypted Quote");
        System.out.println(c.encryptedQuote);
        System.out.println("Decrypted Quote for display");
        System.out.println(c.decryptedQuote);
        System.out.println(c.getCountsString());
        
        System.out.println();
        System.err.println("Now it's going to test itself");
        int i = 0;
        while (!c.isSolved()){  
            char d = c.cryptKey[0][i];
            char e = c.cryptKey[1][i];            
            if (c.processGuess(e, d)){
                System.out.println(c.getCountsString());
                System.out.println(c);
            }else{
                System.out.println("That substitution is wrong.");
            }
            i++;
        } 
    }
}
