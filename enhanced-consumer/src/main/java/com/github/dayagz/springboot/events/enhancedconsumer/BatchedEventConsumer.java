package com.github.dayagz.springboot.events.enhancedconsumer;

import com.datastax.driver.core.utils.UUIDs;
import com.github.dayagz.springboot.events.enhancedconsumer.entity.SimpleEvent;
import com.github.dayagz.springboot.events.enhancedconsumer.repository.SimpleEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static reactor.core.publisher.Flux.fromIterable;

@Service
public class BatchedEventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchedEventConsumer.class);

    @Autowired
    private SimpleEventRepository simpleEventRepository;


    @KafkaListener(topics = "#{'${event.topics}'.split('\\\\ ')}", groupId = "batched-listener")
    public void saveEvent(List<String> messages, Acknowledgment acknowledgment) {
        LOGGER.info("Received {} messages", messages.size());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Convert");


        List<SimpleEvent> events = messages.parallelStream().map(message -> new SimpleEvent()
                .eventId(UUIDs.timeBased().toString())
                .received_hour(LocalDateTime.now().getHour())
                .received_date(LocalDate.now().format(DateTimeFormatter.ISO_DATE))
                .received_timestamp(LocalDateTime.now())
                .message(message))
                .collect(Collectors.toList());

        simpleEventRepository.saveAll(fromIterable(events))
                .onErrorStop()
                .subscribe(null, e -> LOGGER.error("Error while saving events", e), () -> acknowledgment.acknowledge());

        stopWatch.stop();
        LOGGER.info("Processed {} in {} milliseconds", messages.size(), stopWatch.getLastTaskTimeMillis());
    }
}
