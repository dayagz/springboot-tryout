package com.github.dayagz.springboot.events.enhancedconsumer.repository;


import com.github.dayagz.springboot.events.enhancedconsumer.entity.SimpleEvent;
import com.github.dayagz.springboot.events.enhancedconsumer.entity.SimpleEventByDateAndHour;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface SimpleEventsByDateAndHourRepository extends ReactiveCassandraRepository<SimpleEventByDateAndHour, MapId> {

    Flux<SimpleEventByDateAndHour> findByReceivedDateAndReceivedHour(String receivedDate, int receivedHour);
}
