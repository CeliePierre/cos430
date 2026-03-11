package KW.AXC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*<exercise chapter="C" section="3" type="programming" number="2">*/
/** CombinedLayoutDemo generates a simple frame that shows how components
 *  are arranged using a combination of layouts. 
 */
public class CombinedLayoutDemo extends JFrame {
    
    // Main Method
    public static void main(String args[]) {
        // Construct a CombinedLayoutDemo object
        JFrame frame = new CombinedLayoutDemo();
        // Display the frame
        frame.setVisible(true);
    }

    // Constructor
    public CombinedLayoutDemo() {
        setTitle("CombinedLayoutDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Get a reference to the content pane 
	// (It is by default in border layout).
	Container contentPane = getContentPane();
	// Create two internal panels
	JPanel panel1 = new JPanel();
	// One in border layout
	panel1.setLayout(new BorderLayout());
	// The other in grid layout
	JPanel panel2 = new JPanel();
	panel2.setLayout(new GridLayout(2,2));
        Border blackBorder = 
            BorderFactory.createLineBorder(Color.BLACK); 
        // Create some lables to be added to the panels
	JLabel outerNorth = new JLabel("Outer North", JLabel.CENTER);
	outerNorth.setBorder(blackBorder);
	JLabel outerEast = new JLabel("Outer East", JLabel.CENTER);
	outerEast.setBorder(blackBorder);
	JLabel outerWest = new JLabel("Outer West", JLabel.CENTER);
	outerWest.setBorder(blackBorder);
	JLabel innerNorth = new JLabel("Inner North", JLabel.CENTER);
	innerNorth.setBorder(blackBorder);
	JLabel innerSouth = new JLabel("Inner South", JLabel.CENTER);
	innerSouth.setBorder(blackBorder);
	JLabel innerCenter = new JLabel("Inner Center", JLabel.CENTER);
	innerCenter.setBorder(blackBorder);
	JLabel innerEast = new JLabel("Inner East", JLabel.CENTER);
	innerEast.setBorder(blackBorder);
	JLabel innerWest = new JLabel("Inner West", JLabel.CENTER);
	innerWest.setBorder(blackBorder);
	JLabel bottomUL = new JLabel("Bottom Upper Left", JLabel.CENTER);
	bottomUL.setBorder(blackBorder);
	JLabel bottomUR = new JLabel("Bottom Upper Right", JLabel.CENTER);
	bottomUR.setBorder(blackBorder);
	JLabel bottomLL = new JLabel("Bottom Lower Left", JLabel.CENTER);
	bottomLL.setBorder(blackBorder);
	JLabel bottomLR = new JLabel("Bottom Lower Right", JLabel.CENTER);
	bottomLR.setBorder(blackBorder);
	// Now add them to the appropraite panels/containers
	contentPane.add(outerNorth, BorderLayout.NORTH);
	contentPane.add(outerEast, BorderLayout.EAST);
	contentPane.add(outerWest, BorderLayout.WEST);
	contentPane.add(panel1, BorderLayout.CENTER);
	contentPane.add(panel2, BorderLayout.SOUTH);
	panel1.add(innerNorth, BorderLayout.NORTH);
	panel1.add(innerCenter, BorderLayout.CENTER);
	panel1.add(innerSouth, BorderLayout.SOUTH);
	panel1.add(innerEast, BorderLayout.EAST);
	panel1.add(innerWest, BorderLayout.WEST);
	panel2.add(bottomUL);
	panel2.add(bottomUR);
	panel2.add(bottomLL);
	panel2.add(bottomLR);
	// Size the frame to fit
        pack();
    }
}
/*</exercise>*/

    
