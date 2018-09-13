package com.ct.tconf;

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 读取yml配置文件 解析未成功待完成
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
                String strOrg = key + "=" + val;
                System.out.println(strOrg);
                map.putAll(matchConfItem("", strOrg));
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

    private static Map<String, String> matchConfItem(String prevKey, String val) {
        Map<String, String> map = new HashMap<>();
        if (val.isEmpty()) {
            return map;
        }
        Pattern pattern = Pattern.compile("^(?<key>[^=]+)=\\{(?<val>.*)}$");
        Matcher matcher = pattern.matcher(val);

        Pattern pattern01 = Pattern.compile("(?<key01>[^=|,]+)=(?<val01>[^,]+)");
        while (matcher.find()) {
            String patternKey = matcher.group("key");
            patternKey = patternKey.replaceAll("^\\{", "");
            patternKey = prevKey.length() <= 0 ? patternKey : prevKey + "." + patternKey;

            String patternVal = matcher.group("val");

            System.out.println(patternKey + "-" + patternVal);
            // map.putAll(matchConfItem(patternKey, patternVal));

            Matcher matcher01 = pattern01.matcher(patternVal);
            while (matcher01.find()) {
                String patternKey01 = matcher01.group("key01");
                String patternVal01 = matcher01.group("val01");
                System.out.println(patternKey01 + "--" + patternVal01);
            }
        }
        if (map.size() <= 0) {
            map.put(prevKey, val);
        }
        return map;
    }

    public static String readConfToJson(String path) throws IOException {
        Map map = readConfToHashMap(path);
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
