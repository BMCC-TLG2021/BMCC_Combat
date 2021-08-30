package com.bmcc.model.item;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class HealingItem extends Item {

    private final int healHP;
    private final int healMP;

    private HealingItem(String name, int healHP, int healMP){
        super(name);
        this.healHP = healHP;
        this.healMP = healMP;
    }

    public int getHealHP() {
        return healHP;
    }

    public int getHealMP() {
        return healMP;
    }

    public static HealingItem getInstanceFromJsonFile(String file) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        int healHP = (int)(long) jo.get("healHP");
        int healMP = (int)(long)jo.get("healMP");

        return new HealingItem(name, healHP, healMP);
    }

    public static HealingItem getInstance(String name, int healHP, int healMP){
        return new HealingItem(name, healHP, healMP);
    }
}
