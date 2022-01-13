package com.streaming.predictitstream.entities;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Single contract entry, Used for deserializing the payload from PredictIt api
 * each contract can be bought or sold like a share
 */
public class Contract implements Serializable {

    @Getter@Setter
    int id;

    @Getter@Setter
    String name;

    @Getter@Setter
    double lastTradePrice;

    @Getter@Setter
    double bestBuyYesCost;

    @Getter@Setter
    double bestBuyNoCost;


}
