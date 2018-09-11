package com.cc;

import com.ct.tconf.LoadConf;
import com.ct.tconf.PropertiesConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.cc", "com.ct"})
public class ConfserverApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConfserverApplication.class, args);

        String pathYml = "D:\\my_study\\study_java\\IConfCenter\\ConfCenterServer\\ConfCenter\\confserver\\src\\main\\resources\\confs\\application-active.yml";
        String pathProperties = "D:\\my_study\\study_java\\IConfCenter\\ConfCenterServer\\ConfCenter\\confserver\\src\\main\\resources\\confs\\application-20180903.properties";
        String pathConfig = "D:\\my_study\\study_java\\IConfCenter\\ConfCenterServer\\ConfCenter\\confserver\\src\\main\\resources\\confs\\App.config";

//        try {
//            LoadConf.readConfToMap(pathConfig).forEach((key, val) -> {
//                System.out.println(key + "=" + val);
//            });
//
////            LoadConf.readConfToMap(pathProperties).forEach((key, val) -> {
////                System.out.println(key + "=" + val);
////            });
//
////            PropertiesConf.readConfToMap(pathConfig).forEach((key, val) -> {
////
////                System.out.println(key + "=" + val);
////            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            LoadConf.readConf(pathYml);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //System.out.println(YmlConf.readConfToJson(pathYml));
//        try {
//            Map<String, Object> mapYml = YmlConf.readConfToHashMap(pathYml);
//            mapYml.forEach((key, val) -> {
//                System.out.println(key + "=" + val);
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("");
//        try {
//            Map<String, Object> mapProperties = PropertiesConf.readConfToMap(pathProperties);
//            mapProperties.forEach((key, val) -> {
//                System.out.println(key + "=" + val);
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
