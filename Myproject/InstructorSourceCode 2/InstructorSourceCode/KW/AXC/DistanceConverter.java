/*<exercise chapter="C" section="5" type="programming" number="1">*/
package KW.AXC;

/** DistanceConverter is a class with static methods
 *  that convert between miles and kilometers.
 *  @author Koffman & Wolfgang
 */
public class DistanceConverter {

    /** The number of kilometers in a mile.
     */
    private static final double KILOMETERS_PER_MILE = 1.609344;

    /** Convert a value in kilometers to miles.
     *  @param kilometers The value in kilometers
     *  @return The value in miles
     */
    public static double toMiles(double kilometers) {
        return kilometers / KILOMETERS_PER_MILE;
    }

    /** Convert a value in miles to kilometers.
     *  @param miles The value in miles
     *  @return The value in kilometers
     */
    public static double toKilometers(double miles) {
        return miles * KILOMETERS_PER_MILE;
    }
}
/*</exercise>*/