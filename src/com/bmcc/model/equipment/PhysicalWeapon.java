package com.bmcc.model.equipment;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;


import com.bmcc.model.character.Character;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bmcc.model.skill.Magic;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class PhysicalWeapon extends Weapon {

    @JsonProperty("damage")
    private final int damage;

    @JsonCreator
    private PhysicalWeapon(@JsonProperty("name") String name, @JsonProperty("damage") int damage){
        super(name);
        this.damage = damage;

    private PhysicalWeapon(String name, int integrity, String desc, int physicalDamage,
                           double magicPowerIncrease){
        super(name,integrity,desc, physicalDamage,magicPowerIncrease);

    }

    public int getDamage(){
        return 0;
    }


    public static PhysicalWeapon getInstanceFromJson(String file) throws Exception {
//        Object obj = new JSONParser().parse(new FileReader(file));
//        JSONObject jo = (JSONObject) obj;
//        String name = (String) jo.get("name");
//        int damage = (int)(long) jo.get("damage");
//
//        return new PhysicalWeapon(name, damage);
        return null;
    }


    public static List<PhysicalWeapon> getPhysicalWeaponListFromJsonFile(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<PhysicalWeapon> physicalWeaponList = Arrays.asList(mapper.readValue(new File(fileName), PhysicalWeapon[].class));
            return physicalWeaponList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PhysicalWeapon getInstance(String name, int damage){
        return new PhysicalWeapon(name, damage);
    }

}
