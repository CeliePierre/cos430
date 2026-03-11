/*<exercise chapter="C" section="5" type="programming" number="1">*/
package KW.AXC;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/** VolumeConverterGUI is a GUI application that converts
 *  values in miles to kilometers and vice versa.
 *  @author Koffman & Wolfgang
 */
public class DistanceConverterGUI
        extends JFrame {

    // Data Fields
    /** Text field to hold miles value */
    private JTextField milesField;
    /** Text field to hold kilometers value */
    private JTextField kilometersField;

    // Main Method
    /** Create an instance of the application and show it.
     *  @param args Command Line Arguments � not used
     */
    public static void main(String[] args) {
        JFrame frame = new DistanceConverterGUI();
        frame.setVisible(true);
    }

    // Constructor
    /** Construct the components and add them to the frame. */
    public DistanceConverterGUI() {
        super("Distance Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Get a reference to the content pane.
        Container contentPane = getContentPane();
        // Set the layout manager to grid layout.
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new JLabel("miles"));
        milesField = new JTextField(15);
        milesField.addActionListener(new NewMilesValue());
        contentPane.add(milesField);
        contentPane.add(new JLabel("kilometers"));
        kilometersField = new JTextField(15);
        kilometersField.addActionListener(new NewKilometersValue());
        contentPane.add(kilometersField);
        // Size the frame to fit.
        pack();
    }

    // Inner Classes
    /** Class to respond to new miles value. */
    private class NewMilesValue implements ActionListener {

        /** Convert the miles value to corresponding kilometers value.
         *  @param e ActionEvent object - not used
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double milesValue =
                        Double.parseDouble(milesField.getText());
                double kilometersValue =
                        DistanceConverter.toKilometers(milesValue);
                kilometersField.setText(String.format("%.1f", kilometersValue));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Number Format",
                        "",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** Class to respond to new kilometers value. */
    private class NewKilometersValue implements ActionListener {

        /** Convert the kilometers value to corresponding miles value.
         *  @param e ActionEvent object - not used
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double kilometersValue =
                        Double.parseDouble(kilometersField.getText());
                double milesValue =
                        DistanceConverter.toMiles(kilometersValue);
                milesField.setText(String.format("%.1f", milesValue));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Number Format",
                        "",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
/*</exercise>*/
