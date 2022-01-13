package com.streaming.predictitstream.entities;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
