package com.github.dayagz.springboot.events.enhancedconsumer.entity;

import com.datastax.driver.core.DataType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Accessors(fluent = true)
@Table("simple_event")
public class SimpleEvent {

    @PrimaryKeyColumn(name="event_id",type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    @CassandraType(type = DataType.Name.TIMEUUID)
    private String eventId;

    @PrimaryKeyColumn(name="received_date", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
    @CassandraType(type= DataType.Name.TEXT)
    private String received_date;

    @PrimaryKeyColumn(name="received_hour", type = PrimaryKeyType.CLUSTERED, ordinal = 2)
    @CassandraType(type= DataType.Name.INT)
    private int received_hour;

    @Column("received_timestamp")
    private LocalDateTime received_timestamp;

    @Column
    private String message;
}
