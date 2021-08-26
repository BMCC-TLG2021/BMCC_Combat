package com.bmcc.combat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Magic {
    private final String name;
    private final int damage;

    private Magic(String name, int damage){
        this.name = name;
        this.damage = damage;
    }

    public String getName(){return this.name;}

    public int getDamage(){return this.damage;}

    public static Magic getInstanceFromJsonFile(String file) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        int damage = (int)(long) jo.get("damage");

        return new Magic(name, damage);
    }

    public static Magic getInstance(String name, int damage){
        return new Magic(name, damage);
    }
}
