/*
 *              Revision History
 * ***************************************************************
 * THIS CLASS IS FINISHED DO NOT CHANGE
 * 3/19/2019  Created to demonstrate random key cryptography  AA
 */
package cryptoquotes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author aapplin
 */
public class CryptoQuotes {

    private Database db = new Database();
    private Cryptogram current;

    /**
     * Reads the input file of quotes. - This method is complete 
     * DO NOT CHANGE
     * @param filename command line argument 
     */
    public void readQuotes(String filename) {

        // now try to connect the sybolic name to the physical file
        try {
            Scanner inFile = new Scanner(new FileReader(filename));
            String line, quote, author;

            Quote quotation; // declare, do not create
            int count = 0;
            while (inFile.hasNext()) {
                // brand new object on each iteration.
                StringBuilder str = new StringBuilder();
                str.append(inFile.nextLine());
                line = inFile.nextLine();
                while (line.length() > 2) {
                    str.append(line);
                    line = inFile.nextLine();
                }
                quote = str.toString();
                author = inFile.nextLine();
                line = inFile.nextLine();
                // now create a new object to add to the database
                quotation = new Quote(quote, author);
                //System.out.println(quotation); // for debugging
                db.addQuote(quotation);
                count++;
            }
            inFile.close();
        } catch (FileNotFoundException ex) {
            // we catch it and print an error message
            System.out.println("File data.txt not found");
            // and exit in a controlled manner
            System.exit(1);
        }
    }

    /**
     * This method chooses a random quotation from the database.
     * @return a Cryptogram object
     */
    public Cryptogram chooseCurrent() {
        Cryptogram c;

        int range = db.getSize();
        do {
            int newQuote = (int) (Math.random() * range + 1);
            c = db.getCryptogram(newQuote);
        } while (c == null);
        return c;
    }

    /**
     * The actual driver for the game.  
     * @param filename The command line argument for the file name.
     */
    public void run(String filename) {

        readQuotes(filename);
        // play the game:
        boolean done = false;
        while (!done) {
            current = chooseCurrent();
            System.out.println(current.getCountsString());
            System.out.println(current);
            Scanner in = new Scanner(System.in);
            boolean hint = false;
            int iteration = 1;
            char e = ' '; // netbeans made me do it.
            char d = ' ';
            while (!current.isSolved()) {
                boolean valid = true; // innocent until proven guilty
                do {
                    valid = true; // for next iteration
                    System.out.print("Give me a letter from the quote and a letter " 
                        + "to substitute for it => ");
                    String s = in.next();
                    if (!Character.isDigit(s.charAt(0) ))
                        e = s.charAt(0);
                    else
                        valid = false;
                    s = in.next();
                    if (!Character.isDigit(s.charAt(0) ))
                        d = s.charAt(0);
                    else
                        valid = false;
                }while (!valid);
                if (current.processGuess(e, d)) {
                    System.out.println(current.getCountsString());
                    System.out.println(current);
                } else {
                    System.out.println("That substitution is wrong.");
                    iteration ++;
                }
                if (iteration  == 10) {
                    System.out.println("Want a hint? y/n => ");
                    char frustrated = in.next().charAt(0);
                    hint = (frustrated == 'y' || frustrated == 'Y');
                    if (hint)
                        System.out.println(current.getHint());
                    iteration = 0;
                }
            }
            System.out.println("The full quote was: ");
            System.out.println(current.getOriginalQuote().toString());
            System.out.println("Do you want to crack another one? y/n ==> ");
            char answer = in.next().charAt(0);
            done = (answer == 'n' || answer == 'N'); // update lcv
        }
        System.out.println("Hope you had fun!");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("usage: prog filename");
            System.exit(1);
        }
        CryptoQuotes game = new CryptoQuotes();
        game.run(args[0]);
    }

}
