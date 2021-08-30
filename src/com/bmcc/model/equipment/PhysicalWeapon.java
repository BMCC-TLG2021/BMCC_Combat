package com.bmcc.model.equipment;

import java.io.FileReader;

import com.bmcc.model.skill.Magic;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class PhysicalWeapon extends Weapon {

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


}
