package main;

import fileManagers.FileReplace;
import fileManagers.SensorInfoFileManager;
import menuPanels.ActiveTextField;
import sensor.SensorManager;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by manhongren on 5/31/17.
 */
public class KeyPadPanel extends JPanel {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b0;
    private JButton offButton;
    private JButton awayButton;
    private JButton onButton;

    private JButton menuBtn;
    private JButton deleteBtn;
    private boolean isBuildingLayoutShown = true;
    private ActionHandler actionHandler = new ActionHandler();

    Thread t1;
    Thread t2;

    private static KeyPadPanel keyPadPanel;

    private KeyPadPanel(){
        //initialize keys
        initializeKeys();

        //Create the event handler

        setLayout(new GridLayout(5,3));

        // add action listeners
        addActionListeners();

        //add buttons to this panel
        addBtnToPanel();


    }

    public static KeyPadPanel getKeyPadPanel(){
        if (keyPadPanel == null){
            keyPadPanel = new KeyPadPanel();
        }
        return keyPadPanel;
    }

    private void initializeKeys(){
        offButton = new JButton("Off");
        onButton = new JButton("On");
        awayButton = new JButton("Away");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        menuBtn = new JButton("menu");
        b0 = new JButton("0");
        deleteBtn = new JButton("delete");
    }

    private void addBtnToPanel(){
        add(offButton);
        add(onButton);
        add(awayButton);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(menuBtn);
        add(b0);
        add(deleteBtn);
    }

    private void addActionListeners(){
        b1.addActionListener(actionHandler);
        b2.addActionListener(actionHandler);
        b3.addActionListener(actionHandler);
        b4.addActionListener(actionHandler);
        b5.addActionListener(actionHandler);
        b6.addActionListener(actionHandler);
        b7.addActionListener(actionHandler);
        b8.addActionListener(actionHandler);
        b9.addActionListener(actionHandler);
        b0.addActionListener(actionHandler);
        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //switch only between buildingLayoutPanel and menuPanel

                if (isBuildingLayoutShown){
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");

                    slideIn(DisplayPanel.getDisplayPanel());

                } else {
                   // SensorManager.getInstance().updateButtonState();
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "newBuildingLayoutPanel");

                    //此处如果加slide会导致Layout图不停的repaint，图出的特别慢。
                    //slideIn(DisplayPanel.getDisplayPanel());
                }
                isBuildingLayoutShown = !isBuildingLayoutShown;
            }
        });

        //delete the last digit from any Textfield
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField currTextField = ActiveTextField.getActiveTextField().getCurrentTextField();
                if (currTextField.getText().length() > 0){
                    currTextField.setText(""+currTextField.getText().substring(0, currTextField.getText().length() - 1));
                }
            }
        });

        offButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SensorManager.getInstance().setAllSensors(false);

            }
        });

        onButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SensorManager.getInstance().setAllSensors(true);
            }
        });
    }

    public void slideOut(Component parent){
        double x;
        double y;
        int initial = 0;
        x = parent.getLocation().getX();
        y = parent.getLocation().getY();

        t1 = new Thread(() -> {
            for(double i = x; i >= x-600; i-- ){
                parent.setLocation((int)i,(int)y);
                try{
                    t1.sleep(1);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }); t1.start();

    }

    public void slideIn(Component parent){
        double x;
        double y;
        int initial = 0;
        x = parent.getLocation().getX();
        y = parent.getLocation().getY();

        t2 = new Thread(() -> {
            for(double i = 600 ; i >= 0 ; i-- ){
                parent.setLocation((int)i,(int)y);
                try{
                    t2.sleep(1);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }); t2.start();

    }


    public class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source instanceof JButton) {
                JButton btn = (JButton) source;
                try {
                    int value = Integer.parseInt(btn.getText().trim());
                    Component comp = ActiveTextField.getActiveTextField().getCurrentTextField();
                    if (comp instanceof JTextComponent) {
                        JTextComponent tc = (JTextComponent) comp;
                        tc.setText(tc.getText() + value);
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }

        }
    }


}
