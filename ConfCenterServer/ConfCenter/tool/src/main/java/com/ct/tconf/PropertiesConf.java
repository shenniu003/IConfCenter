package com.ct.tconf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2018/8/31.
 */
public class PropertiesConf {

    public static Map<String, Object> readConfToMap(String path) throws IOException {

        Map<String, Object> map = new HashMap<>();
        FileReader fileReader = null;
        BufferedReader reader = null;
        try {
            fileReader = new FileReader(path);
            reader = new BufferedReader(fileReader);
            Properties readProperties = new Properties();
            readProperties.load(reader);
            for (Object key :
                    readProperties.keySet()) {
                map.put(key.toString(), readProperties.get(key));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
        return map;
    }
}
