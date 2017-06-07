package menuPanels;

import fileManagers.PasswordFileManager;
import main.DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by manhongren on 6/2/17.
 */
public class PasswordPanel extends JPanel{
    //private JButton enterBtn;
    private JButton doneBtn;
    private JPasswordField passwordTextField;
    private JPasswordField confirmPasswordTextField;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private char[] firstInput;
    private char[] secondInput;

    public PasswordPanel(){
        initiateBtns();
        addActionListeners();
        setLayout(new GridLayout(0, 1, 5, 5));
        addBtnsToPanel();
    }


    private void initiateBtns(){
        passwordLabel = new JLabel("Please enter your password: ");
        confirmPasswordLabel = new JLabel("Please confirm your password: ");
        //enterBtn = new JButton("Enter");
        doneBtn = new JButton("Done");
        passwordTextField = new JPasswordField();
        passwordLabel.setFont(new Font("Serif", Font.ITALIC, 20));
        confirmPasswordTextField = new JPasswordField();
        confirmPasswordLabel.setFont(new Font("Serif", Font.ITALIC, 20));
    }
    private void addBtnsToPanel(){
        add(passwordLabel);
        add(passwordTextField);
        add(confirmPasswordLabel);
        add(confirmPasswordTextField);
       // add(enterBtn);
        add(doneBtn);
    }

    private void addActionListeners(){
        doneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstInput = passwordTextField.getPassword();
                secondInput = confirmPasswordTextField.getPassword();
                if (firstInput == null || firstInput.length == 0 || secondInput == null || secondInput.length == 0){
                    JOptionPane.showMessageDialog(getParent(),
                            "Please enter two passwords",
                            "No Password",
                            JOptionPane.WARNING_MESSAGE);
                    return;

                }
                for (int i = 0; i < firstInput.length; i++){
                    if (firstInput[i] != secondInput[i]){
                        JOptionPane.showMessageDialog(getParent(),
                                "The two passwords are not matching",
                                "Not Matching",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                PasswordFileManager.getFileManager().addToFile(String.valueOf(secondInput));
                DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");
            }
        });

        passwordTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ActiveTextField.getActiveTextField().setCurrentTextField(passwordTextField);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        confirmPasswordTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ActiveTextField.getActiveTextField().setCurrentTextField(confirmPasswordTextField);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }
}
