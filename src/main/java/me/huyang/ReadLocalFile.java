package me.huyang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by young on 2015/5/5.
 */
public class ReadLocalFile {

    public static void readFile(String hdfsPath, File file) throws Exception {

        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String string;
        WriteFileToHDFS.setPathStr(hdfsPath);
        while ((string = reader.readLine()) != null) {

            String[] keyvalue = string.split("\\t");
            WriteFileToHDFS.write(Integer.valueOf(keyvalue[0]), keyvalue[1]);
        }
    }

    public static void main(String[] args) {

        String hdfsPath = args[args.length -1];
        String localPath = args[args.length -2];
        File file = new File(localPath);
        try {
            readFile(hdfsPath, file);
        } catch (Exception e) {
            System.out.println("error here");
            e.printStackTrace();
        }
    }
}
