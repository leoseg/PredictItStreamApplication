package com.streaming.predictitstream.helpClasses;

import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.node.ArrayNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Class for building json data for testing
 */
public class JsonDataBuilder {

    ObjectNode jsonObject;
    ObjectMapper objectMapper;

    /**
     * Constructor that initalize the object mapper attribute
     */
    public JsonDataBuilder(){
       this.objectMapper = new ObjectMapper();
    }

    /**
     * Creates a Json in form of the predictit json with a list of contract and one custom contract with the name of
     * a ImportantContract name
     * @param numberContracts number of contracts to create
     */
    public void buildPredictItJson(int numberContracts,double lastTradePrice,String name,double bestBuyYesCost, double bestBuyNoCost){
        ObjectNode PredictItTopic = objectMapper.createObjectNode();
        PredictItTopic.put("shortName","testtopic");
        PredictItTopic.put("timeStamp", "2022-01-22T15:15:22");
        PredictItTopic.put("id",75660);
        ArrayNode contractArray = objectMapper.createArrayNode();
        for(int i=1; i<numberContracts;i++){
            contractArray.add(buildContractNode(i));
        }
        contractArray.add(buildCustomContractNode(numberContracts+1,lastTradePrice,name,bestBuyYesCost,bestBuyNoCost));
        PredictItTopic.putArray("contracts").addAll(contractArray);
        this.jsonObject = PredictItTopic;
    }

    /**
     * Builds a test contract
     * @param id id for test contract
     * @return the test contract object node
     */
    private ObjectNode buildContractNode(int id){
        ObjectNode contract = objectMapper.createObjectNode();
        contract.put("id",id);
        contract.put("lastTradePrice",0.4);
        contract.put("name","testcontract "+id);
        contract.put("bestBuyYesCost",0.2);
        contract.put("bestBuyYesCost",0.3);
        return contract;
    }

    /**
     * Builds a custom contract with given data
     * @param id id of contract
     * @param lastTradePrice lastTradePrice
     * @param name name of contract
     * @param bestBuyYesCost bestbuyyes cost
     * @param bestBuyNoCost bestbuyno cost
     * @return contract as ObjectNode
     */
    private ObjectNode buildCustomContractNode(int id,double lastTradePrice,String name,double bestBuyYesCost, double bestBuyNoCost ){
        ObjectNode contract = objectMapper.createObjectNode();
        contract.put("id",id);
        contract.put("lastTradePrice",lastTradePrice);
        contract.put("name",name);
        contract.put("bestBuyYesCost",bestBuyYesCost);
        contract.put("bestBuyYesCost",bestBuyNoCost);
        return contract;
    }
    /*
    Returns the json object build before as string
     */
    public String getJsonStringOfObject(){
        return this.jsonObject.toString();
    }
}
