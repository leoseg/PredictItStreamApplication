package com.streaming.predictitstream.entities;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Used for mapping the contracts to the database over time
 */
@Entity
public class ContractLog {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    int candidateId;

    @Getter@Setter
    double lastTradePrice;

    @Getter@Setter
    LocalDateTime timeStamp;


    @Getter@Setter
    double bestBuyYesCost;

    @Getter@Setter
    double bestBuyNoCost;


}
