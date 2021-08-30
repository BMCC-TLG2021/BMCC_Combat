package com.bmcc.model.equipment;

import com.bmcc.model.character.Character;
import com.bmcc.model.skill.Magic;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Weapon extends Equipment{

    private final int physicalDamage;

    private final double magicPowerIncrease;

    private Weapon(@JsonProperty("name") String name,
                  @JsonProperty("integrity") int integrity,
                  @JsonProperty("desc") String desc,
                  @JsonProperty("physicalDamage") int physicalDamage,
                  @JsonProperty("magicPowerIncrease") double magicPowerIncrease){
        super(name,integrity,desc);
        this.physicalDamage = physicalDamage;
        this.magicPowerIncrease = magicPowerIncrease;
    }


    public double getMagicPowerIncrease(){
        return this.magicPowerIncrease;
    }

    public int getPhysicalDamage(){
        return this.physicalDamage;
    };


    public static Weapon getInstanceFromJson(String file) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        int integrity = (int)(long) jo.get("integrity");
        String desc = (String) jo.get("desc");
        int physicalDamage = (int)(long) jo.get("physicalDamage");
        double magicPowerIncrease = (double) jo.get("magicPowerIncrease");

        return new Weapon(name,integrity,desc, physicalDamage, magicPowerIncrease) {
        };
    }

    public static Weapon getInstance(String name, int integrity, String desc,
                                     int physicalDamage, double magicPowerIncrease){
        return new Weapon(name,integrity,desc, physicalDamage, magicPowerIncrease) {
        };
    }

    public static List<Weapon> getWeaponListFromJsonFile(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(new File(filePath), Weapon[].class));
    }
}
