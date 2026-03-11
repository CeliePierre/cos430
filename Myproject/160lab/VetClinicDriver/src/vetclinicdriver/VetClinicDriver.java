/*
 *              Revision History
 * ***************************************************************
 * 
 */
package vetclinicdriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author aapplin
 * revised by: 
 */
public class VetClinicDriver {
    Clinic clinic = new Clinic();
    
    /**
     * Reads the data file and store it into a clinic object
     * @param fileName the filename from the command line arguments
     */
    public void readFile(String fileName) {
    try {
        Scanner inFile = new Scanner(new FileReader(fileName));
        if (!inFile.hasNext()) {
            System.err.println("The file is empty.");
            System.exit(2);
        }
        Patient patient;
        while (inFile.hasNext()) {
            String patientNum = inFile.next();
            int month = inFile.nextInt();
            int day = inFile.nextInt();
            int year = inFile.nextInt();
            Date dob = new Date(month, day, year);
            String breed = inFile.nextLine();
            double weight = inFile.nextDouble();
            String name = inFile.next();
            if (patientNum.compareTo("70000") < 0) {
                String owner = inFile.next();
                patient = new Patient(patientNum,
                        new Pet(dob, breed, weight, name, owner));
            } else {
                patient = new Patient(patientNum,
                        new ShelterDog(dob, breed, weight, name));
            }
            clinic.addPatient(patient);
        }
        inFile.close();
        clinic.sortPatients();
    } catch (FileNotFoundException ex) {
        System.out.println("File " + fileName + " not found.");
        System.exit(3);
    }
}
    
    /**
     * The actual driver for this application
     * @param fileName the filename from the command line arguments
     */
    public void run(String fileName) {
        // enroll the competitors
        readFile(fileName);
        System.out.println(clinic.toString());
    }
    /**
     * main method.  The ONLY static method in a class!!!
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // check for a data file name
        if (args.length < 1) {
            System.err.println("usage: progname inputFile");
            System.exit(1);
        }
        VetClinicDriver driver = new VetClinicDriver();
        driver.run(args[0]);
    }
    
}

     
          