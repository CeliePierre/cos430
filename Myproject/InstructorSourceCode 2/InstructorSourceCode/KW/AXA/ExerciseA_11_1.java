/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KW.AXA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Paul Wolfgang
 */
public class ExerciseA_11_1 {

    public static void main(String[] args) {
        int total = 1000;
        int num = 0;
        int average = 0;
        String numStr = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            numStr = in.readLine();
            num = Integer.parseInt(numStr);
            average = total / num;
            /*<exercise chapter="A" section="11" type="programming" number="1">*/
        } catch (IOException ioex) {
            System.err.println("IO Exception");
            ioex.printStackTrace();
            System.exit(1);
        } catch (NumberFormatException nfex) {
            System.err.println("Number Format Exception");
            nfex.printStackTrace();
            System.exit(1);
        } catch (ArithmeticException arex) {
            average = 0;
            System.err.println("Arithmetic exception");
            arex.printStackTrace();
            System.exit(1);
        } finally {
            System.out.println("That's all folks");
        }
        /*</exercise>*/
    }
}
