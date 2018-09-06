package com.confclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.confclient", "com.ccc","com.ct"})
public class Client01Application {

    public static void main(String[] args) {
        SpringApplication.run(Client01Application.class, args);

    }
}
