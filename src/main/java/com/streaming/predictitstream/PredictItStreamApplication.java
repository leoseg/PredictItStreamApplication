package com.streaming.predictitstream;
import com.streaming.predictitstream.kafkaConsumer.PredictItConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class PredictItStreamApplication {

    private final static Logger LOGGER = Logger.getLogger(PredictItStreamApplication.class.getName());


    @Resource
    PredictItConsumer predictItConsumer;

    public static void main(String[] args) {
        LOGGER.setLevel(Level.INFO);
        SpringApplication.run(PredictItStreamApplication.class, args);
    }



}



