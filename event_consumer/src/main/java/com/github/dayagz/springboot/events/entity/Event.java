package com.github.dayagz.springboot.events.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

import static com.datastax.driver.core.DataType.Name.*;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Data
@Accessors(fluent = true) 
@Table("events_by_correlation_id")
public class Event {

    @PrimaryKeyColumn(name = "correlation_id", ordinal = 0, type = PARTITIONED)
    @CassandraType(type = TEXT)
    private String correlation_id;

    @PrimaryKeyColumn(name = "event_type", ordinal = 1, type = CLUSTERED)
    @CassandraType(type = TEXT)
    private String event_type;

    @PrimaryKeyColumn(name = "event_version", ordinal = 2, type = CLUSTERED)
    @CassandraType(type = TEXT)
    private String event_version;

    @PrimaryKeyColumn(name = "received_date", ordinal = 3, type = CLUSTERED)
    @CassandraType(type = TEXT)
    private String received_date;

    @PrimaryKeyColumn(name = "received_hour", ordinal = 4, type = CLUSTERED)
    @CassandraType(type = INT)
    private int received_hour;

    @Column
    @CassandraType(type = TEXT)
    private String application_id;

    @Column
    @CassandraType(type = TEXT)
    private String application_module;

    @Column
    @CassandraType(type = TEXT)
    private String event_payload_json;

    @Column
    @CassandraType(type = TIMESTAMP)
    private LocalDateTime received_timestamp;

    @Column
    @CassandraType(type = TEXT)
    private String spec_version;

}