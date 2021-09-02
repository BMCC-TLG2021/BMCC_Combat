package com.bmcc.controller;

import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Weapon;
import com.bmcc.util.GameOutput;

import java.io.IOException;
import java.util.List;

public class Vendor {
    private List<Weapon> weaponList = Weapon.getWeaponListFromJsonFile("asset/sampleWeapons.json");;
    private List<Armor> armorList = Armor.getArmorListFromJsonFile("asset/sampleArmors.json");;

    // constructor
    private Vendor() throws Exception {
    }

    void showWeaponList() {
        GameOutput.displayWeaponList(this.getWeaponList());
    }

    void showArmorList() {
        GameOutput.displayArmorList(this.armorList);
    }


    void sellToPlayer(Player userPlayer) {

    }

    void buyFromPlayer(Player userPlayer) {

    }

    Vendor makeVendor() throws Exception {
        return new Vendor();
    }

    // getter
    List<Weapon> getWeaponList() {
        return weaponList;
    }

    List<Armor> getArmorList() {
        return armorList;
    }
}
