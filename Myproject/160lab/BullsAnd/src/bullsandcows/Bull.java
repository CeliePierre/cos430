
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
    private String gender; // Store gender as a String internally
    private int month;
    private int day;
    private int year;
    private ClassificationScore classificationScore;
    private Proving proving;
    
    /**
     * Constructs a new Bull instance.
     * Gender is accepted as a char and converted to a String.
     */
    public Bull(String registrationNumber, String sireRegNum, String damRegNum, char gender, int month, int day, int year, String name, ClassificationScore classificationScore,
            Proving proving) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.sireRegNum = sireRegNum;
        this.damRegNum = damRegNum;
        this.gender = String.valueOf(gender); // Convert char to String
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