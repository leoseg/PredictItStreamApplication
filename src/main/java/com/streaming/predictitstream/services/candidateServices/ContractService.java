package com.streaming.predictitstream.services.candidateServices;

import com.streaming.predictitstream.entities.Contract;
import com.streaming.predictitstream.entities.ContractLog;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public interface ContractService {

    void saveContract(Contract contract, Timestamp timestamp);

    ContractLog setContractLogData(Contract contract, ContractLog ContractLog, Timestamp timestamp);

}
