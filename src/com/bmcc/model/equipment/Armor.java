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
                  @JsonProperty("desc") String desc,
                  @JsonProperty("defenceIncrease") int defenceIncrease) {
        super(name, integrity, desc);
        this.defenceIncrease = defenceIncrease;
    }

    public int getDefenceIncrease(){
        return this.defenceIncrease;
    }

    public Armor getInstance(String name, int integrity, String desc, int defenceIncrease) {
        return new Armor(name, integrity, desc, defenceIncrease);
    }


    public Equipment getInstanceFromJson(String filePath) {
        return null;
    }


    public static List<Armor> getArmorListFromJsonFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(new File(filePath), Armor[].class));
    }

}
