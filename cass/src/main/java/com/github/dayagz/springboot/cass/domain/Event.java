package com.github.dayagz.springboot.cass.domain;

import static com.datastax.driver.core.DataType.Name.INT;
import static com.datastax.driver.core.DataType.Name.TEXT;
import static com.datastax.driver.core.DataType.Name.TIMESTAMP;
import static org.springframework.cassandra.core.PrimaryKeyType.CLUSTERED;
import static org.springframework.cassandra.core.PrimaryKeyType.PARTITIONED;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import lombok.Data;
import lombok.experimental.Accessors;

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