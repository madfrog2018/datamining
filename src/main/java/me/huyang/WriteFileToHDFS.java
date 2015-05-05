package me.huyang;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by young on 2015/5/5.
 */
public class WriteFileToHDFS {


    private static final Logger LOGGER = LogManager.getLogger(WriteFileToHDFS.class);
    private static IntWritable key = new IntWritable();
    private static Text value = new Text();

    public static String getPathStr() {
        return pathStr;
    }

    public static void setPathStr(String pathStr) {
        WriteFileToHDFS.pathStr = pathStr;
    }

    private static String pathStr;
    private static SequenceFile.Writer writer = null;
    static {
        Configuration conf = new Configuration();
        try {
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(pathStr);
            writer = SequenceFile.createWriter(fs, conf, path,
                    IntWritable.class, Text.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(int keyInt, String valueStr) {

        if (null != writer) {

            key.set(keyInt);
            value.set(valueStr);
            try {
                writer.append(key, value);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeStream(writer);
            }
        } else {
            LOGGER.info("writer is null");
        }
    }
}
