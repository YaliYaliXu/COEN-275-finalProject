package main;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by YALI on 6/6/17.
 */
public class CoverPanel extends JPanel {

    JPanel welcomePanel;

    public CoverPanel(){
        welcomePanel = new JPanel();
        createWelcomePanel();

    }

    public void createWelcomePanel(){
        JLabel contactID,ID, nameLabel, addressLabel, contactNumberLabel, emailLabel, datesLabel;
        JTextField name, address, phone, email, dates;
        JTextArea infoSummary;
        JPanel contactPanel, namePanel, addressPanel, phonePanel, emailPanel, datesPanel, infoSummaryPanel ;
        JButton add;

        contactID = new JLabel("Service Contact ID:");
        ID = new JLabel();
        nameLabel = new JLabel("Customer name: ");
        addressLabel = new JLabel("Address of the property: ");
        contactNumberLabel = new JLabel("Add 2 contact Numbers:");
        emailLabel = new JLabel("Customer email: ");
        datesLabel = new JLabel("Effective dates:");

        name = new JTextField(10);
        address = new JTextField(10);
        phone = new JTextField(10);
        email = new JTextField(10);
        dates = new JTextField(10);

        infoSummary = new JTextArea(20,20);
        add = new JButton("Add to file");

        contactPanel = new JPanel();
        namePanel = new JPanel();
        addressPanel = new JPanel();
        phonePanel = new JPanel();
        emailPanel = new JPanel();
        datesPanel = new JPanel();
        infoSummaryPanel = new JPanel();

        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoSummary.append("Customer name: " + name.getText() + "\n");
            }
        });

        address.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoSummary.append("Address of property: " + address.getText() + "\n");
            }
        });

        phone.addActionListener(new ActionListener() {
            File file = new File("phoneNumber.txt");
            @Override
            public void actionPerformed(ActionEvent e) {
                infoSummary.append("Emergency contact added: " + phone.getText() + "\n");

                String number = phone.getText();
                try {
                    FileWriter fw = new FileWriter(file, true);
                    fw.append(number + '\n');
                    fw.close();
                }
                catch(FileNotFoundException ex){
                    System.out.println("unable to open file: " + "phoneNumber.txt");
                }
                catch(IOException exc){
                    exc.printStackTrace();
                }

            }
        });

        email.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoSummary.append("Customer email: " + email.getText() + "\n");
                ID.setText(email.getText());
                infoSummary.append("Service ID: " + email.getText() + "\n");
            }
        });

        dates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoSummary.append("Effective dates: " + dates.getText() + "\n");
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("infoSummary.txt");

                String info = infoSummary.getText();
                try {
                    FileWriter fw = new FileWriter(file, true);
                    fw.append(info + '\n');
                    fw.close();
                }
                catch(FileNotFoundException ex){
                    System.out.println("unable to open file: " + "infoSummary.txt");
                }
                catch(IOException exc){
                    exc.printStackTrace();
                }

            }
        });

        contactPanel.add(contactID);
        contactPanel.add(ID);
        namePanel.add(nameLabel);
        namePanel.add(name);
        addressPanel.add(addressLabel);
        addressPanel.add(address);
        phonePanel.add(contactNumberLabel);
        phonePanel.add(phone);
        emailPanel.add(emailLabel);
        emailPanel.add(email);
        datesPanel.add(datesLabel);
        datesPanel.add(dates);
        infoSummaryPanel.add(infoSummary);
        infoSummaryPanel.add(add);

        welcomePanel.setLayout(new GridLayout(0,1));
        welcomePanel.setBorder(new TitledBorder(new EtchedBorder(), "Initial system"));
        welcomePanel.setSize(300,500);
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(0,1));
        upperPanel.add(contactPanel);
        upperPanel.add(namePanel);
        upperPanel.add(addressPanel);
        upperPanel.add(phonePanel);
        upperPanel.add(emailPanel);
        upperPanel.add(datesPanel);
        welcomePanel.add(upperPanel);
        welcomePanel.add(infoSummaryPanel);
    }
}
