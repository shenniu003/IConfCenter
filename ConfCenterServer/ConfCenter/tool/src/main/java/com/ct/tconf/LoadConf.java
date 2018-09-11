package com.ct.tconf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/8/31.
 */
public class LoadConf {

    /**
     * 读取路径的文件内容
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static StringBuilder readConf(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if (path.isEmpty()) {
            return stringBuilder;
        }

        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            reader = new FileReader(path);
            bufferedReader = new BufferedReader(reader);
            char[] chars = new char[1024 * 1024];
            while (bufferedReader.read(chars) != -1) {
                stringBuilder.append(chars);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder;
    }

    /**
     * 根据配置文件后缀解析成map，支持后缀有.config .properties .xml
     * @param path
     * @return
     * @throws IOException
     */
    public static Map<String, Object> readConfToMap(String path) throws IOException {
        Map<String, Object> map = new HashMap<>();
        if (path.isEmpty()) {
            return map;
        }

        Pattern pattern = Pattern.compile("[^.]+.(?<profix>\\w+)");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            String profix = matcher.group("profix");
            if (profix.isEmpty()) {
                return map;
            }

            switch (profix.toLowerCase()) {
                case "config":
                    map = ConfigConf.readConfToMap(path);
                    break;
                case "properties":
                    map = PropertiesConf.readConfToMap(path);
                    break;
                default:
                    break;
            }
        }
        return map;
    }
}
