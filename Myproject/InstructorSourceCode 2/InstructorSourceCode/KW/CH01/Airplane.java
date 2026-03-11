package KW.CH01;

/**
 * Class Airplane example for Exercise 1.5.3
 */
public class Airplane {

    private Engine eng;
    private Rudder rud;
    private Wing[] wings = new Wing[2];

    /*<exercise chapter="1" section="5" type="programming" number="3">*/
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj.getClass() == this.getClass()) {
            Airplane other = (Airplane) obj;

            return eng.equals(other.eng) && rud.equals(other.rud)
                    && wings[0].equals(other.wings[0])
                    && wings[1].equals(other.wings[1]);
        } else {
            return false;
        }
    }
    // equals methods need to be defined for classes Engine, Rudder, and Wing
    /*</exercise>*/
}

// Dummy classes to allow exercise to compile
class Engine {
}

class Rudder {
}

class Wing {
}
