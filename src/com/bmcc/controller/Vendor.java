package com.bmcc.controller;

import com.bmcc.model.character.Player;
import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Equipment;
import com.bmcc.model.equipment.Weapon;
import com.bmcc.util.GameOutput;

import java.io.IOException;
import java.util.List;

public class Vendor {
    private List<Weapon> weaponList = Weapon.getWeaponListFromJsonFile("asset/sampleWeapons.json");
    private List<Armor> armorList = Armor.getArmorListFromJsonFile("asset/sampleArmors.json");
    private static Vendor instance = null;
    private Player customer;
    // constructor
    private Vendor(Player player) throws Exception {
        customer = player;
    }

    void showWeaponList() {
        GameOutput.displayWeaponList(this.getWeaponList());
    }

    void showArmorList() {
        GameOutput.displayArmorList(this.armorList);
    }


    void sellToPlayer(Equipment equipment) {

    }

    void buyFromPlayer(Equipment equipment) {

    }

    public static Vendor createInstance(Player player) throws Exception {
        if (instance == null) {
            instance = new Vendor(player);
        }
        return instance;
    }

    // getter
    List<Weapon> getWeaponList() {
        return weaponList;
    }

    List<Armor> getArmorList() {
        return armorList;
    }

    public void tradeEquipment() {

    }
}
