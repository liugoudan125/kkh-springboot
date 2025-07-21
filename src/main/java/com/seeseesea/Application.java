package com.seeseesea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        SpringApplication.run(Application.class, args);
    }
}
