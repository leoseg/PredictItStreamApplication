package com.streaming.predictitstream;

import com.streaming.predictitstream.services.ContractService;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PredictItStreamApplicationTests {

    @Autowired
    ContractService contractService;





    @Test
    void contextLoads() {
        assertNotNull(contractService);
    }

}
