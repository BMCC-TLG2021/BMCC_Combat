package com.bmcc.combat;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class PhysicalWeapon extends Weapon{
    private final double damage;

    private PhysicalWeapon(String name, double damage){
        super(name);
        this.damage = damage;
    }

    public double getDamage(){return this.damage;}

    public static PhysicalWeapon getInstanceFromJson(String file) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        double damage = (double) jo.get("damage");

        return new PhysicalWeapon(name, damage);
    }

    public static PhysicalWeapon getInstance(String name, double damage){
        return new PhysicalWeapon(name, damage);
    }

}
