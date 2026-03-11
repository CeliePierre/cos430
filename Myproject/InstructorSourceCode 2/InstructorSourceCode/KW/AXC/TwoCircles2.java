/*<listing chapter="C" number="2">*/
package KW.AXC;

import java.awt.GridLayout;
import javax.swing.JFrame;

/** TwoCircles is a simple event-oriented application that
 *  displays two circles and two buttons. The buttons are
 *  placed below the circles. The buttons are labeled on/off.
 *  When a button is clicked, the state of the circle is toggled.
 *  @author Koffman & Wolfgang
 */
public class TwoCircles2 extends JFrame {

    // Constructor
    /** Construct a TwoCircles object. Set the title and
     *  default close operation. Using a grid layout add
     *  two CirclePanel objects. Finally, pack the frame
     *  and set it visible.
     */
    public TwoCircles2() {
        super("Two Circles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(1, 2));
        getContentPane().add(new CirclePanel2(100));
        getContentPane().add(new CirclePanel2(100));
        pack();
        setVisible(true); // Show the JFrame.
    }

    // Main Method
    /** Instantiate a TwoCircles object.
     *  @param args Not used. */
    public static void main(String[] args) {
        TwoCircles2 tc = new TwoCircles2();
    }
}
/*</listing>*/
