package com.hackathon.recipientclassification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RecipientClassificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipientClassificationApplication.class, args);
    }

}
