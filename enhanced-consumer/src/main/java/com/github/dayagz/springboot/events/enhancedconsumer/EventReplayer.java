package com.github.dayagz.springboot.events.enhancedconsumer;

import com.github.dayagz.springboot.events.enhancedconsumer.repository.SimpleEventsByDateAndHourRepository;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

@Component
public class EventReplayer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventReplayer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private SimpleEventsByDateAndHourRepository repository;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private Future<?> future;

    Lock lock = new ReentrantLock();

    public void replayEvents() {

        lock.lock();
        try {
            if (future == null || future.isDone()) {
                future = executor.submit(() -> {
                    LOGGER.info("Replaying events");
                    List<EventId> ids = range(0, 1)
                            .mapToObj(i -> LocalDate.now().minusDays(i).format(DateTimeFormatter.ISO_DATE))
                            .map(day -> range(0, 24).mapToObj(i -> new EventId().receivedDate(day).receivedHour(i)).collect(Collectors.toList()))
                            .flatMap(mapIds -> mapIds.stream()).collect(Collectors.toList());

                    Flux.fromIterable(ids)
                            .flatMap(eventId -> repository.findByReceivedDateAndReceivedHour(eventId.receivedDate, eventId.receivedHour))
                            .subscribe(event ->
                                    kafkaTemplate.send("events_replay", event.message()), e -> e.printStackTrace(), () -> LOGGER.info("******************DONE********************"));
                });
            } else {
                throw new IllegalStateException("Replayer already running!");
            }
        } finally {
            lock.unlock();
        }
    }

    @Data
    @Accessors(fluent = true)
    static class EventId {
        private String receivedDate;
        private int receivedHour;


    }
}
