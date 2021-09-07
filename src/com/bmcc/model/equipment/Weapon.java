package com.bmcc.model.equipment;

import com.bmcc.model.character.Character;
import com.bmcc.model.skill.Magic;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Weapon extends Equipment {

    private final int physicalDamage;

    private final double magicPowerIncrease;

    private Weapon(@JsonProperty("name") String name,
                   @JsonProperty("integrity") int integrity,
                   @JsonProperty("maxIntegrity") int maxIntegrity,
                   @JsonProperty("desc") String desc,
                   @JsonProperty("moneyValue") int moneyValue,
                   @JsonProperty("physicalDamage") int physicalDamage,
                   @JsonProperty("magicPowerIncrease") double magicPowerIncrease) {
        super(name, integrity, maxIntegrity, desc, moneyValue);
        this.physicalDamage = physicalDamage;
        this.magicPowerIncrease = magicPowerIncrease;
    }

    public double getMagicPowerIncrease() {
        return this.magicPowerIncrease;
    }

    public int getPhysicalDamage() {
        return this.physicalDamage;
    }

    public static Weapon getInstanceFromJson(String file) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        int integrity = (int) (long) jo.get("integrity");
        int maxIntegrity = (int) (long) jo.get("maxIntegrity");
        String desc = (String) jo.get("desc");
        int moneyValue = (int) (long) jo.get("moneyValue");
        int physicalDamage = (int) (long) jo.get("physicalDamage");
        double magicPowerIncrease = (double) jo.get("magicPowerIncrease");

        return new Weapon(name, integrity, maxIntegrity, desc, moneyValue, physicalDamage, magicPowerIncrease) {
        };
    }

    public static Weapon getInstance(String name, int integrity, int maxIntegrity, String desc, int moneyValue,
                                     int physicalDamage, double magicPowerIncrease) {
        return new Weapon(name, integrity, maxIntegrity, desc, moneyValue, physicalDamage, magicPowerIncrease) {
        };
    }

    public static List<Weapon> getWeaponListFromJsonFile(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Weapon> weaponList = new ArrayList<>(Arrays.asList(mapper.readValue(new File(filePath), Weapon[].class)));
        return weaponList;
    }
}
