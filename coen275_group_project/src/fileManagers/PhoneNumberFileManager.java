package fileManagers;

import sensor.Sensor;

import java.io.*;

/**
 * Created by manhongren on 6/5/17.
 */
public class PhoneNumberFileManager {

    private File file;
    /**
     * each line in a file is an entry
     */
    public static class Entry{
        String priority;
        Integer phoneNumber;
        public Entry(String priority, Integer phoneNumber){
            this.priority = priority;
            this.phoneNumber = phoneNumber;
        }
        @Override
        public String toString(){
            return  priority + ", phoneNumber is:" + phoneNumber;
        }
    }

    private static PhoneNumberFileManager phoneNumberFileManager;

    public static PhoneNumberFileManager getFileManager(){
        if (phoneNumberFileManager == null){
            phoneNumberFileManager = new PhoneNumberFileManager();
            phoneNumberFileManager.init();
        }
        return phoneNumberFileManager;
    }

    private void init() {
        this.file = new File("phoneNumber.txt");
    }

    // add an entry to the file
    public void addToFile(Entry e){
        try {
            FileWriter fw = new FileWriter(file,true); // true is append, false is to overwrite
            fw.append(e.toString() + '\n');
            System.out.println("Adding phone " + e.toString() + " to file");
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
