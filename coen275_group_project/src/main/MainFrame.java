package main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by manhongren on 5/31/17.
 */
public class MainFrame extends JFrame{
    public static void main(String[] args) {
        JFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("SoSafe");
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);

        mainFrame.setSize(600,800);
        mainFrame.setVisible(true);

    }
    public MainFrame(){
        setLayout(new GridLayout(0, 1));
        add(DisplayPanel.getDisplayPanel());
        add(KeyPadPanel.getKeyPadPanel());
    }
}
