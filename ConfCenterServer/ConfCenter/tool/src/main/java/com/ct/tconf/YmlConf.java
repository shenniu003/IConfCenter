package com.ct.tconf;

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取yml配置文件
 * Created by Administrator on 2018/8/31.
 */
public class YmlConf {

    public static Map<String, Object> readConfToHashMap(String path) throws IOException {
        Map<String, Object> map = new HashMap<>();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            Yaml yaml = new Yaml();
            Map<String, Object> ymlMap = yaml.load(fileInputStream);
            ymlMap.forEach((key, val) -> {
//                String newVal = (key + "=" + val).replaceAll("=\\{", ".").replaceAll("}", "");
//                String[] newValArr = newVal.split("=");
//                map.put(
//                        newValArr[0],
//                        newValArr[1]);

                map.put(key, val);
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return map;
    }

    public static String readConfToJson(String path) throws IOException {
        Map map = readConfToHashMap(path);
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
