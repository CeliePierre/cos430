/*<exercise chapter="2" section="11" type="programming" number="2">*/
package KW.CH02;

/**
 * Stub for my input
 * Stubs always return returnValue
 */
public class MyInputStubs {

    private static int returnValue;

    public static void setReturnValue(int rv) {
        returnValue = rv;
    }

    /**
     * Method to return an integer data value.
     * @param prompt Messsage
     * @return returnValue since this is a stub
     */
    public static int readInt(String prompt) {
        return returnValue;
    }

    /**
     * Method to return an integer data value between two
     * specified end points.
     * @pre minN <= maxN.
     * @param prompt Message
     * @param minN Smallest value in range
     * @param maxN Largest value in range
     * @throws IllegalArgumentException
     * @return returnValue since this is a stub
     */
    public static int readInt(String prompt, int minN, int maxN) {
        return returnValue;
    }
}
/*</exercise>*/
