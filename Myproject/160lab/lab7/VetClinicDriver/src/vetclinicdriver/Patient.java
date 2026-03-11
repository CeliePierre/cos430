
package vetclinicdriver;

/**
 *
 * @author kalalajoel
 */
public class  Patient implements Comparable {
    private String patientNumber;
    private Dog patient;

    public Patient(String patientNumber, Dog patient) {
        this.patientNumber = patientNumber;
        this.patient = patient;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public Dog getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return patientNumber + " " + patient.toString();
    }

    @Override
    public int compareTo(Object that) {
        
         int comparison = 0;
         if(that == null){
         comparison = 1;
     } else if (this == that) {
    comparison = 0;
} else {
    comparison = this.patientNumber.
            compareTo(((Patient)that).getPatientNumber());
     
    
     }
    return comparison;
    
     }
}

      
         
        