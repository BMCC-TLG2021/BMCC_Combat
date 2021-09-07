package com.bmcc.model.character;

import com.bmcc.controller.Vendor;
import com.bmcc.model.equipment.Equipment;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character {
    @JsonIgnore
    private List<Equipment> backpack = new ArrayList<>();
    private final int maxHP;
    private final int maxMP;
    private final int maxDefense;
    private final int maxAttack;
    private int gold = 100;
    private int rank = 8;
    private static Player instance = null;


    private Player(@JsonProperty("name") String name,
                   @JsonProperty("occupation") String occupation,
                   @JsonProperty("race") String race,
                   @JsonProperty("hitPoint") int hitPoint,
                   @JsonProperty("magicPoint") int magicPoint,
                   @JsonProperty("defensePower") int defensePower,
                   @JsonProperty("attackPower") int attackPower,
                   @JsonProperty("gold") int gold,
                   @JsonProperty("rank") int rank) {
        super(name, occupation, race, hitPoint, magicPoint, defensePower, attackPower);
        maxHP = hitPoint;
        maxMP = magicPoint;
        maxDefense = defensePower;
        maxAttack = attackPower;
        this.gold = gold;
        this.rank = rank;
    }

    private Player(String name,
                   String occupation,
                   String race,
                   int hitPoint,
                   int magicPoint,
                   int defensePower,
                   int attackPower
    ) {
        super(name, occupation, race, hitPoint, magicPoint, defensePower, attackPower);
        maxHP = hitPoint;
        maxMP = magicPoint;
        maxDefense = defensePower;
        maxAttack = attackPower;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public int getMaxDefense() {
        return maxDefense;
    }

    public int getMaxAttack() {
        return maxAttack;
    }


    public void getPlayerReadyToFight() {
        this.setHitPoint(maxHP);
        this.setMagicPoint(maxMP);
        this.setDefensePower(maxDefense);
        this.setAttackPower(maxAttack);
        if (this.getWeapon() != null) {
            this.getWeapon().restoreIntegrity();
        }
        if (this.getArmor() != null) {
            this.getArmor().restoreIntegrity();
        }

    }

    @JsonIgnore
    public List<Equipment> getEquipmentFromBackpack() {
        return backpack;
    }

    public void addEquipmentToBackpack(Equipment equipment) {
        this.backpack.add(equipment);
    }

    public boolean removeEquipmentFromBackpack(Equipment equipment) {
        return backpack.remove(equipment);
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public boolean spendGold(int gold) {
        if (this.getGold() - gold >= 0) {
            this.gold -= gold;
            return true;
        } else {
            return false;
        }
    }

    public int getRank() {
        return rank;
    }

    public void rankUp() {
        if (this.rank > 1) {
            this.rank -= 1;
        }
    }

    public static Player createInstanceFromCharacter(Character character) {
        if (instance == null) {
            instance = new Player(character.getName(),
                    character.getOccupation(),
                    character.getRace(),
                    character.getHitPoint(),
                    character.getMagicPoint(),
                    character.getTotalDefensePower(),
                    character.getAttackPower());
        }
        instance.setMagic(character.getMagic());
        return instance;
    }

    public void setRank(int rank){
        this.rank = rank;
    }
}
