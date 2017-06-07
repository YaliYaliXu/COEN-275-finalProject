package fileManagers;

import java.io.*;

/**
 * Created by manhongren on 6/5/17.
 */
public class PasswordFileManager {

    private File file;
    /**
     * each line in a file is an entry
     */

    private static PasswordFileManager passwordFileManager;

    public static PasswordFileManager getFileManager() {
        if (passwordFileManager == null) {
            passwordFileManager = new PasswordFileManager();
            passwordFileManager.init();
        }
        return passwordFileManager;
    }

    private void init() {
        this.file = new File("password.txt");
    }

    // add a password to the file
    public void addToFile(String password) {
        try {
            FileWriter fw = new FileWriter(file, true); // true is to append, false is to overwrite
            fw.append(password + '\n');
            System.out.println("Adding password " + password + " to file");
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public String getFileName() {
        return this.file.getName();
    }

    //read the last line of the file
    public String readFromFile() {
        String lastLine = "";
        String sCurrentLine;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(getFileName()));
            while ((sCurrentLine = br.readLine()) != null) {
               // System.out.println(sCurrentLine);
                lastLine = sCurrentLine;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }
    public boolean isExit(){
        return file.exists();
    }
}