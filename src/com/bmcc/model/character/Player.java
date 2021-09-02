package com.bmcc.model.character;

import com.bmcc.model.equipment.Equipment;

import java.util.List;

public class Player extends Character {

    private List<Equipment> backpack;
    private final int maxHP;
    private final int maxMP;
    private final int maxDefense;
    private final int maxAttack;
    private int gold = 100;
    private int rank = 8;
    private Player instance;

    private Player(String name, String occupation, String race,int hitPoint, int magicPoint, int defensePower,
                   int attackPower) {
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

    //todo: reset status for player

    public void getPlayerReadyToFight(){

    }

    public List<Equipment> getEquipmentFromBackpack() {
        return backpack;
    }

    public void addEquipmentToBackpack(Equipment equipment) {
        this.backpack.add(equipment);
    }

    public boolean removeEquipmentFromBackpack(String equipmentName){
        int index = -1;
        for (int i = 0; i < backpack.size(); i++){
            if (equipmentName.equalsIgnoreCase(backpack.get(i).getName())){
                index = i;
                break;
            }
        }

        if (index != -1) {
            backpack.remove(index);
        }

        return index != -1;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public boolean spendGold(int gold){
        if (this.getGold() - gold >= 0){
            this.gold -= gold;
            return true;
        } else{
            return false;
        }
    }

    public int getRank() {
        return rank;
    }

    public void rankUp() {
        if (this.rank > 1){
            this.rank -= 1;
        }
    }

    public Player createInstanceFromCharacter(Character character){
        if (this.instance == null){
            instance = new Player(character.getName(),
                    character.getOccupation(),
                    character.getRace(),
                    character.getHitPoint(),
                    character.getMagicPoint(),
                    character.getTotalDefensePower(),
                    character.getAttackPower());
        }
        return this.instance;
    }
}
