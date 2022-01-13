package com.streaming.predictitstream.collections;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.List;

@Configuration
public class CollectionConfig {

    /**
     * Names of the contracts that should be stored from the PredictIt api
     * @return list with names
     */
    @Bean
    @Qualifier("CollectionsBean")
    public List<String> ImportantContractNames(){
        return  Arrays.asList("Mario Draghi","Silvio Berlusconi","Viktor Orbán" ,"Péter Márki-Zay","Emmanuel Macron" ,"Válerie Pécresse","Marine Le Pen","Will Boris Johnson remain British prime minister through May?","Boris Johnson");
    }


}
