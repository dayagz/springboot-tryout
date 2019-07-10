package com.github.dayagz.springboot.events.enhancedconsumer;

import org.springframework.data.cassandra.core.mapping.MapId;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;
import static org.springframework.data.cassandra.core.mapping.BasicMapId.id;

public class Test {

    public static void main(String[] args) {
        Stream<MapId> ids = range(0, 5)
                .mapToObj(i -> LocalDate.now().minusDays(i).format(DateTimeFormatter.ISO_DATE))
                .map(day -> range(0, 24).mapToObj(i -> id().with("receivedDate", day).with("receivedHour", i)).collect(Collectors.toList()))
                .flatMap(mapIds -> mapIds.stream());


        Flux.fromStream(ids)
                .subscribe(System.out::println);
    }
}
