package com.github.dayagz.springboot.cass.repository;
import com.github.dayagz.springboot.cass.domain.Event;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CassandraRepository<Event>{

}