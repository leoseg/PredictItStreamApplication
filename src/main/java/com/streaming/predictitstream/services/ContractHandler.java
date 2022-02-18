package com.streaming.predictitstream.services;
import com.streaming.predictitstream.entities.Contract;
import com.streaming.predictitstream.entities.ContractLog;
import com.streaming.predictitstream.repository.ContractRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class ContractHandler {

    @Resource
    private ContractRepository contractPresidentRepository;



    public void saveContract(Contract contract, LocalDateTime timestamp) {
        ContractLog contractPresidentLog = new ContractLog();
        this.setContractLogData(contract, contractPresidentLog,timestamp);
        contractPresidentRepository.save(contractPresidentLog);
    }


    public void setContractLogData(Contract contract, ContractLog contractPresidentLog, LocalDateTime timestamp) {
            contractPresidentLog.setCandidateId(contract.getId());
            contractPresidentLog.setLastTradePrice(contract.getLastTradePrice());

            contractPresidentLog.setTimeStamp(timestamp);
            contractPresidentLog.setBestBuyNoCost(contract.getBestBuyNoCost());
            contractPresidentLog.setBestBuyYesCost(contract.getBestBuyYesCost());
    }
}
