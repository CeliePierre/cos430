/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KW.AXC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul Wolfgang
 */
public class ExerciseC_1 {

    private static JFrame frame;

    public static void main(String[] args) {

        frame = new JFrame("Exercise C.1.1 and C.1.2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*<exercise chapter="C" section="1" type="programming" number="1">*/
        JButton submit = new JButton("Submit");
        submit.addActionListener(new DoSubmit());
        /*</exercise>*/
        frame.add(submit);
        frame.pack();
        frame.setVisible(true);
    }

    /*<exercise chapter="C" section="1" type="programming" number="2">*/
    private static class DoSubmit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Thanks for the submission");
        }
    }
    /*</exercise>*/


}
