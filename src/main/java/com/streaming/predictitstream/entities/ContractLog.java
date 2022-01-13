package com.streaming.predictitstream.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

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
    int candiateId;

    @Getter@Setter
    double lastTradePrice;

    @Getter@Setter
    Timestamp timeStamp;


    @Getter@Setter
    double bestBuyYesCost;

    @Getter@Setter
    double bestBuyNoCost;


}
