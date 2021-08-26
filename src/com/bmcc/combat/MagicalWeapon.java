package com.bmcc.combat;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class MagicalWeapon {

    private final String name;
    private final double magicPowerIncrease;

    private MagicalWeapon(String name, double magicPowerIncrease){
        this.name = name;
        this.magicPowerIncrease = magicPowerIncrease;
    }

    public String getName(){return this.name;}

    public double getMagicPowerIncrease(){return this.magicPowerIncrease;}

    public static MagicalWeapon getInstanceFromJson(String file) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        double magicPowerIncrease = (double) jo.get("magicPowerIncrease");

        return new MagicalWeapon(name, magicPowerIncrease);
    }

    public static MagicalWeapon getInstance(String name, double magicPowerIncrease){
        return new MagicalWeapon(name, magicPowerIncrease);
    }

}
