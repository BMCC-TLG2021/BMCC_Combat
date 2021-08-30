package com.bmcc.model.skill;

import com.bmcc.model.character.Character;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Magic {
    @JsonProperty("name") // optional
    private final String name;
    @JsonProperty("damage") // optional
    private final int damage;

    @JsonCreator
    private Magic(@JsonProperty("name") String name, @JsonProperty("damage") int damage){
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

    public static List<Magic> getMagicListFromJsonFile(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Magic> magicList = Arrays.asList(mapper.readValue(new File(fileName), Magic[].class));
            return magicList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Magic getInstance(String name, int damage){
        return new Magic(name, damage);
    }
}
