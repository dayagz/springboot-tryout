package com.github.dayagz.springboot.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EventConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventConsumerApplication.class, args);
    }

}
