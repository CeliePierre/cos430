package agilitycompetition;

/**
 *
 * @author kalalajoel
 */
public class Competitor extends Pet {

    private final ElapsedTime time;

    public Competitor(Date dob, String breed, double weight,
            String name, String owner, ElapsedTime time) {

        super(dob, breed, weight, name, owner);

        this.time = time;
    }

    /**
     * Accessor for the course time
     *
     * @return the value of course time
     */
    public ElapsedTime getTime() {
        return time;
    }
}
