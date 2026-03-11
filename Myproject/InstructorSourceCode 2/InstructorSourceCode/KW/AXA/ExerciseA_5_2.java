/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KW.AXA;

/**
 *
 * @author Paul Wolfgang
 */
public class ExerciseA_5_2 {

    public static void main(String[] args) {
        String s = "Nancy* has thrity-three*** fine!! teath.";
        /*<exercise chapter="A" section="5" type="programming" number="2">*/
        String[] tokens = s.split("[* !\\.]+");
        StringBuilder stb = new StringBuilder();
        stb.append(tokens[0]);
        for (int i = 1; i < tokens.length; i++) {
            stb.append(", ");
            stb.append(tokens[i]);
        }
        /*</exercise>*/
        System.out.println(stb.toString());

    }
}
