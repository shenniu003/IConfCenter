package com.ct.tconf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/31.
 */
public class LoadConf {

    /**
     * 读取路径的文件内容
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

}
