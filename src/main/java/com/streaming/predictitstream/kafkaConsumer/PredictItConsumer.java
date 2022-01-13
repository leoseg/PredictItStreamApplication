package com.streaming.predictitstream.kafkaConsumer;

import com.streaming.predictitstream.PredictItStreamApplication;
import com.streaming.predictitstream.entities.Contract;
import com.streaming.predictitstream.entities.PredictItTopic;
import com.streaming.predictitstream.services.candidateServices.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for consuming data from PredictIt
 */
@Component
public class PredictItConsumer {


    private final static Logger LOGGER = Logger.getLogger(PredictItStreamApplication.class.getName());

    @Autowired
    ContractService contractService;

    @Autowired
    @Qualifier("CollectionsBean")
    List<String> contractNames;


    /**
     * Kafka listener to listen to all topics beginning with president.* and saving the to the same table
     * @param record record of type predictittopic with the data
     */
    @KafkaListener(topicPattern= "president.*", groupId = "group-1", containerFactory = "kafkaListenerContainerFactory")
    public void listenPresidentTopics(PredictItTopic record) {
        LOGGER.log(Level.INFO, "Recieved message from " + record.getShortName()+ " at "+record.getTimeStamp());
        Timestamp timestamp = record.getTimeStamp();
        int counter = 0;
        for (Contract contract : record.getContracts()) {
            if (contractNames.contains(contract.getName()) || counter < 5) {
                contractService.saveContract(contract, timestamp);
            }
            counter++;

        }
    }
}
