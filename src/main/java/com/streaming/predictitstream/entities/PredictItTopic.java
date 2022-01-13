package com.streaming.predictitstream.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictItTopic implements Serializable {

    @Getter@Setter
    Timestamp timeStamp;

    @Getter@Setter
    List<Contract> contracts;

    @Getter@Setter
    String shortName;

    @Getter@Setter
    int id;

}