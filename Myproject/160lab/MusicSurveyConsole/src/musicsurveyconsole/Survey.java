/**
 *                        Revision History
 *  ==========================================================================
 *  2022 rewritten in Java for the GUI lab as an experiment! Anne Applin 
 *  2003 Originally written at Ithaca College for comp171 by Sharon Stansfield. 
 */

package musicsurveyconsole;


import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Anne
 */
public class Survey {
    private String collegeName;
    private String location;
    private Date date;
    private int[] tallies;
    private HashMap<String, Student> registeredVoters;
    private int numberVoting;
    
    /**
     * Parameterized Constructor
     * @param collegeName College where the survey takes place
     * @param location campus where the survey takes place
     * @param date date of the survey
     */
    public Survey(String collegeName, String location, Date date) {
        this.collegeName = collegeName;
        this.location = location;
        this.date = date;
        tallies = new int[8];
        registeredVoters = new HashMap<>();
        numberVoting = 0;
    }

    /**
     * adds a student to the map
     * @param s a Student object
     */
    public void addStudent(Student s){
        registeredVoters.put(s.getIdNumber(), s);
    }

    /**
     * makes sure that the voter id is valid and that the voter has not
     * already voted.
     * @param idNumber  the student id of the voter
     * @return one of 3 strings: not registered, already voted, or a greeting.
     */
    public String validateVoter(String idNumber){
        if (!registeredVoters.containsKey(idNumber))
            return "You are not a registered voter.";
        else if (registeredVoters.get(idNumber).hasVoted())
            return "You have alread voted.";
        else 
            return "Hello, " + registeredVoters.get(idNumber).getFirstName();
    }

    /**
     * counts the actual vote and sets voted to true
     * @param vote the music type that the user entered
     * @param idNumber the student id of the voter
     */
    public void processCurrentVoter(int vote, String idNumber){
        numberVoting++;
        tallies[vote] ++;
        registeredVoters.get(idNumber).setVoted();
    }

    /**
     * finds the largest number of votes in the ballot box
     * @return the index of the music type with the most votes
     */
    public int determineSurveyResults(){
        int winningIndex = 0;
        for (int i = 0; i < tallies.length; i++){
            if (tallies[i] > tallies[winningIndex])
                winningIndex = i;
        }
        return winningIndex;
    }
    /**
     * Accessor for the property tallies[]
     * @return the entire array of vote counts
     */
    public int[] getTallies() {
        return tallies;
    }

    /**
     * returns the number of students who actually voted in the survey.
     * @return the number of voters
     */
    public int getNumberVoting() {
        return numberVoting;
    }

    /**
     * returns the size of the map of students
     * @return the number of registered students
     */
    public int getSize(){
        return registeredVoters.size();
    }
}
