/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KW.AXA;

/**
 *
 * @author Paul Wolfgang
 */
public class ExerciseA_5_1 {

    public static void main(String[] args) {
        String s = "Doe, John 5/15/65";
        /*<exercise chapter="A" section="5" type="programming" number="1">*/
        int posComma = s.indexOf(", ");
        int posSpace = s.indexOf(' ', posComma + 2);
        int posSlash1 = s.indexOf('/', posSpace + 1);
        int posSlash2 = s.indexOf('/', posSlash1 + 1);
        String token1 = s.substring(0, posComma);
        String token2 = s.substring(posComma + 2, posSpace);
        String token3 = s.substring(posSpace + 1, posSlash1);
        String token4 = s.substring(posSlash1 + 1, posSlash2);
        String token5 = s.substring(posSlash2 + 1);
        /*</exercise>*/
        System.out.println(token1);
        System.out.println(token2);
        System.out.println(token3);
        System.out.println(token4);
        System.out.println(token5);
    }

}
