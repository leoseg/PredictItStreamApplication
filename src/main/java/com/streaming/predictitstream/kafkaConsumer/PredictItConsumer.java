package com.streaming.predictitstream.kafkaConsumer;

import com.streaming.predictitstream.PredictItStreamApplication;
import com.streaming.predictitstream.entities.Contract;
import com.streaming.predictitstream.entities.PredictItTopic;
import com.streaming.predictitstream.services.candidateServices.ContractService;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for consuming data from PredictIt
 */
@Component
public class PredictItConsumer {


    private final static Logger LOGGER = Logger.getLogger(PredictItStreamApplication.class.getName());

    @Resource
    ContractService contractService;

    @Resource
    @Qualifier("CollectionsBean")
    List<String> contractNames;


    /**
     * Method for consuming all topics beginning with president.* and saving it to the to the same table
     * @param record record of type predictittopic with the data
     */
    @KafkaListener(topicPattern= "president.*", groupId = "group-1", containerFactory = "kafkaListenerContainerFactory")
    public void listenPresidentTopics(PredictItTopic record) {
        LOGGER.log(Level.INFO, "Recieved message from " + record.getShortName()+ " at "+record.getTimeStamp());
        LocalDateTime timestamp = record.getTimeStamp();
        int counter = 0;
        for (Contract contract : record.getContracts()) {
            if (contractNames.contains(contract.getName()) || counter < 5) {
                contractService.saveContract(contract, timestamp);
            }
            counter++;

        }
    }
}
