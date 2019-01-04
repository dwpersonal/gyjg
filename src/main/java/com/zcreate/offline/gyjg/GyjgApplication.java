package com.zcreate.offline.gyjg;

import com.zcreate.offline.gyjg.schedule.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GyjgApplication {

    public static void main(String[] args) {
        SpringApplication.run(GyjgApplication.class, args);
    }
}

