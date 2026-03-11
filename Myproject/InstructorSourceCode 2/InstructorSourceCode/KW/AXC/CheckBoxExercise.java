/*<exercise chapter="C" section="4" type="programming" number="2">*/
package KW.AXC;

import java.awt.Container;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CheckBoxExercise extends JFrame {

    private CheckBoxGroupPanel temperaturePanel;
    private CheckBoxGroupPanel conditionPanel;
    private CheckBoxGroupPanel visibilityPanel;
    private JTextArea textArea = new JTextArea(4, 34);

    public static void main(String[] args) {
        new CheckBoxExercise();
    }

    private CheckBoxExercise() {
        PropertyChangeListener updateDisplays = new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent e) {
                StringBuffer sb =
                        new StringBuffer("Here are the weather conditions\n");
                sb.append(temperaturePanel.getCurrentChoice());
                sb.append("\n");
                sb.append(conditionPanel.getCurrentChoice());
                sb.append("\n");
                sb.append(visibilityPanel.getCurrentChoice());
                textArea.setText(sb.toString());
            }
        };
        String[] temperatures = {"Cold", "Mild", "Hot"};
        temperaturePanel =
                new CheckBoxGroupPanel(temperatures);
        temperaturePanel.addPropertyChangeListener("CurrentChoice",
                updateDisplays);
        String[] conditions = {"Dry", "Wet"};
        conditionPanel =
                new CheckBoxGroupPanel(conditions);
        conditionPanel.addPropertyChangeListener("CurrentChoice",
                updateDisplays);
        visibilityPanel =
                new VisibilityPanel();
        visibilityPanel.addPropertyChangeListener("CurrentChoice",
                updateDisplays);
        JPanel buttonPanels = new JPanel();
        buttonPanels.setLayout(new BoxLayout(buttonPanels,
                BoxLayout.Y_AXIS));
        buttonPanels.add(temperaturePanel);
        buttonPanels.add(conditionPanel);
        buttonPanels.add(visibilityPanel);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(buttonPanels);
        updateDisplays.propertyChange(null);
        contentPane.add(textArea);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Radio Button Exercise");
        setSize(400, 200);
        setVisible(true);
    }
}
/*</exercise>*/
