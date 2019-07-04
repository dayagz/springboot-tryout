package com.github.dayagz.springboot.events;


import com.github.dayagz.springboot.events.consumer.EventConsumer;
import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import io.confluent.kafka.serializers.AvroSchemaUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka(topics = {"test"})
public class EventConsumerIntegrationTest {


    @Autowired
    EventConsumer receiver;

    @Autowired
    private KafkaTemplate<String, GenericEvent> kafkaTemplate;

    @Autowired
    private MockSchemaRegistryClient mockSchemaRegistryClient;

    @Autowired
    private KafkaProperties props;

    @Test
    public void shouldConsume() throws InterruptedException, IOException, RestClientException {


        GenericEvent event = GenericEvent.newBuilder()
                .setApplicationId("TEST")
                .setApplicationModule("TESTM")
                .setCorrelationId(UUID.randomUUID().toString())
                .setEventType("GENERIC_EVENT")
                .build();

        mockSchemaRegistryClient.register("test-value", AvroSchemaUtils.getSchema(event));


        ListenableFuture<SendResult<String, GenericEvent>> result = kafkaTemplate.send("test", "test", event);
        result.addCallback(new ListenableFutureCallback<SendResult<String, GenericEvent>>() {
            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
                System.out.println(throwable);
            }

            @Override
            public void onSuccess(SendResult<String, GenericEvent> stringGenericEventSendResult) {
                System.out.println(stringGenericEventSendResult);
            }
        });

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat("Test", receiver.getLatch().getCount() == 0);
    }


}
