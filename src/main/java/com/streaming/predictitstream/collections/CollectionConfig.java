package com.streaming.predictitstream.collections;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CollectionConfig {

    @Bean
    @Qualifier("CollectionsBean")
    public List<String> ImportantContractNames(){
        return  Arrays.asList("Mario Draghi","Silvio Berlusconi","Viktor Orban" ,"Peter Marki-Zay","Emmanuel Macron" ,"Valerie Pecresse","Marine le Pen","Will Boris Johnson remain British prime minister through May?");
    }
}
