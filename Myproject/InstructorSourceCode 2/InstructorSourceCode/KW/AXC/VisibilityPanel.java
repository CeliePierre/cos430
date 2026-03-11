/*<exercise chapter="C" section="4" type="programming" number="2">*/
package KW.AXC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

public class VisibilityPanel extends CheckBoxGroupPanel {

    private boolean clear = true;
    private boolean rain = false;
    private boolean snow = false;

    public VisibilityPanel() {
        super(new String[]{"Clear", "Rain", "Snow"});
        ActionListener visibilityListener = new VisibilityListener();
        for (int i = 0; i < 3; i++) {
            ActionListener[] actionListeners = checkBoxes[i].getActionListeners();
            for (int j = 0; j < actionListeners.length; j++) {
                checkBoxes[i].removeActionListener(actionListeners[j]);
            }
            checkBoxes[i].addActionListener(visibilityListener);
        }
    }

    @Override
    public String getCurrentChoice() {
        if (clear) {
            return "Clear";
        }
        if (rain && snow) {
            return "Mix";
        }
        if (rain) {
            return "Rain";
        }
        if (snow) {
            return "Snow";
        }
        return null;
    }

    private class VisibilityListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentChoice = (JCheckBox) e.getSource();
            if (currentChoice.getText().equals("Clear")) {
                clear = true;
                rain = false;
                snow = false;
                checkBoxes[0].setSelected(true);
                checkBoxes[1].setSelected(false);
                checkBoxes[2].setSelected(false);
            } else if (currentChoice.getText().equals("Rain")) {
                if (clear) {
                    clear = false;
                    checkBoxes[0].setSelected(false);
                }
                if (currentChoice.isSelected()) {
                    rain = true;
                } else {
                    rain = false;
                }
            } else if (currentChoice.getText().equals("Snow")) {
                if (clear) {
                    clear = false;
                    checkBoxes[0].setSelected(false);
                }
                if (currentChoice.isSelected()) {
                    snow = true;
                } else {
                    snow = false;
                }
            }
            if (!rain && !snow) {
                clear = true;
                checkBoxes[0].setSelected(true);
            }
            firePropertyChange("CurrentChoice", null, currentChoice);
        }
    }
}
/*</exercise>*/
