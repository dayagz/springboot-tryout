package com.github.dayagz.springboot.events.enhancedconsumer;

import com.datastax.driver.core.utils.UUIDs;
import com.github.dayagz.springboot.events.enhancedconsumer.entity.SimpleEvent;
import com.github.dayagz.springboot.events.enhancedconsumer.repository.SimpleEventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveEventRepoIntegrationTest {

    @Autowired
    SimpleEventRepository repository;

    @Before
    public void setUp() {

        int i = 0;
        Flux<SimpleEvent> deleteAndInsert = repository.saveAll(Flux.just(new SimpleEvent().eventId(UUIDs.timeBased().toString())
                .received_hour(LocalDateTime.now().getHour())
                .received_date(LocalDate.now().format(DateTimeFormatter.ISO_DATE))
                .received_timestamp(LocalDateTime.now())
                .message("Int-test1")));

        deleteAndInsert.subscribe(even -> System.out.println(even), e -> {
            e.printStackTrace();
            throw Exceptions.propagate(e);
        }, () -> System.out.println("***************************COMPLETED******************************"));
        //StepVerifier.create(deleteAndInsert).expectNextCount(1).verifyComplete();
    }

    @Test
    public void test() {

    }

}
