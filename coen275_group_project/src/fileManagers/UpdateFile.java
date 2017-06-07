package fileManagers;

import java.io.*;

/**
 * Created by manhongren on 6/4/17.
 */
public class UpdateFile {
    public static void updateFiles(String fileName, String symbol, String newStr){
        String oldFileName = fileName;
        String tmpFileName = "tmp.txt";

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(oldFileName));
            bw = new BufferedWriter(new FileWriter(tmpFileName));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(symbol))
                    line = newStr;
                bw.write(line+"\n");
            }
        } catch (Exception e) {
            return;
        } finally {
            try {
                if(br != null)
                    br.close();
            } catch (IOException e) {
                //
            }
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                //
            }
        }
        // Once everything is complete, delete old file..
        File oldFile = new File(oldFileName);
        oldFile.delete();

        // And rename tmp file's name to old file name
        File newFile = new File(tmpFileName);
        newFile.renameTo(oldFile);
    }
}
