package com.bmcc.combat;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class PhysicalWeapon extends Weapon{
    private final int damage;

    private PhysicalWeapon(String name, int damage){
        super(name);
        this.damage = damage;
    }

    public int getDamage(){return this.damage;}

    public static PhysicalWeapon getInstanceFromJson(String file) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        int damage = (int)(long) jo.get("damage");

        return new PhysicalWeapon(name, damage);
    }

    public static PhysicalWeapon getInstance(String name, int damage){
        return new PhysicalWeapon(name, damage);
    }

}
