package com.bmcc.model.character;

import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Weapon;
import com.bmcc.model.item.Item;
import com.bmcc.model.skill.Magic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Character {
    @JsonProperty("name")
    private String name;
    @JsonProperty("occupation")
    private String occupation;
    @JsonProperty("race")
    private String race;
    @JsonProperty("hitPoint")
    private int hitPoint;
    @JsonProperty("magicPoint")
    private int magicPoint;
    @JsonProperty("defensePower")
    private int defensePower;
    @JsonProperty("attackPower")
    private int attackPower;
    @JsonIgnore
    private Weapon weapon;
    @JsonIgnore
    private Armor armor;
    @JsonIgnore
    private Magic magic;
    @JsonIgnore
    private List<Item> itemList;
    // Constructors
    private Character(String name, String occupation, String race,int hitPoint, int magicPoint, int defensePower, int attackPower) {
        this.name = name;
        this.occupation = occupation;
        this.race = race;
        this.hitPoint = hitPoint;
        this.magicPoint = magicPoint;
        this.defensePower = defensePower;
        this.attackPower = attackPower;
    }

    // jackson use this default constructor to create object
    private Character() {

    }

    // Business methods

    public void damage(int points) {
        if (hitPoint < points) {
            hitPoint = 0;
        } else {
            hitPoint -= points;
        }
    }

    public boolean reduceMagicPoint() {
        if (getMagicPoint() >= 20) {
            magicPoint -= 20;
            return true;
        }
        return false;
    }

    public int getTotalPhysicalAttackPower() {
        if (this.getWeapon() != null){
            return this.attackPower + this.getWeapon().getPhysicalDamage();
        } else {
            return this.attackPower;
        }


    }

    public int getTotalMagicalPower() {
        int originalDamage = this.getMagic().getDamage();
        if (this.getWeapon() != null) {
            double damageBuffer = this.getWeapon().getMagicPowerIncrease();
            return (int) ((damageBuffer + 1) * originalDamage);
        } else {
            return originalDamage;
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {return occupation;}

    public String getRace() {return race;}

    public int getHitPoint() {
        return hitPoint;
    }

    public int getMagicPoint() {
        return magicPoint;
    }

    public int getTotalDefensePower() {
        if (getArmor() != null){
            return getArmor().getDefenceIncrease() + defensePower;
        } else {
            return defensePower;
        }
    }

    public int getAttackPower() {
        return attackPower;
    }

    public Magic getMagic() {
        return magic;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setMagic(Magic magic) {
        this.magic = magic;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", hitPoint=" + hitPoint +
                ", magicPoint=" + magicPoint +
                ", defensePower=" + defensePower +
                ", attackPower=" + attackPower +
                ", weapon=" + weapon +
                '}';
    }

    public static Character getInstanceFromJsonFile(String fileName) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(fileName));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        String occupation = (String) jo.get("occupation");
        String race = (String) jo.get("race");
        int hitPoint = (int)(long) jo.get("hitPoint");
        int magicPoint = (int)(long) jo.get("magicPoint");
        int defensePower = (int)(long) jo.get("defensePower");
        int attackPower = (int)(long) jo.get("attackPower");

        return new Character(name, occupation, race, hitPoint,magicPoint,defensePower,attackPower);
    }

    public static List<Character> getCharacterListFromJsonFile(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            return Arrays.asList(mapper.readValue(new File(fileName), Character[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Character getInstance(String name, String occupation, String race,int hitPoint,
                                 int magicPoint, int defensePower, int attackPower){
        return new Character(name, occupation, race, hitPoint,magicPoint,defensePower,attackPower);
    }


}
