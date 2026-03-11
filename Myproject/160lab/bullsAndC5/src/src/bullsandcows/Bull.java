
package bullsandcows;

/**
 *
 * @author kalalajoel
 */
public class Bull {
    private String registrationNumber;
    private String name;
    private String sireRegNum;
    private String damRegNum;
    private char gender;
    private int month;
    private int day;
    private int year;
    private ClassificationScore classificationScore;
    private Proving proving;
    
    public Bull(String registrationNumber, String name, String sireRegNum, String damRegNum, char gender, int month, int day, int year, ClassificationScore classificationScore, Proving proving) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.sireRegNum = sireRegNum;
        this.damRegNum = damRegNum;
        this.gender = gender;
        this.month = month;
        this.day = day;
        this.year = year;
        this.classificationScore = classificationScore;
        this.proving = proving;
    }
     @Override
    public String toString() {
        return "Bull #registrationNumber : " + registrationNumber + " #name: " + name + " #sireRegNum: " 
       + sireRegNum + " #damRegNum: " + damRegNum + " # gender : " + gender + " # month: " + month + " #day : " 
       + day + " #year : " + year + " # classificationScore : " + classificationScore + " #proving : " + proving;
    }
}