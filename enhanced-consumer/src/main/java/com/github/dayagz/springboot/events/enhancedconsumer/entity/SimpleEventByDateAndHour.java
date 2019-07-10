package com.github.dayagz.springboot.events.enhancedconsumer.entity;

import com.datastax.driver.core.DataType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalDateTime;

@Data
@Accessors(fluent = true)
@Table("simple_events_by_hour")
public class SimpleEventByDateAndHour {

    @PrimaryKeyColumn(name = "received_date", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    @CassandraType(type = DataType.Name.TEXT)
    private String receivedDate;

    @PrimaryKeyColumn(name = "received_hour", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
    @CassandraType(type = DataType.Name.INT)
    private int receivedHour;

    @PrimaryKeyColumn(name="event_id",type = PrimaryKeyType.CLUSTERED, ordinal = 2)
    @CassandraType(type = DataType.Name.TIMEUUID)
    private String eventId;
    @Column
    private String message;

    @Column("received_timestamp")
    private LocalDateTime received_timestamp;
}
