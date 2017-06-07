package main;

import fileManagers.PasswordFileManager;
import menuPanels.ActiveTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by manhongren on 6/5/17.
 */
public class CheckIdentityFrame extends JFrame {
    private JLabel label;
    private JPasswordField passwordField;
    private JButton enterButton;
    private String nextPanelToShow;
    public CheckIdentityFrame(String nextPanelToShow){
        this.nextPanelToShow = nextPanelToShow;
        label = new JLabel("    Enter Password:     ");
        passwordField = new JPasswordField();
        enterButton = new JButton("Enter");
        setLayout(new GridLayout(0,1));
        registerActionListeners();
        add(label);
        add(passwordField);
        add(enterButton);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void registerActionListeners(){
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actualPassword = PasswordFileManager.getFileManager().readFromFile();
                String userEnterPassword = String.valueOf(passwordField.getPassword());
                if (actualPassword.equals(userEnterPassword)){
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), nextPanelToShow);
                    dispose(); // close the pop up frame
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
