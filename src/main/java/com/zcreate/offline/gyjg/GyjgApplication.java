package com.zcreate.offline.gyjg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GyjgApplication {

    public static void main(String[] args) {
        SpringApplication.run(GyjgApplication.class, args);
    }

}

