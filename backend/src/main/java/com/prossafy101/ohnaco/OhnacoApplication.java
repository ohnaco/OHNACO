package com.prossafy101.ohnaco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OhnacoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OhnacoApplication.class, args);
    }

}
