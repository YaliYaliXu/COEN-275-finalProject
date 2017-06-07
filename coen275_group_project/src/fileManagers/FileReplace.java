package fileManagers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manhongren on 6/5/17.
 */
public class FileReplace {
    public static void doIt(String filePath, String oldStr, String newStr) {
        List<String> lines = new ArrayList<>();
        String line;
        try {
            File f1 = new File(filePath);
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains(oldStr))
                    line = line.replace(oldStr, newStr);
                lines.add(line);
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines){
                out.write(s);
                out.newLine();
            }

            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
