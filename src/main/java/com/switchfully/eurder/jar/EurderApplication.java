package com.switchfully.eurder.jar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.switchfully.eurder"})
public class EurderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurderApplication.class, args);
    }

}
