package main;

import fileManagers.PasswordFileManager;
import menuPanels.ActiveTextField;
import sensor.SensorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by manhongren on 6/5/17.
 */
public class PopupFrame extends JFrame {
    private JLabel imageLabel;
    private JLabel label;
    private JPasswordField passwordField;
    private JButton enterButton;
    private JPanel topImagePanel;
    private JPanel bottomInputPanel;
    public PopupFrame(String fileName){
        //initialize
        initializeComponents(fileName);
        addComponentsToPanel();
        setLayout(new GridLayout(0, 1));
        //register action listeners
        registerActionListeners();
        add(topImagePanel);
        add(bottomInputPanel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void initializeComponents(String fileName){
        ImageIcon icon = new ImageIcon(fileName);
        imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        label = new JLabel(" Enter password to disarm system: ");
        label.setFont(new Font("Serif", Font.ITALIC, 20));
        passwordField = new JPasswordField();
        enterButton = new JButton("Enter");
        topImagePanel = new JPanel();
        bottomInputPanel = new JPanel();
    }

    private void addComponentsToPanel(){
        topImagePanel.add(imageLabel);
        bottomInputPanel.setLayout(new GridLayout(0, 1, 8, 10));
        bottomInputPanel.add(label);
        bottomInputPanel.add(passwordField);
        bottomInputPanel.add(enterButton);
    }

    private void registerActionListeners(){
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actualPassword = PasswordFileManager.getFileManager().readFromFile();
                String userEnterPassword = String.valueOf(passwordField.getPassword());
                if (actualPassword.equals(userEnterPassword)){
                    dispose(); // close the pop up frame
                    SensorManager.getInstance().setAllSensors(false);
                } else {
                    JOptionPane.showMessageDialog(getParent(),
                            "The password is not correct",
                            "Wrong Password",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ActiveTextField.getActiveTextField().setCurrentTextField(passwordField);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }


}
