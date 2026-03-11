/**
 * Revision History
 *******************************************************************
 * 02.01.2021  Updated the lab
 * 2014  cleaned up and JavaDoc added
 * 2005  original version written
 */
package agilitycompetition;

/**
 * Course: CSCI 160
 * Project: AgilityCompetition (Version 1)
 * Class: Dog
 * Uses: Date class
 * Extends: nothing
 * Implements: Comparable
 * Driver for the Agility Competition V1 showing the use of inheritance and
 * implementation of an interface.
 * The driver performs the input from a file to the AgilityContest object
 * of the competitor objects, finds the winners, prints the sorted list of
 * all competitors and prints a very small press release.
 * 
 * @author aapplin
 */


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AgilityCompetitionDriver {

    // create an Contest object as a property
    private Contest ac = new Contest("South Portland",
            "Blue Bison", new Date(5, 12, 2015));

    /**
     * This method reads the input file and fills the contest
     * with Dog objects
     * @param fileName the filename from the command line args
     */
    public void readCompetitorFile(String fileName) {

        // now try to connect the sybolic name to the physical file
        try {
            // if the physical file doesn't exist it throws an exception
            Scanner inFile = new Scanner(new FileReader(fileName));
            String name, breed, owner;
            double weight;
            ElapsedTime time;
            Date dob;
            int int1, int2, int3, int4;// use 1 - 3 for dob and 1 - 4 for time
            Dog competitor; // declare, do not create
            while (inFile.hasNext()) {                
                int1 = inFile.nextInt();
                int2 = inFile.nextInt();
                int3 = inFile.nextInt();
                dob = new Date(int1, int2, int3);
                // because breed can have multiple words with spaces
                breed = inFile.nextLine().trim();
                // and the nextLine() bug doesnt' hurt us because the
                // next thing read in is numeric.
                weight = inFile.nextDouble();
                owner = inFile.next();
                name = inFile.next();
                int1 = inFile.nextInt();
                int2 = inFile.nextInt();
                int3 = inFile.nextInt();
                int4 = inFile.nextInt();
                time = new ElapsedTime(int1, int2, int3, int4);
                // now create a new object to add to the competition
                competitor = new Dog(dob, breed, weight, name, owner, time);
                //System.out.println(competitor); // for debugging
                ac.addContestant(competitor);

            }
            inFile.close();
            ac.determineWinners();
        } catch (FileNotFoundException ex) {
            // we catch it and print an error message
            System.out.println("File " + fileName +" not found");
            // and exit in a controlled manner
            System.exit(1);
        }
    }

    /**
     *  This method prints all of the competitors.  Mostly
     *  something like this is used for debugging just to make
     *  sure that everything was read in.
     */
    public void printCompetitors() {
        double age; // since this changes based on the current date
        // we don't store it, we calcualate it on the fly
        Date contestDate = ac.getDate(); // call the method once
        Dog current; // local storage so we don't call get.() but once
        for (int i = 0; i < ac.getNumberOfContestants(); i++) {
            current = ac.getContestantAt(i);
            age = (contestDate.difference(current.getBirthDate())) / 364.5;
            System.out.printf("%4d   %s%6.2f\n", i, current, age);
        }
    }

    /**
     *  This method prints a short "blurb" for the local newspaper.
     */
    public void printPressRelease() {
        Dog winner = ac.getWinner();
        double age = (ac.getDate().difference
                        (winner.getBirthDate())) / 364.5;
       
        String timeStr = winner.getTime().getTimeInWords();

        System.out.printf("It was a beautiful day here at the park in "
                + "%s where the 3rd annual city wide "
                + "%nagility competition \nsponsored by %s"
                + " %nwas held. Top honors go to %s,"  
                + " a %.2f lb %.0f year old %s"
                + " who ran the course in %s.\n",
                 ac.getLocation(), ac.getSponsor(),
                 winner.getName(), winner.getWeight(),
                 age ,winner.getBreed(), timeStr);
    }

    public void run(String fileName) {
        // enroll the competitors
        readCompetitorFile(fileName);
        // print them
        printCompetitors();
        // print a press release
        printPressRelease();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("usage: progname inputFile");
            System.exit(1);
        }
        AgilityCompetitionDriver driver = new AgilityCompetitionDriver();
        driver.run(args[0]);
    }
}