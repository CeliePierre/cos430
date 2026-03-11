package KW.AXC;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*<exercise chapter="C" section="3" type="programming" number="3">*/
public class ExerciseC_3_3 extends JFrame {

    public ExerciseC_3_3() {

        JPanel rows = new JPanel();
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        rows.setLayout(new BoxLayout(rows, BoxLayout.Y_AXIS));
        for (int i = 0; i < 5; i++) {
            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
            for (int j = 0; j < 10; j++) {
                JLabel label = new JLabel(i + ", " + j);
                label.setBorder(blackBorder);
                row.add(label);
            }
            rows.add(row);
        }
        add(rows);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
        new ExerciseC_3_3().setVisible(true);
    }
}
/*</exercise>*/
