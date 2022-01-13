package com.streaming.predictitstream.kafkaConsumerTests;

import com.streaming.predictitstream.kafkaConsumer.PredictItConsumer;
import com.streaming.predictitstream.services.candidateServices.ContractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

//
//@SpringBootTest
//@DirtiesContext
//@EmbeddedKafka(partitions = 1 ,bootstrapServersProperty = "localhost:9092"
//        ,brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" }
//        ,topics={"president.test"})
//public class PredictItConsumerTests {
//
//    @MockBean
//    ContractService contractService;
//
//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;
//
//    @Autowired
//    private PredictItConsumer predictItConsumer;
//
//    @Test
//    public void testListenPresidentTopics() throws Exception{
//        kafkaTemplate.send("president.test","somemssage");
//
//    }
//
//
//}
