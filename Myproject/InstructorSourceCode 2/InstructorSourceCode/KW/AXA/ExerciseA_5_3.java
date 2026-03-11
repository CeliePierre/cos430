/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KW.AXA;

/**
 *
 * @author Paul Wolfgang
 */
public class ExerciseA_5_3 {

    public static void main(String[] args) {
        String sentence = "Let's all learn how to program in Java";
        String[] tokens = sentence.split("\\s+");
        /*<exercise chapter="A" section="5" type="programming" number="3">*/
        for (String token : tokens) {
            System.out.println(token);
        }
        /*</exercise>*/
    }

}
