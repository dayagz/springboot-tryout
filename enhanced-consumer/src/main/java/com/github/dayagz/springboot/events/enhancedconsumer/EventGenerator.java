package com.github.dayagz.springboot.events.enhancedconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

@Component
@EnableScheduling
public class EventGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventGenerator.class);

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> future;

    Lock lock = new ReentrantLock();

    @Autowired
    KafkaTemplate kafkaTemplate;

    public void produceEvent() {

        lock.lock();
        try {
            if (future == null || future.isDone()) {
                future = executor.scheduleAtFixedRate(() -> {
                    IntStream.range(0, 1).forEach(i -> {
                        String event = String.format("test%d @ %s", i, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        if (i % 2 == 0)
                            kafkaTemplate.send("test_events", event);
                        else
                            kafkaTemplate.send("test_events_1", event);
                    });
                    LOGGER.info("Produced events");
                }, 1, 1, TimeUnit.SECONDS);
            } else {
                throw new IllegalStateException("Generator already running!");
            }
        } finally {
            lock.unlock();
        }
    }
}
