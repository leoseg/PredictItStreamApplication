package com.streaming.predictitstream.servicesTests;


import com.streaming.predictitstream.entities.Contract;
import com.streaming.predictitstream.entities.ContractLog;
import com.streaming.predictitstream.repository.candidateRepositories.ContractRepository;
import com.streaming.predictitstream.services.candidateServices.ContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContractServiceTests {


    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ContractService contractService;

    Contract testcontract;

    @BeforeEach
    void setTextContract(){
        testcontract = new Contract();
        testcontract.setBestBuyYesCost(0.8);
        testcontract.setLastTradePrice(0.5);
        testcontract.setBestBuyNoCost(0.1);
        testcontract.setId(25666);
        testcontract.setName("testcontract");
    }

    @Test
    void testContractLogCreation(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        contractService.saveContract(testcontract,timestamp);
        List<ContractLog> contractLogList = contractRepository.findAll();
        ContractLog exspectedContractLog = contractLogList.get(0);


        assert(exspectedContractLog.getLastTradePrice()==0.5);
        assert(exspectedContractLog.getBestBuyYesCost()==0.8);
        assert(exspectedContractLog.getBestBuyNoCost()==0.1);
        assert(exspectedContractLog.getCandiateId()==25666);

    }
}
