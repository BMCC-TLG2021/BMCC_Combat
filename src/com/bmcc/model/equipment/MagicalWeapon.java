package com.bmcc.model.equipment;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class MagicalWeapon extends Weapon {
    @JsonProperty("magicPowerIncrease")
    private final double magicPowerIncrease;

    @JsonCreator
    private MagicalWeapon(@JsonProperty("name") String name, @JsonProperty("magicPowerIncrease") double magicPowerIncrease){
        super(name);
        this.magicPowerIncrease = magicPowerIncrease;
    }

    public double getMagicPowerIncrease(){return this.magicPowerIncrease;}

    public static MagicalWeapon getInstanceFromJson(String file) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        double magicPowerIncrease = (double) jo.get("magicPowerIncrease");

        return new MagicalWeapon(name, magicPowerIncrease);
    }

    public static List<MagicalWeapon> getMagicalWeaponListFromJsonFile(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<MagicalWeapon> magicalWeaponList = Arrays.asList(mapper.readValue(new File(fileName), MagicalWeapon[].class));
            return magicalWeaponList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MagicalWeapon getInstance(String name, double magicPowerIncrease){
        return new MagicalWeapon(name, magicPowerIncrease);
    }

}
