/**
 * Driver for the Agility Competition showing the use of inheritance and
 * implementation of an interface.
 * The driver performs the input from a file to the AgilityContest object
 * of the competitor objects, finds the winners, prints the sorted list of 
 * all competitors and prints a very small press release.
 */
package agilitycompetition;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aapplin
 */
public class AgilityCompetitionDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // create a contest object
        AgilityContest contest = new AgilityContest("South Portland",
                "Blue Bison", new Date(5, 12, 2015), args[0]);
        // print them
        printCompetitors(contest);
        // print a press release
        //printPressRelease(contest);
        findOwnersWithPartialNames();
        
       
    }
    public static void findOwnersWithPartialNames(){
        Scanner in  = new Scanner(System.in);
        System.out.println("enter the partial owner name: ");
        String partial = in.next();
        
    }

    /**
     *
     * @param ac
     */
    public static void printCompetitors(AgilityContest ac){
        double age; // since this changes based on the current date
                    // we don't store it, we calcualate it on the fly
        Date contestDate = ac.getContestDate(); // call the method once
        // local storage so we don't call get.() but once
        Competitor [] compArray = ac.getAllContestants(); 
        for (int i = 0; i < compArray.length; i++){
            age = (contestDate.difference(compArray[i].getBirthDate())) / 364.5;
            System.out.printf("%4d   %s%6.2f\n", i, compArray[i] , age);
        }
    }

    /**
     *
     * @param ac
     */
    public static void printPressRelease(AgilityContest ac){
        Date contestDate = ac.getContestDate(); // call the method once
        double age = (contestDate.difference(ac.getWinner().getBirthDate())) 
                / 364.5;
        int years = (int)age;
        String timeStr = ac.getWinner().getTime().getTimeInWords();
        
        System.out.println("It was a beautiful day here at the park in " +
                ac.getContestLocation() + " where the 3rd annual city wide " +
                "agility competition \nsponsored by "+ ac.getContestSponsor() +
                " was held. Top honors go to " + ac.getWinner().getName() +
                " a " + ac.getWinner().getWeight() + "lb " + 
                years + " year old " +
                ac.getWinner().getBreed() + " who ran the course in \n" +
                timeStr);
    }
}
