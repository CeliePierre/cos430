/** ******************************************************
 * Revision History
 ********************************************************
 * 2014 - AA - skeleton written to prevent errors in the
 *             driver.
 ******************************************************* */
package bullsandcows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import bullsandcows.Animal;

/**
 * Database of cows. Please complete the documentation
 *
 * @author
 */
public class Database {

    // Declare a Map here do not create it.
    private Map<String, Animal> herd;

    /**
     * Reads the input file and creates either a Bull or Cow object and adds it
     * to the Database NOTE: During development, we print out the full message
     * of the exception by using e.toString() and e.printStackTrace(). That is
     * not recommended for production programs, since it gives hackers too much
     * insight into the innards of the software, possibly allowing them to
     * exploit it.Instead, production programs write error messages to internal
     * log files that hackers wouldn't see.
     *
     * @param fileName a String for the file that we want to use.
     */
    public Database(String fileName) {
        herd = new HashMap<>();
        try {
            Scanner inputFile = new Scanner(new File(fileName));
            while (inputFile.hasNext()) {
                String regNum = inputFile.next();
                String sireRegNum = inputFile.next();
                String damRegNum = inputFile.next();
                String genderString = inputFile.next(); // Read gender as a String
                char gender = genderString.charAt(0); // Convert the String to a char
                int month = inputFile.nextInt();
                int day = inputFile.nextInt();
                int year = inputFile.nextInt();
                String name = inputFile.nextLine().trim(); // Assuming name is the last part of the line

                // Assuming the classification score is not part of the input file
                Animal animal;
                if (gender == 'm' || gender == 'M') {
                    Bull bull = new Bull(regNum, sireRegNum, damRegNum, gender, month, day, year, name, null, null); // Assuming null for missing Bull-specific fields
                } else {
                    Cow cow = new Cow(regNum, sireRegNum, damRegNum, gender, month, day, year, name, null, null); // Assuming null for missing Cow-specific fields
                }
                Animal Animal = null;
                herd.put(regNum, Animal);

                // Skipping the offspring reading part since we can't add offspring
                int numOffspring = 0;
                if (inputFile.hasNextInt()) {
                    numOffspring = inputFile.nextInt();
                } else {
                    System.out.println("Expected an integer for the number of offspring but found something else.");
                    // Handle the error or skip to the next line
                    inputFile.nextLine();
                }

            }

            System.out.println(printHerd());
            inputFile.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Input mismatch: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // ... Other methods remain unchanged ...
    /**
     * This method is for debugging the specifications for a B. You should
     * iterate through your map to generate the output String Look at
     * BookStoreMap for an example of iterating over a map.
     *
     * @return a string that is the output of the entire herd if the herd is
     * less that 14 (use with cattle10) for testing.
     */
    public String printHerd() {
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
     * Implement this plus countOffspring() and evaluateOffSpring() for an A
     * Method to create a string that represents 2 generations of an animal's
     * ancestry. If the Animal exists in the database, it is printed. If its dam
     * is present it is printed as are the dam's parents if they exist. If its
     * sire is present it is printed as are the sire's parents if they exist.
     *
     * @param regNum the registration number of the animal to evaluate
     * @return a two generation pedigree of the input animal if it exists or a
     * string that says it does not exist.
     */
    public String printPedigree(String regNum) {

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
     * Implement this plus printPedigree() and evaluateOffSpring() for an A if
     * you implement this, then change these comments and complete the JavaDoc
     * otherwise leave it ALL alone
     *
     * @param regNum the registration number of the animal to evaluate
     * @return
     */
    public String countOffspring(String regNum) {
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
     * Implement this plus printPedigree() and countOffspring() for an A if you
     * implement this, then change these comments and complete the JavaDoc
     * otherwise leave it ALL alone
     *
     * @param regNum the registration number of the animal to evaluate
     * @return
     */
    public String evaluateOffspring(String regNum) {
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
            str.append("Average male score: ").append(maleScore / maleCount).append("\n");
            str.append("Average female score: ").append(femaleScore / femaleCount).append("\n");
        } else {
            str.append("Animal does not exist.");
        }
        return str.toString();

    }

}