/*<exercise chapter="C" section="4" type="programming" number="1">*/
package KW.AXC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButtonGroupPanel extends JPanel {

    // Data Fields
    String[] selections = null;
    JRadioButton[] radioButtons = null;
    ButtonGroup buttonGroup = null;
    String currentChoice = null;

    // Constructor
    public RadioButtonGroupPanel(String[] selections) {
        this.selections = selections;
        radioButtons = new JRadioButton[selections.length];
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // Create a button group for the buttons
        buttonGroup = new ButtonGroup();
        // Create radio buttons and add them to the panel
        // Also add them to the button group
        ActionListener newSelection = new SelectionChangeMade();
        for (int i = 0; i != selections.length; ++i) {
            radioButtons[i] = new JRadioButton(selections[i]);
            radioButtons[i].getModel().setActionCommand(selections[i]);
            radioButtons[i].addActionListener(newSelection);
            buttonGroup.add(radioButtons[i]);
            add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);
        currentChoice = selections[0];
    }

    // Action Listener Classes
    private class SelectionChangeMade implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String oldChoice = currentChoice;
            currentChoice =
                    buttonGroup.getSelection().getActionCommand();
            firePropertyChange("CurrentChoice", oldChoice, currentChoice);
        }
    }

    public String getCurrentChoice() {
        return currentChoice;
    }
}
/*</exercise>*/
