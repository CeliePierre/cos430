
package vetclinicdriver;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kalalajoel
 */
public class Clinic {
    private ArrayList<Patient> patients;

public Clinic(){
        patients = new ArrayList<>();
        
    }


    public void addPatient(Patient p){
        
        patients.add(p);
        
    }
    public void sortPatients(){
       Collections.sort(patients);
        
    }
    @Override
    
    public String toString(){
        StringBuilder str = new StringBuilder();
        String eol = System.lineSeparator();
        for(Patient p: patients){
            str.append(p.toString()).append(eol);
        }
        return str.toString();
    }
    }

    
    