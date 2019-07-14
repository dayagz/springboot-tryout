package com.github.dayagz.springboot.events.enhancedconsumer;

import com.datastax.driver.core.utils.UUIDs;
import com.github.dayagz.springboot.events.enhancedconsumer.entity.SimpleEvent;
import com.github.dayagz.springboot.events.enhancedconsumer.repository.SimpleEventRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static reactor.core.publisher.Flux.fromIterable;

@Service
public class BatchedEventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchedEventConsumer.class);

    @Autowired
    private SimpleEventRepository simpleEventRepository;

    @Autowired
    private KieContainer kieContainer;


    @KafkaListener(topics = "#{'${event.topics}'.split('\\\\ ')}", groupId = "batched-listener")
    public void saveEvent(List<Message<String>> messages, Acknowledgment acknowledgment) {
        LOGGER.info("Received {} messages", messages.size());
        StopWatch stopWatch = new StopWatch("Event Processor");
        stopWatch.start("Convert");

        List<SimpleEvent> events = messages.parallelStream().map(message -> new SimpleEvent()
                .setEventId(UUIDs.timeBased().toString())
                .setReceived_hour(LocalDateTime.now().getHour())
                .setReceived_date(LocalDate.now().format(DateTimeFormatter.ISO_DATE))
                .setReceived_timestamp(LocalDateTime.now())
                .setMessage(message.getPayload())
                .setEventSource(String.valueOf(message.getHeaders().get(KafkaHeaders.RECEIVED_TOPIC))))
                .collect(toList());
        stopWatch.stop();

        stopWatch.start("Determine Event Type");
        determineEventType(events);
        stopWatch.stop();

        stopWatch.start("Save");
        simpleEventRepository.saveAll(fromIterable(events))
                .onErrorStop()
                .subscribe(null, e -> LOGGER.error("Error while saving events", e), () -> acknowledgment.acknowledge());
        stopWatch.stop();


        LOGGER.info("Processed {} events in {} ms", messages.size(), stopWatch.getTotalTimeMillis());
        LOGGER.info(stopWatch.prettyPrint());

    }

    private void determineEventType(List<SimpleEvent> events) {
        StatelessKieSession ksession = kieContainer.newStatelessKieSession();
        ksession.execute(events);
    }
}
