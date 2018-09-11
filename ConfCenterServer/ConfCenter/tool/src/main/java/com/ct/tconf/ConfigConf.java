package com.ct.tconf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析.config文件
 * Created by Administrator on 2018/8/31.
 */
public class ConfigConf {

    /**
     * 获取config配置信息
     * @param path
     * @return
     * @throws IOException
     */
    public static Map<String, Object> readConfToMap(String path) throws IOException {

        Map<String, Object> map = new HashMap<>();

        //获取文件内容
        StringBuilder sbStr = LoadConf.readConf(path);
        //正则匹配
        Pattern pattern = Pattern.compile("=\"(?<key>[^\"]+)\"[^\"]+\"(?<val>[^\"]*)");
        Matcher matcher = pattern.matcher(sbStr);
        while (matcher.find()){
            map.put(matcher.group("key"),matcher.group("val"));
        }
        return map;
    }
}
