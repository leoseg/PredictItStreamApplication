package com.streaming.predictitstream;

import com.streaming.predictitstream.services.ContractHandler;
import com.streaming.predictitstream.kafkaConsumer.PredictItConsumer;
import com.streaming.predictitstream.repository.ContractRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
class PredictItStreamApplicationTests {

    @Resource
    ContractHandler contractHandler;


    @Resource
    ContractRepository contractRepository;

    @Resource
    PredictItConsumer predictItConsumer;


    @Test
    void contextLoads() {
        Assertions.assertNotNull(contractRepository);
        Assertions.assertNotNull(predictItConsumer);
        Assertions.assertNotNull(contractHandler);
    }

}
