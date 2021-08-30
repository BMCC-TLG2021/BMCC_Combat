package com.bmcc.model.equipment;

public class Armor extends Equipment{
    private final int defenceIncrease;

    private Armor(String name, int integrity, String desc, int defenceIncrease) {
        super(name, integrity, desc);
        this.defenceIncrease = defenceIncrease;
    }

    public int getDefenceIncrease(){
        return this.defenceIncrease;
    }

    public Armor getInstance(String name, int integrity, String desc, int defenceIncrease) {
        return new Armor(name, integrity, desc, defenceIncrease);
    }


    public Equipment getInstanceFromJson(String filePath) {
        return null;
    }


    public Equipment getInstanceFromJsonArray(String filePath) {
        return null;
    }


}
