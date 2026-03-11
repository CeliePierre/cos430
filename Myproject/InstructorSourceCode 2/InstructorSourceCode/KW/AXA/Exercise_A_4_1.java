/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KW.AXA;

/**
 *
 * @author Paul Wolfgang
 */
public class Exercise_A_4_1 {

    /*<exercise chapter="A" section="4" type="programming" number="1"*/
    public static void main(String[] args) {
        System.out.println("n \tpower of 2");
        for (int i = 1; i < 30; i += 2) {
            System.out.println(i + "\t" + Math.pow(2, i));
        }
    }
    /*</exercise>*/
}
