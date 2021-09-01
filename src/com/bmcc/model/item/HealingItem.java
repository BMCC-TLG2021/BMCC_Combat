package com.bmcc.model.item;

import com.bmcc.model.skill.Magic;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class HealingItem extends Item {
    @JsonProperty("healHP")
    private final int healHP;
    @JsonProperty("healMP")
    private final int healMP;

    private HealingItem(@JsonProperty("name") String name,@JsonProperty("healHP") int healHP, @JsonProperty("healMP")int healMP){
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

    public static List<HealingItem> getHealItemListFromJsonFile(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<HealingItem> healingItemList = Arrays.asList(mapper.readValue(new File(fileName), HealingItem[].class));
            return healingItemList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HealingItem getInstance(String name, int healHP, int healMP){
        return new HealingItem(name, healHP, healMP);
    }
}
