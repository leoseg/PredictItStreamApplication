package com.streaming.predictitstream.services;
import com.streaming.predictitstream.entities.Contract;
import com.streaming.predictitstream.entities.ContractLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service for saving contracts
 */
@Service
public interface ContractService {

    /**
     * save contract as contractlog with given timestamp in database
     * @param contract contract to save
     * @param timestamp timestamp used for saving
     */
    void saveContract(Contract contract, LocalDateTime timestamp);

    /**
     * Sets the data for the contractlog
     * @param contract contract to get data from
     * @param ContractLog contract thats get setted
     * @param timestamp timestamp for contractlog
     */
    void setContractLogData(Contract contract, ContractLog ContractLog, LocalDateTime timestamp);

}
