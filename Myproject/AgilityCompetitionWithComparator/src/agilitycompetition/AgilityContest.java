/**
 * Course:  CSCI 160
 * Class:   AgilityContest 
 * Uses:    Date, Competitor
 * Extends: nothing
 * Implements: nothing
 */
package agilitycompetition;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;


/**
 *
 * @author aapplin
 */
public class AgilityContest {
    private String contestLocation;
    private String contestSponsor;
    private Date contestDate;
    private Competitor[] contestants;         
    private Competitor winner;
    private Competitor second;
    private Competitor third;
    
    /**
     * Default Constructor
     */
    public AgilityContest(){
        this("None", "None", new Date(), null);
    }

    /**
     * Parameterized Constructor
     * @param location the city where the competition was held
     * @param sponsor the company sponsoring the competition
     * @param date the date of the competition
     */
    public AgilityContest(String location, String sponsor, Date date, 
            String filename){
        contestLocation = location;
        contestSponsor = sponsor;
        contestDate = date;
        ArrayList <Competitor> list = new ArrayList<>(); 
        Scanner inFile = null;
        if (filename == null){
            return;
        }
        // now try to connect the sybolic name to the physical file
        try {
            inFile = new Scanner(new FileReader(filename));
            // if the physical file doesn't exist it throws an exception
        } catch (FileNotFoundException ex) {
            // we catch it and print an error message
            System.out.println("File data.txt not found");
            // and exit in a controlled manner
            System.exit(1);
        }
        // otherwise.... we continue with reading things in
        // String name, Date dob, String breed, double weight, String owner, 
        // Time time
        String name, breed, owner;
        double weight;
        ElapsedTime time;
        Date dob;
        int int1, int2, int3, int4;
        Competitor competitor; // declare, do not create
        while (inFile.hasNext()) {
            name = inFile.next();
            int1 = inFile.nextInt();
            int2 = inFile.nextInt();
            int3 = inFile.nextInt();
            dob = new Date(int1, int2, int3);
            breed = inFile.nextLine();
            weight = inFile.nextDouble();
            owner = inFile.next();
            int1 = inFile.nextInt();
            int2 = inFile.nextInt();
            int3 = inFile.nextInt();
            int4 = inFile.nextInt();
            time = new ElapsedTime(int1, int2, int3, int4);
            // now create a new object to add to the competition
            competitor = new Competitor(dob, breed, weight, name, owner,  time);
            //System.out.println(competitor); // for debugging
            list.add(competitor);            
        }
        inFile.close();
        contestants = new Competitor[list.size()];
        contestants = list.toArray(contestants);
        Arrays.sort(contestants);
        determineWinners();
    }

    /**
     * Mutator for contest location
     * @param contestLocation input value for the city
     */
    public void setContestLocation(String contestLocation) {
        this.contestLocation = contestLocation;
    }

    /**
     * Mutator for the sponsor
     * @param contestSponsor input value for the sponsor company
     */
    public void setContestSponsor(String contestSponsor) {
        this.contestSponsor = contestSponsor;
    }

    /**
     * Mutator for contest date
     * @param contestDate input for the contest date
     */
    public void setContestDate(Date contestDate) {
        this.contestDate = contestDate;
    }

    /**
     * Sorts the ArrayList and then assigns the first second and third
     * place winners from the resulting sorted ArrayList 
     */
    public void determineWinners(){       
        winner = contestants[0];
        second = contestants[1];
        third  = contestants[2];
    }

    /**
     * Accessor for contest location
     * @return the string for the contest location
     */
    public String getContestLocation() {
        return contestLocation;
    }

    /**
     * Accessor for the contest sponsor
     * @return the string for the contest sponsor
     */
    public String getContestSponsor() {
        return contestSponsor;
    }

    /**
     * Accessor for Contest Date
     * @return the contestDate object
     */
    public Date getContestDate() {
        return contestDate;
    }

    /**
     * Accessor for the ArrayList
     * @param i the index of the element we want
     * @return the contestant at location i
     */
    
    public Competitor[] getAllContestants(){
        return contestants;
    }
    /**
     * Accessor for the winner Competitor object
     * Preconditions: the arrayList has been loaded and determineWinners()
     *     has been called
     * @return the object identified as the competitor with the lowest 
     * time on the course.
     */
    public Competitor getWinner() {
        return winner;
    }

    /**
     * Accessor for the second place Competitor object
     * * Preconditions: the arrayList has been loaded and determineWinners()
     *     has been called
     * @return the object identified as the competitor with the second lowest 
     * time on the course.
     */
    public Competitor getSecond() {
        return second;
    }

    /**
     * Accessor for the third place Competitor object
     * Preconditions: the arrayList has been loaded and determineWinners()
     *     has been called
     * @return the object identified as the competitor with the third lowest 
     * time on the course.
     */
    public Competitor getThird() {
        return third;
    }
  
    
    
    public static void main(String[]args){
        if (args.length == 0) {
            System.err.println("usage: prog songfile");
            return;
        }
        AgilityContest contest = new AgilityContest("South Portland",
                "Blue Bison", new Date(5, 12, 2015), args[0]);
        Competitor[] compArray = contest.getAllContestants();
      
        for (int i = 0; i < compArray.length && i < 10; i++)
            System.out.println(compArray[i]);
    }
}
