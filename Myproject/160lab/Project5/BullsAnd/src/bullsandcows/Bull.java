
package bullsandcows;

/**
 *
 * @author kalalajoel
 */
public class Bull extends Animal {
    private Proving proving;

    public Bull(String registrationNumber, String sireRegNum, String damRegNum, 
                char gender, int month, int day, int year, String name, 
                ClassificationScore classificationScore, Proving proving) {
        super(registrationNumber, sireRegNum, damRegNum, gender, 
              month, day, year, name, classificationScore);
        this.proving = proving;
    }

    // Getter for proving
    public Proving getProving() {
        return proving;
    }

    // Setter for proving
    public void setProving(Proving proving) {
        this.proving = proving;
    }

    @Override
    public String toString() {
        return "Bull: " + super.toString() + ", Proving: " + proving;
    }

    // Additional Bull-specific methods
}