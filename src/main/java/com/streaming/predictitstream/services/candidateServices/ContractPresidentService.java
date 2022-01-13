package com.streaming.predictitstream.services.candidateServices;
import com.streaming.predictitstream.entities.Contract;
import com.streaming.predictitstream.entities.ContractLog;
import com.streaming.predictitstream.repository.candidateRepositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ContractPresidentService implements ContractService {

    @Autowired
    private ContractRepository contractPresidentRepository;


    @Override
    public void saveContract(Contract contract, Timestamp timestamp) {
        ContractLog contractPresidentLog = new ContractLog();
        this.setContractLogData(contract, contractPresidentLog,timestamp);
        contractPresidentRepository.save(contractPresidentLog);
    }


    @Override
    public ContractLog setContractLogData(Contract contract, ContractLog contractPresidentLog, Timestamp timestamp) {
            contractPresidentLog.setCandiateId(contract.getId());
            contractPresidentLog.setLastTradePrice(contract.getLastTradePrice());
            contractPresidentLog.setTimeStamp(timestamp);
            contractPresidentLog.setBestBuyNoCost(contract.getBestBuyNoCost());
            contractPresidentLog.setBestBuyYesCost(contract.getBestBuyYesCost());
            return contractPresidentLog;
    }
}
