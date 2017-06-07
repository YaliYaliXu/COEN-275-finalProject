package main;

import menuPanels.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by manhongren on 6/1/17.
 */
public class DisplayPanel extends JPanel {
    private CardLayout cards = new CardLayout();
    private static DisplayPanel displayPanel;
    private JPanel menuPanel;
    private JPanel phoneNumberPanel;
    private JPanel passwordPanel;
    private JPanel scheduleTimePanel;
    private JPanel scheduleSensorPanel;
    private JPanel newBuildingPanel;

    private JPanel coverPanel;


    private DisplayPanel(){
        setLayout(cards);
        setBorder(BorderFactory.createEmptyBorder(8,8,12,8));
        initiatePanels();
        addPanels();
    }

    private void initiatePanels(){
        menuPanel = new MenuPanel();
        phoneNumberPanel = new PhoneNumberPanel();
        passwordPanel = new PasswordPanel();
        scheduleTimePanel = new ScheduleTimePanel();
        scheduleSensorPanel = new ScheduleSensorPanel();
        newBuildingPanel = new SensorPanel(true, true);
        coverPanel = new CoverPanel();
    }

    private void addPanels(){
        add(newBuildingPanel, "newBuildingLayoutPanel");
        add(menuPanel, "menuPanel");
        add(phoneNumberPanel, "phoneNumberPanel");
        add(passwordPanel, "passwordPanel");
        add(scheduleTimePanel, "scheduleTimePanel");
        add(scheduleSensorPanel, "scheduleSensorPanel");
    }

    public static DisplayPanel getDisplayPanel(){
        if (displayPanel == null){
            displayPanel = new DisplayPanel();
        }
        return displayPanel;
    }

    public CardLayout getCards(){
        return cards;
    }
}
