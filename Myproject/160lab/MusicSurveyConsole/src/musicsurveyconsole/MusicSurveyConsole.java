/**
 *                        Revision History
 *  ==========================================================================
 *  2022 rewritten in Java for the GUI lab as an experiment! Anne Applin 
 *  2003 Originally written at Ithaca College for comp171 by Sharon Stansfield. 
 */
package musicsurveyconsole;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Anne
 */
public class MusicSurveyConsole {
    private Survey survey;
    private static String[] musicTypes = {"Classical", "Rap", "Latin", "Jazz",
                               "Rock", "Disco", "Techno", "Swing"};

    /**
     * Reads the file of student names and id numbers
     * @param fileName from the args list
     */
    public void readFile(String fileName) {
        try {
            Scanner inFile = new Scanner(new FileReader(fileName));
            if (!inFile.hasNext()) {
                System.out.println("The file is empty.");
                System.exit(1);
            }
            // each line has 3 strings
            while (inFile.hasNext()) {
                String first = inFile.next();
                String last = inFile.next();
                String id = inFile.next();
                survey.addStudent(new Student(first, last, id));                
            }
            System.out.println("Number of registered voters: " 
                    + survey.getSize());
            inFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println("The file " + fileName + " does not exist");
            System.exit(1);
        } catch (InputMismatchException ex) {
            System.err.println("Attempt to read the wrong data type.");
            System.exit(1);
        }

    }

    /**
     *  Displays the menu for the music types.
     */
    public void displayMusicTypes(){
        System.out.print(  "##################\n" + 
                           "#  Choose one:   #\n" +
                           "##################\n" +
                           "# 0. Classical   #\n" +
                           "# 1. Rap         #\n" + 
                           "# 2. Latin       #\n" + 
                           "# 3. Jazz        #\n" + 
                           "# 4. Rock        #\n" + 
                           "# 5. Disco       #\n" + 
                           "# 6. Techno      #\n" + 
                           "# 7. Swing       #\n" +
                           "##################\n" +
                           "   ===>  ");
    }

    /**
     * method that runs the program.
     * @param fileName from the args list
     */
    public void run(String fileName){       
        survey = new Survey("SMCC", "South Portland", 
                new Date(System.currentTimeMillis()) );
        readFile(fileName);
        Scanner kb = new Scanner(System.in);
        boolean done;
        System.out.println("Are you ready to vote? (y/n) ");
        done = kb.next().toLowerCase().charAt(0) == 'n';
        while (!done){
            System.out.println("Please enter your Student id number.");
            String id = kb.next();
            String valid = survey.validateVoter(id);
            System.out.println(valid);
            if (valid.contains("Hello")){
                displayMusicTypes();
                int vote = kb.nextInt();
                survey.processCurrentVoter(vote, id);            
            }
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");  //clear the menu
            System.out.println("Are you ready to vote? (y/n) ");
            done = kb.next().toLowerCase().charAt(0) == 'n';
  
        }
        System.out.println("The number of votes cast was " + 
                survey.getNumberVoting() + 
                " out of a student body of " + survey.getSize() +
                ".");
        System.out.println("The winning music type is " +
                musicTypes[survey.determineSurveyResults()]);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("usage: prog inputFile");
            System.exit(1);
        }
        MusicSurveyConsole msc = new MusicSurveyConsole();
        msc.run(args[0]);
    }
    
}
