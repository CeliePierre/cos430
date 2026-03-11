/**
 **********************************************************************
 * REVISION HISTORY (newest first)
 * THIS CLASS IS FINISHED - DO NOT CHANGE  
 **********************************************************************
 *  the date and YOUR NAME  - implemented loadHerd()
 * 04/19/2017 - Anne Applin - added documentation
 * 2015 - David Briggs - original starting code
 *********************************************************************
 * Driver for the project. 
 * PLEASE NOTE:  Use Unit testing to test your classes and 
 * ignore the errors in this file until you have all classes 
 * working. 
 */
package bullsandcows;
import java.util.*;
import java.io.*;
/**
 * The driver for the Holstein-Fresian Pedigree application.
 * @author various
 */
public class BullsAndCows {
    // Properties of the class
    private static Database herdBook;
    /**
     * Displays a menu, asks for and reads a menu choice
     * This Method is complete - DO NOT CHANGE
     * @return an integer between 1 and 4 inclusive
     */
    public int getMenuChoice(){
        Scanner stdIn = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.print("*************************\n"
                           + "* 1. Print Pedigree     *\n"
                           + "* 2. Count Offspring    *\n"
                           + "* 3. Evaluate Offspring *\n"
                           + "* 4. Quit.              *\n"
                           + "*************************\n"
                           + "  Enter Choice --> ");
            if (stdIn.hasNextInt())
                choice = stdIn.nextInt();
            else
                stdIn.next(); // read and throw away
        }while(choice < 1 || choice > 4);
        
        return choice;
    }
    /**
     *         Make no changes.
     */
    public void printPedigree(String regNum){
        System.out.println(herdBook.printPedigree(regNum));       
    }

    /**
     *         Make no changes.
     */
    public void countOffspring(String regNum){
        System.out.println(herdBook.countOffspring(regNum));
    }

    /**
     *             Make no changes.
     */
    public void evaluateOffspring(String regNum){
        System.out.println(herdBook.evaluateOffspring(regNum));        
    }
    /**
     * The actual driver for the application
     * This method is DONE - DO NOT CHANGE THIS
     * @param fileName comes from the command line arguments
     */
    public void run(String fileName){
        boolean finished = false;
        // create an instance of the Database
        herdBook = new Database(fileName);
        
        Scanner stdIn = new Scanner(System.in);
        String regNum;
        while (!finished) {
            int choice = getMenuChoice();
            switch(choice){
                case 1: 
                    System.out.print("Enter the registration number for "
                           + "the animal\nthat you want the pedigree for: ");
                    regNum = stdIn.next();
                    printPedigree(regNum);     
                    break;
                case 2: 
                    System.out.print("Enter the registration number for "
                           + "the animal\nthat you want the count for: ");
                    regNum = stdIn.next();
                    countOffspring(regNum);    
                break;
                case 3: 
                    System.out.print("Enter the registration number for "
                           + "the animal\nthat you want the evaluation for: ");
                    regNum = stdIn.next();
                    evaluateOffspring(regNum); 
                break;
                case 4: finished = true;
            }                
        }//end while  
    } // end run
    /**
     * This method is DONE DO NOT CHANGE THIS
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: prog datafile");
            return;
        }
        // fileName from the command line arguments
        String fileName = args[0];        
        BullsAndCows driver = new BullsAndCows();
        driver.run(fileName);
    }
}
