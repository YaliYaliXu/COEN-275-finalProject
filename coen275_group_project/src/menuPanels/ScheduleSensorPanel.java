package menuPanels;

import main.DisplayPanel;
import main.SensorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by manhongren on 6/3/17.
 */
public class ScheduleSensorPanel extends JPanel {
    private JLabel scheduleSensorLabel;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private JButton doneButton;
    private SensorPanel sensorPanel;

    private final Set<JCheckBox> checkBoxes;

    public ScheduleSensorPanel(){
        checkBoxes = new HashSet<>();

        setPreferredSize(new Dimension(350, 400));

        labelPanel = new JPanel();
        buttonPanel = new JPanel();
        doneButton = new JButton("Done");
        sensorPanel = new SensorPanel(false, false);
        scheduleSensorLabel = new JLabel("Check the sensor you want to turn on: ");
        scheduleSensorLabel.setFont(new Font("Serif", Font.BOLD, 16));

        add(labelPanel);
        add(sensorPanel);
        add(buttonPanel);
        addButtonsToPanel();
        setLayout(new BoxLayout(this, Y_AXIS));

        //add action listeners
        addActionListeners();
    }

    private void addButtonsToPanel() {
        labelPanel.add(scheduleSensorLabel);
        buttonPanel.add(doneButton);
    }

    private void addActionListeners(){
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sensorPanel.updateSensorsFromCheckBoxes();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");
            }
        });

    }
}
