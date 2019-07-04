package com.github.dayagz.springboot.events.consumer;

import com.github.dayagz.springboot.events.entity.Event;
import com.github.dayagz.springboot.events.repository.EventRepository;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;


@Service
public class EventConsumer {
    private final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

    @Autowired
    private EventRepository eventRepository;

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;    }

    @KafkaListener(topics = "test")
    public void consume(GenericRecord consumerRecord) {
        logger.info(String.format("#### -> Consumed message -> %s", consumerRecord));

        String correlation_id = String.valueOf(consumerRecord.get("correlation_id"));
        String event_type = String.valueOf(consumerRecord.get("event_type"));
        String event_version = String.valueOf(consumerRecord.get("event_version"));
        String application_id = String.valueOf(consumerRecord.get("application_id"));
        String application_module = String.valueOf(consumerRecord.get("application_module"));
        String spec_version = String.valueOf(consumerRecord.get("spec_version"));
        String payload_json = consumerRecord.toString();
        String received_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Integer received_hour = Integer.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")));
        LocalDateTime received_timestamp = LocalDateTime.now();


        Event event = new Event().correlation_id(correlation_id).event_type(event_type).event_version(event_version)
                .received_date(received_date)
                .received_hour(received_hour)
                .received_timestamp(received_timestamp)
                .application_id(application_id)
                .application_module(application_module)
                .event_payload_json(payload_json)
                .spec_version(spec_version);

        eventRepository.save(event);

        latch.countDown();


    }
}
