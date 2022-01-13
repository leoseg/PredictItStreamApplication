package com.streaming.predictitstream.repository.candidateRepositories;

import com.streaming.predictitstream.entities.ContractLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<ContractLog,Long> {
}
