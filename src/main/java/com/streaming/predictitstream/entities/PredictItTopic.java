package com.streaming.predictitstream.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Whole payload gotten from PredictIt contains timestamp, id, name and list of contracts
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictItTopic implements Serializable {

    @Getter@Setter
    LocalDateTime timeStamp;

    @Getter@Setter
    List<Contract> contracts;

    @Getter@Setter
    String shortName;

    @Getter@Setter
    int id;

}
