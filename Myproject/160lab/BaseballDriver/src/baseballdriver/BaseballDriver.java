/***********************************************************************
 * REVISION HISTORY (Newest First)
 ***********************************************************************
 * 12/2020  - Anne Applin - a little house cleaning
 * 08/04/2016 - Anne Applin - formatting, JavaDoc, and a few methods
 * 2014       - David Briggs - original starting code and comments
 ***********************************************************************
 */
package baseballdriver;

import java.util.*;
import java.io.*;

/**
 * A class that is the driver for the Baseball project.
 * @author dbriggs
 */
public class BaseballDriver {

    // TreeSets are ordered sets (alpha or numeric or use the compareTo
    // written in a class). choices is a set of valid menu options.
    private static TreeSet<Character> choices = new TreeSet<>();
    private static League league;
    /**
     * a validation loop for menu choices. written by Anne Applin
     *
     * @param in a Scanner object for the keyboard
     * @return a valid menu choice based on the members of the choices set.
     */
    public char getMenuChoice(Scanner in) {
        Character choice;

        do {
            System.out.println("=================================");
            System.out.println("|  L  Look up Player            |");
            System.out.println("|  P  Print Pitching Statistics |");
            System.out.println("|  H  Print Hitting Statistics  |");
            System.out.println("|  U  Update Player Statistics  |");
            System.out.println("|  A  Print League Statistics   |");
            System.out.println("|  Q  Quit                      |");
            System.out.println("=================================");
            System.out.println("Enter your command or Q to quit.");
            choice = in.next().toUpperCase().charAt(0);
        } while (!choices.contains(choice)); 
        // does the set of valid choices include the character read?

        return choice;
    }
    
    public void run(String fileName) {
        // one Scanner for the keyboard and one for the file
        Scanner inFile = null;
        Scanner stdIn  = new Scanner(System.in);

        try {
            inFile = new Scanner(new File(fileName));
            if (!inFile.hasNext()) {
            System.err.println("No data in file " + fileName);
            return; // exits program
        }
            league = new League(inFile);
            inFile.close();
        } catch (Exception e) {
            System.err.println("Could not open the file.");
            return; // exits program
        }
        
        // if we are here, the file exists and is not empty 
        // so execution can continue including declaration of 
        // necessary variaables.

        // add the valid menu choices to the set.
        choices.add('L');
        choices.add('P');
        choices.add('H');
        choices.add('U');
        choices.add('A');
        choices.add('Q');

        // priming read for the LCV for the while loop
        char command = getMenuChoice(stdIn);

        // process user commands until Q is entered
        while (command != 'Q') {
            // declare the variables necessary for actual processing
            // that are needed INSIDE the while loop but not outside.
            String outputString = null;
            String team = null;

            int    playerNumber = 0;
            /* 
               a switch statement is a decision statement much like an 
               if..else - it allows for multiple branches based on exact
               values - not ranges. The break statement prevents execution
               from continuing into the next case. This is the ONLY place
               where you are allowed to use a break statement in this course.
            */
            switch (command) {
                case 'L':
                    System.out.println("Enter team name: ");
                    team = stdIn.next();
                    System.out.println("Enter player number: ");
                    playerNumber = stdIn.nextInt();
                    outputString = league.lookup(team, playerNumber);
                    break;

                case 'P':
                    System.out.println("Enter team name: ");
                    team = stdIn.next();
                    outputString = league.calcPitchingStats(team);
                    break;

                case 'H':
                    System.out.println("Enter team name: ");
                    team = stdIn.next();
                    outputString = league.calcHittingStats(team);
                    break;

                case 'U':
                    outputString = league.update(stdIn);
                    break;

                case 'A':
                    outputString = league.calculateHandedness();
                    break;
                default:
                    outputString = "Unrecognized command.";
            } // end switch
            System.out.println("\n" + outputString + "\n");
            command = getMenuChoice(stdIn); // update the LCV
        } // end while
    }

    /**
     * The main method for the project
     *
     * @param args the command line arguments the input file.
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("usage prog file");
            System.exit(1);  // exits the program
        }
        BaseballDriver driver = new BaseballDriver();
        driver.run(args[0]);

    } // end main
} // end class
