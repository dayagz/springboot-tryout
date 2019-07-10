package com.github.dayagz.springboot.events.enhancedconsumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

//@Service
public class EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventConsumer.class);

    int count = 0;

    @KafkaListener(topics = "#{'${event.topics}'.split('\\\\ ')}", groupId = "simple-listener")
    public void saveEvent(String message, Acknowledgment acknowledgment) {
        LOGGER.info(message);

        if (count % 2 == 0) {
            acknowledgment.acknowledge();
        }
        count++;
    }
}
