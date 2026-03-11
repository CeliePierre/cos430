/**
 * Revision History
 *******************************************************************
 * 02.01.2021  Updated the lab
 * 2014  cleaned up and JavaDoc added
 * 2005  original version written
 */
package agilitycompetition;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Course:  CSCI 160
 * Project: AgilityCompetition (Version 1 Solution)
 * File:    AgilityContest.java 
 * Uses:    Date, Competitor
 * Extends: nothing
 * Implements: nothing
 * A class to model any kind of sponsored contest. The ArrayList can 
 * be filled with ANY type of objects that are comparable/sortable.
 * @author aapplin
 */
public class Contest {
    // global properties
    private String contestLocation;
    private String contestSponsor;
    private Date contestDate;
    private ArrayList <Competitor> contestants = new ArrayList<>();     
    private Competitor winner;
    private Competitor second;
    private Competitor third;
    
    /**
     * Parameterized Constructor
     * @param location the city where the competition was held
     * @param sponsor the company sponsoring the competition
     * @param date the date of the competition
     */
    public Contest(String location, String sponsor, Date date){
        contestLocation = location;
        contestSponsor = sponsor;
        contestDate = date;
    }

    /**
     * Adds a new competitor to the ArrayList contestants
     * @param d a new competitor
     */
    public void addContestant(Competitor d) {
        this.contestants.add(d);
    }
    
    /**
     * Sorts the ArrayList and then assigns the first second and third
     * place winners from the resulting sorted ArrayList.
     * We can use the Java Collections.sort because we wrote a compareTo() 
     * method for Dog and used the implements clause in the class declaration.
     */
    public void determineWinners(){
        Collections.sort(contestants);
        winner = contestants.get(0);
        second = contestants.get(1);
        third  = contestants.get(2);
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
     * @return the Dog object at location i
     */
    public Competitor  getContestantAt(int i) {
        return contestants.get(i);
    }

    /**
     * Accessor for the size of the ArrayList which will allow a for loop
     * to be used in the client code (main) for printing or searching.
     * @return the size of the ArrayList
     */
    public int getNumberOfContestants(){
        return contestants.size();
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
     * Preconditions: the arrayList has been loaded and determineWinners()
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
    
}
