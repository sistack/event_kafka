package com.cfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication(exclude={ApplicationAvailabilityAutoConfiguration.class})
public class WorkerApp {

    public static void main(String[] args) {

        SpringApplication.run(WorkerApp.class, args);
    }
}
