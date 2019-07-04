package com.github.dayagz.springboot.events.repository;

import com.github.dayagz.springboot.events.entity.Event;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CassandraRepository<Event, MapId> {

}