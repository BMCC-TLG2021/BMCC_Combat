package com.bmcc.model.equipment;

import java.io.FileReader;

import com.bmcc.model.skill.Magic;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class MagicalWeapon extends Weapon {



    private MagicalWeapon( String name, int integrity, String desc, int physicalDamage,
                           double magicPowerIncrease){
        super(name,integrity,desc, physicalDamage,magicPowerIncrease);
    }

    public double getMagicPowerIncrease(){return super.getMagicPowerIncrease();}

    public static MagicalWeapon getInstanceFromJson(String file) throws Exception {
//        Object obj = new JSONParser().parse(new FileReader(file));
//        JSONObject jo = (JSONObject) obj;
//        String name = (String) jo.get("name");
//        double magicPowerIncrease = (double) jo.get("magicPowerIncrease");
//
//        return new MagicalWeapon(name, magicPowerIncrease);
        return null;
    }

}
