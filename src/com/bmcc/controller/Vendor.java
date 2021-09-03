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

    void buyFromPlayer(String equipmentName) {
        boolean removed = customer.removeEquipmentFromBackpack(equipmentName);
        if (removed) {
            Equipment tradeEquipment = null;
            List<Equipment> backpack = customer.getEquipmentFromBackpack();
            for (Equipment equipment : backpack) {
                if (equipmentName.equalsIgnoreCase(equipment.getName())) {
                    tradeEquipment = equipment;
                }
            }
            int valueEquipment = tradeEquipment.getMoneyValue();
            customer.addGold(valueEquipment);
            // add trade equipment to vendor's lists
            if (tradeEquipment instanceof Weapon) {
                getWeaponList().add((Weapon)tradeEquipment);
            } else {
                getArmorList().add((Armor)tradeEquipment);
            }
            System.out.println("You made " + valueEquipment + " by selling " + equipmentName);
        } else {
            System.out.println("You can't sell equipment that not in your backpack");
        }

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
