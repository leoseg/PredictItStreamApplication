package com.streaming.predictitstream.kafkaConsumerTests;

import com.streaming.predictitstream.helpClasses.JsonDataBuilder;
import com.streaming.predictitstream.kafkaConsumer.PredictItConsumer;
import com.streaming.predictitstream.services.ContractService;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@DirtiesContext
@RunWith(SpringRunner.class)
@EmbeddedKafka(partitions = 1,bootstrapServersProperty = "localhost:9092"
        ,brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092", "num-partitions=1" }
        ,topics={"president.test"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PredictItConsumerTests {

    @MockBean
    ContractService contractService;

    @Resource
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Resource
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @BeforeAll
    public void setUp()  {
        for (final MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer,
                    1);
        }
    }

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    @Resource
    private PredictItConsumer predictItConsumer;




    /**
     * Should save 6 times a contract, because maximum of 5 contracts to save or an important name is in it so 6
     * @throws InterruptedException thrown because of thread sleep method
     */
    @Test
    public void givenNineContractsProducedWithOneImportantContract_whenKafaListenerIsActive_thenSixContractsShouldBeSaved() throws InterruptedException, ExecutionException {
        JsonDataBuilder jsonDataBuilder = new JsonDataBuilder();
        jsonDataBuilder.buildPredictItJson(9,0.5,"Mario Draghi",0.2,0.3);
        kafkaTemplate.send("president.test",jsonDataBuilder.getJsonStringOfObject());
        Thread.sleep(5000);
        verify(contractService,times(6)).saveContract(any(), eq(LocalDateTime.parse("2022-01-22T15:15:22.00000")));
    }

    @Test
    public void givenThreeContractsProduced_whenKafaListenerIsActive_thenThreeContractsShouldBeSaved() throws InterruptedException, ExecutionException {
        JsonDataBuilder jsonDataBuilder = new JsonDataBuilder();
        jsonDataBuilder.buildPredictItJson(3,0.5,"Mario Draghi",0.2,0.3);
        kafkaTemplate.send("president.test",jsonDataBuilder.getJsonStringOfObject());
        Thread.sleep(5000);
        verify(contractService,times(3)).saveContract(any(),eq(LocalDateTime.parse("2022-01-22T15:15:22.00000")));
    }


}
