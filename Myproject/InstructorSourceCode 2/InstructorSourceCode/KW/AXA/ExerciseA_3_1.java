/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KW.AXA;

/**
 *
 * @author Paul Wolfgang
 */
public class ExerciseA_3_1 {

    public static void main(String[] args) {
        int sum = 0;
        int prod = 1;
        int maxValue = 10;
        /*<exercise chapter="A" section="3" type="programming" number="1">*/
        int nextInt = 1;
        do {
            if (nextInt % 2 == 0) {
                sum += nextInt;
            } else {
                prod *= nextInt;
            }
            nextInt++;
        } while (nextInt <= maxValue + 1);
        /*</exercise>*/
    }
}
