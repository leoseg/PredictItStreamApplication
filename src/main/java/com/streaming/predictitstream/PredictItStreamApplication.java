package com.streaming.predictitstream;

import com.streaming.predictitstream.entities.Contract;
import com.streaming.predictitstream.entities.PredictItTopic;
import com.streaming.predictitstream.services.candidateServices.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class PredictItStreamApplication {

    private final static Logger LOGGER = Logger.getLogger(PredictItStreamApplication.class.getName());

    @Autowired
    ContractService contractService;

    @Autowired
    @Qualifier("CollectionsBean")
    List<String> contractNames;

    public static void main(String[] args) {
        LOGGER.setLevel(Level.INFO);
        SpringApplication.run(PredictItStreamApplication.class, args);
    }

    @KafkaListener(topicPattern= "president.*", groupId = "group-1", containerFactory = "kafkaListenerContainerFactory")
    public void listenPresidentItalia(PredictItTopic record) {
        LOGGER.log(Level.INFO,"Recieved message from "+record.getShortName());
        Timestamp timestamp= record.getTimeStamp();
        int counter =0;
        for (Contract contract : record.getContracts()){
            if (contractNames.contains(contract.getName()) || counter <5){
                contractService.saveContract(contract,timestamp);
            }
            counter++;

        }

    }


}
