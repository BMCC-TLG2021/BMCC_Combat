package com.bmcc.combat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Magic {
    private final String name;
    private final double damage;

    private Magic(String name, double damage){
        this.name = name;
        this.damage = damage;
    }

    public String getName(){return this.name;}

    public double getDamage(){return this.damage;}

    public static Magic getInstanceFromJsonFile(String file) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        double damage = (double) jo.get("damage");

        return new Magic(name, damage);
    }

    public static Magic getInstance(String name, double damage){
        return new Magic(name, damage);
    }
}
