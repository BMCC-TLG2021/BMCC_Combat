package com.bmcc.model.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Armor extends Equipment{
    private final int defenceIncrease;

    private Armor(@JsonProperty("name") String name,
                  @JsonProperty("integrity") int integrity,
                  @JsonProperty("integrity") int maxIntegrity,
                  @JsonProperty("desc") String desc,
                  @JsonProperty("moneyValue") int moneyValue,
                  @JsonProperty("defenceIncrease") int defenceIncrease) {
        super(name, integrity, maxIntegrity, desc, moneyValue);
        this.defenceIncrease = defenceIncrease;
    }

    public int getDefenceIncrease(){
        return this.defenceIncrease;
    }

    public Armor getInstance(String name, int integrity, int maxIntegrity, String desc, int moneyValue, int defenceIncrease) {
        return new Armor(name, integrity, maxIntegrity, desc, moneyValue, defenceIncrease);
    }


    public Equipment getInstanceFromJson(String filePath) {
        return null;
    }


    public static List<Armor> getArmorListFromJsonFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(new File(filePath), Armor[].class));
    }

}
