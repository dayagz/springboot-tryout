package com.github.dayagz.springboot.cass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.github.dayagz.springboot.cass.domain.Event;
import com.github.dayagz.springboot.cass.repository.EventRepository;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.cassandra.service.CassandraDaemon;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventRepositoryTest.class);


    public static final String KEYSPACE_CREATION_QUERY = "CREATE KEYSPACE IF NOT EXISTS testEvents" +
            " WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': '1' };";
    public static final String KEYSPACE_ACTIVATE_QUERY = "USE testEvents;";
    public static final String EVENTS_TABLE_NAME = "events_by_correlation_id";

    @Autowired
    private EventRepository eventRepository;

    private static CassandraAdminOperations adminTemplate;

    @Autowired
    private CassandraTemplate cassandraTemplate;


    @BeforeClass
    public static void startCassandraEmbedded()
            throws ConfigurationException, TTransportException, IOException, InterruptedException {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        final Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9142).build();
        System.out.println("Server Started at 127.0.0.1:9142... ");
        LOGGER.info("Server Started at 127.0.0.1:9142... ");
        final Session session = cluster.connect();
        session.execute(KEYSPACE_CREATION_QUERY);
        session.execute(KEYSPACE_ACTIVATE_QUERY);
        adminTemplate = new CassandraAdminTemplate(session, new MappingCassandraConverter());
        LOGGER.info("KeySpace created and activated.");
        Thread.sleep(5000);
    }

    @Before
    public void createTable() throws ConfigurationException {
        System.out.println("Creating Table!");
        adminTemplate.createTable(true, CqlIdentifier.cqlId(EVENTS_TABLE_NAME), Event.class,
                new HashMap<>());
        System.out.println("Table created!");
    }

    @Test
    public void shouldSaveTheEvent() {

        // given
        String correlation_id = UUID.randomUUID().toString();
        String event_type = "Test";
        String event_version = "v1.0";
        String received_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Integer received_hour = Integer.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")));
        LocalDateTime received_timestamp = LocalDateTime.now();
        String application_id = "TEST_APP";
        String application_module = "TEST_MODULE";
        String payload_json = "{key:value}";
        String spec_version = "V2.0";


        Event event = new Event().correlation_id(correlation_id).event_type(event_type).event_version(event_version)
                .received_date(received_date)
                .received_hour(received_hour)
                .received_timestamp(received_timestamp)
                .application_id(application_id)
                .application_module(application_module)
                .event_payload_json(payload_json)
                .spec_version(spec_version);

        // when
        eventRepository.save(event);

        // then
        Select query = QueryBuilder.select()
                .from(EVENTS_TABLE_NAME)
                .where(QueryBuilder.eq("correlation_id",correlation_id))
                .limit(1);

        final Event saved_event = cassandraTemplate.selectOne(query, Event.class);

        assertNotNull(saved_event);
        assertEquals(saved_event,event);

    }

    @After
    public void dropTable() {
        adminTemplate.dropTable(CqlIdentifier.cqlId(EVENTS_TABLE_NAME));
    }

    @AfterClass
    public static void stopCassandraEmbedded() {
        //
        Class<?> klass = EmbeddedCassandraServerHelper.class;
        try {
            java.lang.reflect.Field field = klass.getDeclaredField("cassandraDaemon");
            field.setAccessible(true);
            CassandraDaemon cassandraDaemon = (CassandraDaemon) field.get(CassandraDaemon.class);
            cassandraDaemon.stop();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.error("Error while stopping embedded cassandra!", e);
        }
    }

}