
package bullsandcows;
import java.util.ArrayList;
/**
 *
 * @author kalalajoel
 */
   public class Cow extends Animal {
    // Additional fields specific to Cow
    private ArrayList<LoctationRecord> lacRecs;
    private String registrationNumber;
    private String name;
    private String sireRegNum;
    private String damRegNum;
    private char gender;
    private int month;
    private int day;
    private int year;
    private ClassificationScore classificationScore;
   // private ArrayList<LoctationRecord> lacRecs;

    
    
       public Cow(String registrationNumber, String sireRegNum, String damRegNum, char gender, int month, int day, int year, String name, ClassificationScore classificationScore, ArrayList<LoctationRecord> lacRecs) {
    super(registrationNumber, sireRegNum, damRegNum, gender, month, day, year, name, classificationScore);
    this.lacRecs = lacRecs;
}

    @Override
    public String toString() {
        return "Cow #registrationNumber: " + registrationNumber + " #name: " + name + " #sireRegNum: " 
       + sireRegNum + " #damRegNum: " + damRegNum + " #gender: " + gender + " #month: " + month + " #day: " 
       + day + " #year: " + year + " #classificationScore: " + classificationScore + " #lacRecs: " + lacRecs;
    }
    
    

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getName() {
        return name;
    }

    public String getSireRegNum() {
        return sireRegNum;
    }

    public String getDamRegNum() {
        return damRegNum;
    }

    public char getGender() {
        return gender;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public ClassificationScore getClassificationScore() {
        return classificationScore;
    }

    public ArrayList<LoctationRecord> getLacRecs() {
        return lacRecs;
    }
}
    
