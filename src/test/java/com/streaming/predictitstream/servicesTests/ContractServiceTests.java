package com.streaming.predictitstream.servicesTests;


import com.streaming.predictitstream.entities.Contract;
import com.streaming.predictitstream.entities.ContractLog;
import com.streaming.predictitstream.repository.ContractRepository;
import com.streaming.predictitstream.services.ContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContractServiceTests {


    @Resource
    ContractRepository contractRepository;

    @Resource
    ContractService contractService;

    Contract testcontract;

    @BeforeEach
    void setTestContract(){
        testcontract = new Contract();
        testcontract.setBestBuyYesCost(0.8);
        testcontract.setLastTradePrice(0.5);
        testcontract.setBestBuyNoCost(0.1);
        testcontract.setId(25666);
        testcontract.setName("testcontract");
    }

    @Test
    void givenContract_whenContractServiceSaveContract_thenContractFoundByRepositoryShouldHaveExspectedData(){
        LocalDateTime timestamp = LocalDateTime.now();
        contractService.saveContract(testcontract,timestamp);
        List<ContractLog> contractLogList = contractRepository.findAll();
        ContractLog actualContract = contractLogList.get(0);


        assert(actualContract.getLastTradePrice()==0.5);
        assert(actualContract.getBestBuyYesCost()==0.8);
        assert(actualContract.getBestBuyNoCost()==0.1);
        assert(actualContract.getCandidateId()==25666);

    }
}
