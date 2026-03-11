/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KW.AXA;

import java.util.Arrays;

/**
 *
 * @author Paul Wolfgang
 */
public class ExerciseA_8_1 {

    /*<exercise chapter="A" section="8" type="programming" number="1">*/
    /**
     * Method to determine if two arrays contain the same elements with the
     * same multiplicites.
     * @param a The first array
     * @param b The second array
     * @return true if b contains the same elements as a
     */
    public static boolean sameElements(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (countOccurances(a, a[i]) != countOccurances(b, a[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to count the number of times a integer occurs in an array
     * @param x The array
     * @param t The targer
     * @return The number of times t occurs in x
     */
    public static int countOccurances(int x[], int t) {
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] == t) {
                count++;
            }
        }
        return count;
    }
    /*</exercise>*/

    public static void main(String[] args) {
        int[] a = {121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = {11, 121, 144, 19, 161, 19, 144, 19};
        if (sameElements(a, b)) {
            System.out.println("Test 1 passed");
        } else {
            System.out.println("Test 1 failed");
        }
        int[] c = {11, 121, 144, 18, 161, 19, 144, 19};
        if (sameElements(a, c)) {
            System.out.println("Test 2 failed");
        } else {
            System.out.println("Test 2 passed");
        }

    }
}
