package com.streaming.predictitstream.kafkaConsumerTests;

import com.streaming.predictitstream.entities.Contract;
import com.streaming.predictitstream.helpClasses.JsonDataBuilder;
import com.streaming.predictitstream.kafkaConsumer.PredictItConsumer;
import com.streaming.predictitstream.services.candidateServices.ContractService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@DirtiesContext
@RunWith(SpringRunner.class)
@EmbeddedKafka(partitions = 1 ,bootstrapServersProperty = "localhost:9092"
        ,brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" }
        ,topics={"president.test"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = { "spring.kafka.bootstrap-servers=localhost:9092","spring.kafka.consumer.auto-offset-reset=earliest" })
public class PredictItConsumerTests {

    @MockBean
    ContractService contractService;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @BeforeAll
    public void setUp()  {
        for (final MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer,
                    embeddedKafkaBroker.getTopics().size() * embeddedKafkaBroker.getPartitionsPerTopic());
        }
    }

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private PredictItConsumer predictItConsumer;




    /**
     * Should save 6 times a contract, because maximum of 5 contracts to save or an important name is in it so 6
     * @throws Exception thrown because of thread sleep method
     */
    @Test
    public void testListenPresidentTopicsMoreThanFiveTopics() throws InterruptedException {
        JsonDataBuilder jsonDataBuilder = new JsonDataBuilder();
        jsonDataBuilder.buildPredictItJson(9,0.5,"Mario Draghi",0.2,0.3);
        kafkaTemplate.send("president.test",jsonDataBuilder.getJsonStringOfObject());
        Thread.sleep(10000);
        verify(contractService,times(6)).saveContract(any(),any());
    }


}
