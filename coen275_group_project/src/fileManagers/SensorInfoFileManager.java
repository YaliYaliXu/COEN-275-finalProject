package fileManagers;

import sensor.Sensor;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by manhongren on 5/27/17. This Class is for managing files
 */
public class SensorInfoFileManager {

    private File file;

    private static SensorInfoFileManager fileManager;

    public static SensorInfoFileManager getFileManager() throws IOException {
        if (fileManager == null){
            fileManager = new SensorInfoFileManager();
        }
        return fileManager;
    }

    private SensorInfoFileManager() throws IOException {
        file = new File("sensorInfo.txt");
        file.createNewFile();
    }

    public void updateSensors(Set<Sensor> sensors) {
        StringBuilder sb = new StringBuilder();
        for (Sensor s : sensors) {
            sb.append(s).append("\n");
        }

        addToFile(sb.toString());
    }

    public void addToFile(String str){
        try {
            FileWriter fw = new FileWriter(file,false); // false means not to append, to overwrite
            fw.append(str);
            System.out.println("Adding to file " + '\n' + str);
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //read from the entire file
    public Set<Sensor> readFromFile(){
        Set<Sensor> sensors = new HashSet<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                sensors.add(Sensor.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sensors;
    }
}
