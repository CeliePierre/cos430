/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KW.AXA;

import javax.swing.JOptionPane;

/**
 *
 * @author Paul Wolfgang
 */
public class ExerciseA_9_1 {

    /*<exercise chapter="A" section="9" type="programming" number="1">*/
    public static Person readPerson() {
        String familyName;
        String givenName;
        String IDNumber;
        int birthYear;

        familyName = JOptionPane.showInputDialog("Enter family name");
        givenName = JOptionPane.showInputDialog("Enter given name");
        IDNumber = JOptionPane.showInputDialog("Enter the ID number");
        String birthYearString = JOptionPane.showInputDialog("Enter birth year");
        birthYear = Integer.parseInt(birthYearString);
        return new Person(familyName, givenName, IDNumber, birthYear);
    }

    public static void main(String[] args) {
        Person person1 = readPerson();
        Person person2 = readPerson();
        if (person1.equals(person2)) {
            System.out.println(person1 + " is the same as " + person2);
        } else {
            System.out.println(person1 + " is not the same as " + person2);
        }
    }
    /*</exercise>*/

}
