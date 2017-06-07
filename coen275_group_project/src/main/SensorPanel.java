package main;

import menuPanels.ActiveTextField;
import sensor.Sensor;
import sensor.SensorManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by manhongren on 6/6/17.
 */
public class SensorPanel extends JPanel implements SensorManager.OnSensorChangeListener {
    private static final Font LABEL_FONT = new Font("GungSeo", Font.PLAIN, 30);
    private static final Border LABEL_BORDER = BorderFactory.createLineBorder(Color.BLUE, 2);
    private static final int ROOM_NUM = 6;
    private static final int ROOM_PER_ROW = 3;

    private final boolean showRadioButton;
    private final boolean showButton;

    private BufferedImage image;
    private JLabel fireLabel;
    private JLabel breakInLabel;
    private JRadioButton fireRadioButton;
    private JRadioButton breakInRadioButton;
    private final Map<JCheckBox, Sensor> sensorByCheckbox = new HashMap<>();

    private List<JLabel> roomLabels = new ArrayList<>();

    private final Set<JButton> buttons;
    private final Set<JCheckBox> checkBoxes;

    public SensorPanel(boolean showRadioButton, boolean showButton){
        this.showRadioButton = showRadioButton;
        this.showButton = showButton;

        SensorManager.getInstance().registerOnSensorChangeListener(this);

        buttons = new HashSet<>();
        checkBoxes = new HashSet<>();

//        for (int i = 0; i < ROOM_NUM; i++) {
//            JLabel label = new JLabel("Room " + (i + 1));
//            label.setBorder(LABEL_BORDER);
//            roomLabels.add(label);
//            add(label);
//        }


        if (showButton) {
            addButtons(SensorManager.getInstance().getSensors());
        } else {
            addCheckBoxes(SensorManager.getInstance().getSensors());
        }

        if (showRadioButton) {
            fireLabel = new JLabel("Fire Sensor");
            breakInLabel = new JLabel("BreakIn Sensor");
            fireRadioButton = new JRadioButton();
            breakInRadioButton = new JRadioButton();
            ButtonGroup group = new ButtonGroup();
            group.add(fireRadioButton);
            group.add(breakInRadioButton);
            fireRadioButton.setSelected(true);
            layoutRadioButtons();

            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    Sensor sensor = new Sensor(true, getSensorType(), e.getX(), e.getY());
                    SensorManager.getInstance().addSensor(sensor);
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });
        }

        setPreferredSize(new Dimension(650, 500));
        try{
            image = ImageIO.read(new File("src/resources/clear_building.png"));
        } catch (IOException e) {
            System.out.println("Can't open the image");
        }

        setLayout(null);
        registerActionListeners();
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("paint component");
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);

        if (showRadioButton) {
            addRadioButtonsToPanel();
        }

//        int labelWidth = image.getWidth() / ROOM_PER_ROW;
//        int labelHeight = image.getHeight()
//                / (roomLabels.size() / ROOM_PER_ROW + ((roomLabels.size() % ROOM_PER_ROW) > 0 ? 1 : 0));
//
//        for (int i = 0; i < roomLabels.size(); i++) {
//            int row = i / ROOM_PER_ROW;
//            int col = i % ROOM_PER_ROW;
//            roomLabels.get(i).setBounds(col * labelWidth, row * labelHeight, labelWidth, labelHeight);
//        }
    }

    private void layoutRadioButtons(){
        fireLabel.setBounds(120, 350, 200, 30);
        fireLabel.setForeground(Color.RED);
        fireLabel.setFont(LABEL_FONT);
        fireRadioButton.setBounds(150, 380, 50, 20);

        breakInLabel.setBounds(300, 350, 200, 30);
        breakInLabel.setFont(LABEL_FONT);
        breakInRadioButton.setBounds(300, 380, 50,20);
        breakInLabel.setForeground(Color.BLUE);
    }

    private void registerActionListeners(){}

    private void addRadioButtonsToPanel(){
        add(fireLabel);
        add(breakInLabel);
        add(fireRadioButton);
        add(breakInRadioButton);
    }


    private Sensor.Type getSensorType() {
       if (fireRadioButton.isSelected()) {
           return Sensor.Type.FIRE;
       } else if (breakInRadioButton.isSelected()) {
           return Sensor.Type.BREAK_IN;
       } else {
           return null;
       }
    }

    @Override
    public void onSensorChange(Set<Sensor> sensors) {
        removeAll();
        if (showButton) {
            addButtons(sensors);
        } else {
            addCheckBoxes(sensors);
        }
        repaint();
    }

    private void addButtons(Set<Sensor> sensors) {
        for (Sensor s : sensors) {
            JButton button = s.generateButton();
            add(button);
        }
    }

    private void addCheckBoxes(Set<Sensor> sensors) {
        sensorByCheckbox.clear();
        for (Sensor s : sensors) {
            JCheckBox checkBox = s.generateCheckBox();
            sensorByCheckbox.put(checkBox, s);
            add(checkBox);
        }
    }

    public void updateSensorsFromCheckBoxes() throws IOException {
        for (Map.Entry<JCheckBox, Sensor> entry : sensorByCheckbox.entrySet()){
            entry.getValue().setSensorOn(entry.getKey().isSelected());
        }
        SensorManager.getInstance().syncToFile();
        SensorManager.getInstance().notifySensorChange();
    }
}
