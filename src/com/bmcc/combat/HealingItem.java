package com.bmcc.combat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class HealingItem extends Item{

    private final double healHP;
    private final double healMP;

    private HealingItem(String name, double healHP, double healMP){
        super(name);
        this.healHP = healHP;
        this.healMP = healMP;
    }

    public double getHealHP() {
        return healHP;
    }

    public double getHealMP() {
        return healMP;
    }

    public static HealingItem getInstanceFromJsonFile(String file) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        double healHP = (double) jo.get("healHP");
        double healMP = (double) jo.get("healMP");

        return new HealingItem(name, healHP, healMP);
    }

    public static HealingItem getInstance(String name, double healHP, double healMP){
        return new HealingItem(name, healHP, healMP);
    }
}
