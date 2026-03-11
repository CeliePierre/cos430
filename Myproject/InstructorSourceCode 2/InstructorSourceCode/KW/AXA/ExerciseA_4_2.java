/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KW.AXA;

/**
 *
 * @author Paul Wolfgang
 */
public class ExerciseA_4_2 {

    /*<exercise chapter="A" section="4" type="programming" number="2">*/
    public static void main(String[] args) {
        System.out.println("n\tlog(n)");
        long n = 1000;
        for (int i = 0; i < 20; i++) {
            System.out.println(n + "\t" + Math.log(n));
            n *= 2;
        }
    }
    /*</exercise>*/

}
