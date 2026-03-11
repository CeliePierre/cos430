package KW.AXC;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*<exercise chapter="C" section="3" type="programming" number="1">*/
public class ExerciseC_3_1 extends JFrame {

    private ExerciseC_3_1() {
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        Color[][] colors = new Color[][]{
            {Color.YELLOW, Color.GRAY, Color.WHITE, Color.GRAY, Color.BLUE},
            {Color.GRAY, Color.GRAY, Color.GREEN, Color.GRAY, Color.GRAY},
            {Color.GRAY, Color.ORANGE, Color.GRAY, new Color(128, 0, 255), Color.GRAY},
            {Color.GREEN, Color.GRAY, Color.RED, Color.GRAY, Color.BLUE},
            {Color.YELLOW, Color.GRAY, Color.GRAY, Color.ORANGE, Color.GRAY}};
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JLabel label = new JLabel();
                label.setBackground(colors[i][j]);
                label.setBorder(blackBorder);
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(50, 25));
                panel.add(label);
            }
        }
        add(panel);
        pack();
    }

    public static void main(String[] args) {
        new ExerciseC_3_1().setVisible(true);
    }
}
/*</exercise>*/
