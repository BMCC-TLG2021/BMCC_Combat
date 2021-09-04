package com.bmcc.model.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Equipment {

    private final String name;
    private int integrity;
    private final int maxIntegrity;
    private final String desc;
    private final int moneyValue;


    Equipment(@JsonProperty("name") String name,
              @JsonProperty("integrity") int integrity,
              @JsonProperty("maxIntegrity") int maxIntegrity,
              @JsonProperty("desc") String desc,
              @JsonProperty("moneyValue") int moneyValue) {
        this.name = name;
        this.integrity = integrity;
        this.maxIntegrity = maxIntegrity;
        this.desc = desc;
        this.moneyValue = moneyValue;
    }

    public String getName(){
        return this.name;
    };

    public int getIntegrity(){
        return this.integrity;
    };

    public int getMaxIntegrity() {
        return maxIntegrity;
    }

    public String getDesc(){
        return this.desc;
    }

    public int getMoneyValue() {
        return moneyValue;
    }

    public void reduceIntegrity(){
        if (this.getIntegrity() != 0){
            this.integrity -= 1;
        }
    }

    public void restoreIntegrity() {
        this.integrity = maxIntegrity;
    }

}
