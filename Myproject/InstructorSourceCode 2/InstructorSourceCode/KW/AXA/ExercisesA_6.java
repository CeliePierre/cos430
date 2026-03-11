/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KW.AXA;

/**
 *
 * @author Paul Wolfgang
 */
public class ExercisesA_6 {

    public static void main(String[] args) {

        Integer i1 = new Integer(10);
        Integer i2 = new Integer(20);
        Integer i3 = new Integer(30);
        Integer i4 = null;

        /*<exercise chapter="A" section="6" type="programming" number="1">*/
        i1 = i1 * 2;
        /*</exercise>*/

        /*<exercise chapter="A" section="6" type="programming" number="2">*/
        // With autoboxing
        i4 = i1 * i2 * i3;
        // Without autoboxing
        i4 = new Integer(i1.intValue() * i2.intValue() * i3.intValue());
        /*</exercise>*/

        System.out.println("i1 " + i1);
        System.out.println("i2 " + i2);
        System.out.println("i3 " + i3);
        System.out.println("i4 " + i4);
    }

}
