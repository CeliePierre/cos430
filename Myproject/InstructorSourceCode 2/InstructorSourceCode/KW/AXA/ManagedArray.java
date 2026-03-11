/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KW.AXA;

/**
 *
 * @author Paul Wolfgang
 */
public class ManagedArray {
    
    private int[] x;
    private static final int MIN_VAL = 0;
    private static final int MAX_VAL = 1000;
    
    public ManagedArray(int n) {
        x = new int[n];
    }
    
    /*<exercise chapter="A" section="12" type="programming" number="2">*/
    /**
     * Method to set an element of x
     * @param index The index
     * @param val The value
     * @throws ArrayIndexOutOfBoundsException if index is not valid
     * @throws IllegalArgumentException if val is not in range
     */
    public void setElementOfX(int index, int val) {
        if (val < MIN_VAL || val > MAX_VAL)
            throw new IllegalArgumentException(val + " is not between " 
                    + MIN_VAL + " and " + MAX_VAL);
        try {
            x[index] = val;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(index 
                    + " is not between 0 and " + x.length);
        }
    }
    /*</exercise>*/
    
    public static void main(String[] args) {
        ManagedArray a = new ManagedArray(10);
        /*<exercise chapter="A" section="12" type="programming" number="2">*/
        try {
            a.setElementOfX(5, 10);
        } catch (ArrayIndexOutOfBoundsException aobex) {
            System.err.println(aobex.getMessage());
        } catch (IllegalArgumentException iaex) {
            System.err.println(iaex.getMessage());
        }
        /*</exercise>*/
    }

}
