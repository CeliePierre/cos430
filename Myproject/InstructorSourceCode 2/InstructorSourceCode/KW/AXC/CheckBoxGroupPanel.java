/*<exercise chapter="C" section="4" type="programming" number="2">*/
package KW.AXC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class CheckBoxGroupPanel extends JPanel {

    // Data Fields
    String[] selections = null;
    JCheckBox[] checkBoxes = null;
    JCheckBox currentChoice = null;

    // Constructor
    public CheckBoxGroupPanel(String[] selections) {
        this.selections = selections;
        checkBoxes = new JCheckBox[selections.length];
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        ActionListener newSelection = new SelectionChangeMade();
        for (int i = 0; i != selections.length; ++i) {
            checkBoxes[i] = new JCheckBox(selections[i]);
            checkBoxes[i].getModel().setActionCommand(selections[i]);
            checkBoxes[i].addActionListener(newSelection);
            add(checkBoxes[i]);
        }
        checkBoxes[0].setSelected(true);
        currentChoice = checkBoxes[0];
    }

    // Action Listener Classes
    private class SelectionChangeMade implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox oldChoice = currentChoice;
            oldChoice.setSelected(false);
            currentChoice = (JCheckBox) e.getSource();
            currentChoice.setSelected(true);
            firePropertyChange("CurrentChoice", null, currentChoice);
        }
    }

    public String getCurrentChoice() {
        return currentChoice.getText();
    }
}
/*</exercise>*/
