package com.github.dayagz.springboot.events.enhancedconsumer.repository;

import com.github.dayagz.springboot.events.enhancedconsumer.entity.SimpleEvent;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleEventRepository extends ReactiveCassandraRepository<SimpleEvent, MapId> {
}
