/********************************************************
 * Revision History
 ********************************************************
 * 2014 - AA - skeleton written to prevent errors in the 
 *             driver.
 ********************************************************/
package bullsandcows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;




/**
 * Database of cows.  Please complete the documentation 
 * @author 
 */
public class Database {
    
    // Declare a Map here do not create it.
      private Map<String, Animal> herd;
 
    
    /** 
     * Reads the input file and creates either a Bull or Cow
     * object and adds it to the Database
     * NOTE:
     * During development, we print out the full message of
     * the exception by using e.toString() and e.printStackTrace().
     * That is not recommended for production programs, since it 
     * gives hackers too much insight into the innards of the 
     * software, possibly allowing them to exploit it.Instead, 
     * production programs write error messages to internal 
     * log files that hackers wouldn't see.                 
     * @param fileName a String for the file that we want to 
     *                 use.
     */
    public Database(String fileName){ 
        // create your Map here
           herd = new HashMap<String, Animal>();
        try {
            Scanner inputFile = new Scanner(new File(fileName));
            // TODO  put the file reading logic here - look back at baseball!
                 while (inputFile.hasNext()) {
                String regNum = inputFile.next();
                String sireRegNum = inputFile.next();
                String damRegNum = inputFile.next();
                 char gender = inputFile.next().charAt(0);
                int month = inputFile.nextInt();
                int day = inputFile.nextInt();
                int year = inputFile.nextInt();
                String name = inputFile.next();
                
               
                //ClassificationScore classificationScore = new ClassificationScore(month, day, year);
              Animal animal = new Animal(regNum, name, sireRegNum, damRegNum, gender, month, day, year, null);
            herd.put(regNum, animal);
                 if (gender == 'm') {
                    int numOffspring = inputFile.nextInt();
                    if (numOffspring != -1) {
                        for (int i = 0; i < numOffspring; i++) {
                            int offspringMonth = inputFile.nextInt();
                            int offspringDay = inputFile.nextInt();
                            int offspringYear = inputFile.nextInt();
                            int offspringWeight = inputFile.nextInt();
                            double offspringHeight = inputFile.nextDouble();
                            animal.addOffspring(offspringMonth, offspringDay, offspringYear, offspringWeight, offspringHeight);
                        }
                    }
                } else {
                    int numCalves = inputFile.nextInt();
                    if (numCalves != -1) {
                        for (int i = 0; i < numCalves; i++) {
                            int calfMonth = inputFile.nextInt();
                            int calfDay = inputFile.nextInt();
                            int calfYear = inputFile.nextInt();
                            int calfWeight = inputFile.nextInt();
                            double calfHeight = inputFile.nextDouble();
                            animal.addCalf(calfMonth, calfDay, calfYear, calfWeight, calfHeight);
                        }
                    }
                 }
                 }
                
            
            // comment the next line out when you start on part 5!
            
            System.out.println(printHerd());
            inputFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found.");
            ex.printStackTrace();
            System.exit(1);
        } catch (InputMismatchException ex) {
            System.err.println("tried to read the wrong data type.");
            ex.printStackTrace();
            System.exit(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }       
    }
    /**
     * This method is for debugging the specifications for a B. 
     * You should iterate through your map to generate the output String
     * Look at BookStoreMap for an example of iterating over a map.  
     * @return a string that is the output of the entire herd if the 
     *         herd is less that 14 (use with cattle10) for testing.
     */
    public String printHerd(){
         StringBuilder str = new StringBuilder();
        // print only 10 elements from the map
        int count = 0;
        for (String key : herd.keySet()) {
            if (count < 10) {
                str.append(herd.get(key).toString()).append("\n");
                count++;
            }
        }
        return str.toString();
    }
    
    
    /**
     * Implement this plus countOffspring() and evaluateOffSpring()
     * for an A
     * Method to create a string that represents 2 generations of an animal's
     * ancestry.  If the Animal exists in the database, it is printed.  If
     * its dam is present it is printed as are the dam's parents if they 
     * exist.  If its sire is present it is printed as are the sire's 
     * parents if they exist.
     * @param regNum the registration number of the animal to evaluate
     * @return a two generation pedigree of the input animal if it exists
     *      or a string that says it does not exist.
     */
    public String printPedigree(String regNum){
        
               StringBuilder str = new StringBuilder();
        if (herd.containsKey(regNum)) {
            Animal animal = herd.get(regNum);
            str.append(animal.toString()).append("\n");
            if (herd.containsKey(animal.getSireRegNum())) {
                Animal sire = herd.get(animal.getSireRegNum());
                str.append(sire.toString()).append("\n");
                if (herd.containsKey(sire.getSireRegNum())) {
                    Animal sireSire = herd.get(sire.getSireRegNum());
                    str.append(sireSire.toString()).append("\n");
                }
                if (herd.containsKey(sire.getDamRegNum())) {
                    Animal sireDam = herd.get(sire.getDamRegNum());
                    str.append(sireDam.toString()).append("\n");
                }
            }
            if (herd.containsKey(animal.getDamRegNum())) {
                Animal dam = herd.get(animal.getDamRegNum());
                str.append(dam.toString()).append("\n");
                if (herd.containsKey(dam.getSireRegNum())) {
                    Animal damSire = herd.get(dam.getSireRegNum());
                    str.append(damSire.toString()).append("\n");
                }
                if (herd.containsKey(dam.getDamRegNum())) {
                    Animal damDam = herd.get(dam.getDamRegNum());
                    str.append(damDam.toString()).append("\n");
                }
            }
        } else {
            str.append("Animal does not exist.");
        }
        return str.toString();
    }

    /**
     * Implement this plus printPedigree() and evaluateOffSpring()
     * for an A
     * if you implement this, then change these comments and complete
     * the JavaDoc otherwise leave it ALL alone
     * @param regNum the registration number of the animal to evaluate
     * @return 
     */
    public String countOffspring(String regNum){
     StringBuilder str = new StringBuilder();
        if (herd.containsKey(regNum)) {
            int maleCount = 0;
            int femaleCount = 0;
            for (String key : herd.keySet()) {
                Animal animal = herd.get(key);
                if (animal.getSireRegNum().equals(regNum)) {
                    if (animal.getGender() == 'm') {
                        maleCount++;
                    } else {
                        femaleCount++;
                    }
                }
                if (animal.getDamRegNum().equals(regNum)) {
                    if (animal.getGender() == 'm') {
                        maleCount++;
                    } else {
                        femaleCount++;
                    }
                }
            }
            str.append("Number of male offspring: ").append(maleCount).append("\n");
            str.append("Number of female offspring: ").append(femaleCount).append("\n");
        } else {
            str.append("Animal does not exist.");
        }
        return str.toString();
    }
    
    /**
     * Implement this plus printPedigree() and countOffspring() 
     * for an A
     * if you implement this, then change these comments and complete
     * the JavaDoc otherwise leave it ALL alone
     * @param regNum the registration number of the animal to evaluate
     * @return 
     */
    public String evaluateOffspring(String regNum){
        StringBuilder str = new StringBuilder();
        if (herd.containsKey(regNum)) {
            int maleCount = 0;
            int femaleCount = 0;
            int maleScore = 0;
            int femaleScore = 0;
            for (String key : herd.keySet()) {
                Animal animal = herd.get(key);
                if (animal.getSireRegNum().equals(regNum)) {
                    if (animal.getGender() == 'm') {
                        maleCount++;
                       femaleScore += animal.getClassificationScore().getScoreMonth() + animal.getClassificationScore().getScoreDay() + animal.getClassificationScore().getScoreYear();
                    } else {
                        femaleCount++;
                        femaleScore += animal.getClassificationScore().getScoreMonth() + animal.getClassificationScore().getScoreDay() + animal.getClassificationScore().getScoreYear();
                    }
                }
                if (animal.getDamRegNum().equals(regNum)) {
                    if (animal.getGender() == 'm') {
                        maleCount++;
                        maleScore += animal.getClassificationScore().getScoreMonth() + animal.getClassificationScore().getScoreDay() + animal.getClassificationScore().getScoreYear();
                    } else {
                        femaleCount++;
                        femaleScore += animal.getClassificationScore().getScoreMonth() + animal.getClassificationScore().getScoreDay() + animal.getClassificationScore().getScoreYear();
                    }
                }
            }
            str.append("Number of male offspring: ").append(maleCount).append("\n");
            str.append("Number of female offspring: ").append(femaleCount).append("\n");
            str.append("Average male score: ").append(maleScore/maleCount).append("\n");
            str.append("Average female score: ").append(femaleScore/femaleCount).append("\n");
        } else {
            str.append("Animal does not exist.");
        }
        return str.toString();
       
    }
}
 