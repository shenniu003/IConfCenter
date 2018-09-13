package com.cc;

import com.ct.tconf.LoadConf;
import com.ct.tconf.PropertiesConf;
import com.ct.tconf.YmlConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.cc", "com.ct"})
public class ConfserverApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConfserverApplication.class, args);

        String confBasePath = "D:\\my_study\\study_java\\IConfCenter\\ConfCenterServer\\ConfCenter\\confserver\\src\\main\\resources\\confs";
        String pathProperties = "D:\\my_study\\study_java\\IConfCenter\\ConfCenterServer\\ConfCenter\\confserver\\src\\main\\resources\\confs\\application-20180903.properties";
        String pathConfig = "D:\\my_study\\study_java\\IConfCenter\\ConfCenterServer\\ConfCenter\\confserver\\src\\main\\resources\\confs\\App.config";

//        LoadConf.dirFiles(confBasePath).forEach((key, val) -> {
//            System.out.println(key + "=" + val);
//        });

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
//            Map<String, Object> mapYml = YmlConf.readConfToHashMap(pathYml);
////            mapYml.forEach((key, val) -> {
////                System.out.println(key + "=" + val);
////            });
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
